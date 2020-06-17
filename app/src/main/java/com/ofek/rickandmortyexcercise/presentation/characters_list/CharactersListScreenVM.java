package com.ofek.rickandmortyexcercise.presentation.characters_list;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ofek.rickandmortyexcercise.domain.objects.PagingResult;
import com.ofek.rickandmortyexcercise.domain.usecases.GetCharactersList;
import com.ofek.rickandmortyexcercise.presentation.common.errors.GenericResponseError;
import com.ofek.rickandmortyexcercise.presentation.common.errors.PresentationError;
import com.ofek.rickandmortyexcercise.presentation.mappers.UiMappers;
import com.ofek.rickandmortyexcercise.presentation.objects.UiCharacter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
@SuppressWarnings({"ConstantConditions"})
public class CharactersListScreenVM extends ViewModel {

    private final GetCharactersList getCharactersList;
    // stores all active streams, helps to terminate all streams when needed
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<CharactersListState> stateLiveData = new MutableLiveData<>();
    private final MutableLiveData<PresentationError> errorLiveData = new MutableLiveData<>();
    // the observing scheduler determines on which thread the callbacks runs on, usually will be the main thread. but also could be test scheduler and such...
    private final Scheduler observingScheduler;
    public CharactersListScreenVM(GetCharactersList getCharactersList, Scheduler observingScheduler) {
        this.getCharactersList = getCharactersList;
        this.observingScheduler = observingScheduler;
        // initialize the state live data to prevent nullability
        stateLiveData.setValue(CharactersListState.getInitialState());
    }

    @SuppressWarnings("unchecked")
    public static class Factory implements ViewModelProvider.Factory {
        private final GetCharactersList getCharactersList;
        private final Scheduler observingScheduler;

        public Factory(GetCharactersList getCharactersList, Scheduler observingScheduler) {
            this.getCharactersList = getCharactersList;
            this.observingScheduler = observingScheduler;
        }

        @androidx.annotation.NonNull
        @Override
        public <T extends ViewModel> T create(@androidx.annotation.NonNull Class<T> modelClass) {
            if (CharactersListScreenVM.class.isAssignableFrom(modelClass)) {
                try {
                    return (T) CharactersListScreenVM.class.getConstructor(GetCharactersList.class,Scheduler.class).newInstance(getCharactersList,observingScheduler);
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
                    throw new IllegalStateException(e);
                }
            } else {
                throw new IllegalStateException("the requested view model is not a class or sub class of CharactersListScreenVM");
            }
        }
    }

    public void loadNextCharactersPage(){
        CharactersListState currentState = stateLiveData.getValue();
        // in this case there's no more pages to load, thus the method should exit
        if (currentState.getLastPageLoaded() >= currentState.getMaxPage()) {
           return;
        }
        getCharactersList.getCharacters(currentState.getLastPageLoaded()+1)
                .map(UiMappers::mapPagingResultToUi)
                .observeOn(observingScheduler)
                .subscribe(new SingleObserver<PagingResult<List<UiCharacter>>>() {
                    Disposable disposable;
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                        compositeDisposable.add(d);
                        // updates the state to loading state
                        CharactersListState.CopyBuilder newState = stateLiveData.getValue().getCopyBuilder().setLoading(true);
                        stateLiveData.setValue(newState.build());
                    }

                    @Override
                    public void onSuccess(@NonNull PagingResult<List<UiCharacter>> listPagingResult) {
                        CharactersListState.CopyBuilder newState = stateLiveData.getValue().getCopyBuilder()
                                .setLoading(false)
                                .addCharacters(listPagingResult.getResult())
                                .setMaxPage(listPagingResult.getMaxPage())
                                .setLastPageLoaded(listPagingResult.getPage());
                        stateLiveData.setValue(newState.build());
                        if (!disposable.isDisposed()) {
                            disposable.dispose();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        CharactersListState.CopyBuilder newState = stateLiveData.getValue().getCopyBuilder()
                                .setLoading(false);
                        //sends a generic error to the observers
                        errorLiveData.setValue(new GenericResponseError());
                        stateLiveData.setValue(newState.build());
                        if (!disposable.isDisposed()) {
                            disposable.dispose();
                        }
                    }
                });
    }

    public void loadFirstPage(boolean refresh) {
        getCharactersList.getCharacters(1)
                .map(UiMappers::mapPagingResultToUi)
                .observeOn(observingScheduler)
                .subscribe(new SingleObserver<PagingResult<List<UiCharacter>>>() {
                    Disposable disposable;
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                        compositeDisposable.add(d);
                        // starts refreshing state
                        CharactersListState.CopyBuilder newState = stateLiveData.getValue().getCopyBuilder()
                                .setRefreshing(true);
                        stateLiveData.setValue(newState.build());
                    }

                    @Override
                    public void onSuccess(@NonNull PagingResult<List<UiCharacter>> listPagingResult) {
                        // when refreshing the list should reset, thus the state restarts from the initial state and the loaded items is added
                        CharactersListState.CopyBuilder newState = CharactersListState.getInitialState()
                                .getCopyBuilder()
                                .setRefreshing(false)
                                .setMaxPage(listPagingResult.getMaxPage())
                                .setLastPageLoaded(listPagingResult.getPage())
                                .addCharacters(listPagingResult.getResult());
                        stateLiveData.setValue(newState.build());
                        if (!disposable.isDisposed()) {
                            disposable.dispose();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        CharactersListState.CopyBuilder newState = stateLiveData.getValue().getCopyBuilder()
                                .setRefreshing(false);
                        errorLiveData.setValue(new GenericResponseError());
                        //sends a generic error to the observers
                        stateLiveData.setValue(newState.build());
                        if (!disposable.isDisposed()) {
                            disposable.dispose();
                        }
                    }
                });

    }

    @Override
    protected void onCleared() {
        // clear all active streams before clearing the view model in order to prevent "ghost" threads and irrelevant calls
        compositeDisposable.dispose();
        super.onCleared();
    }
}
