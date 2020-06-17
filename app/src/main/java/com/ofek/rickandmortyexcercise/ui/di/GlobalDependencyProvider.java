package com.ofek.rickandmortyexcercise.ui.di;

import com.ofek.rickandmortyexcercise.data.datastores.RnMDataStore;
import com.ofek.rickandmortyexcercise.data.di.common.DataCommonModule;
import com.ofek.rickandmortyexcercise.data.di.managers.DataStoreModule;
import com.ofek.rickandmortyexcercise.data.di.repositories.RepositoriesModule;
import com.ofek.rickandmortyexcercise.data.services.RnMService;
import com.ofek.rickandmortyexcercise.domain.common.AsyncTransformers;
import com.ofek.rickandmortyexcercise.domain.di.UseCasesModule;
import com.ofek.rickandmortyexcercise.domain.objects.CharacterObj;
import com.ofek.rickandmortyexcercise.domain.objects.PagingResult;
import com.ofek.rickandmortyexcercise.domain.repositories.CharactersRepo;
import com.ofek.rickandmortyexcercise.domain.usecases.GetCharactersList;
import com.ofek.rickandmortyexcercise.presentation.characters_list.CharactersListScreenVM;
import com.ofek.rickandmortyexcercise.presentation.di.viewmodels.ViewModelFactoriesModule;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.SingleTransformer;

/**
 * this class builds the view model factories from the modules
 * by looking at this class the whole application dependency tree can be explained
 */
public class GlobalDependencyProvider {

    public static CharactersListScreenVM.Factory getCharactersListVMFactory() {
        RnMService rnMService = DataCommonModule.getManagersProvider().provideRnMService();
        RnMDataStore rnMDataStore = DataStoreModule.getManagersProvider().provideRnMDataStore(rnMService);
        CharactersRepo charactersRepo = RepositoriesModule.getRepositoriesProvider().provideCharactersRepo(rnMDataStore);
        SingleTransformer<PagingResult<List<CharacterObj>>, PagingResult<List<CharacterObj>>> transformer = AsyncTransformers.getSingleTransformer();
        GetCharactersList getCharactersList = UseCasesModule.getUseCasesProvider().provideGetCharactersList(charactersRepo, transformer);
        // the callbacks on the view model will run on this scheduler(main thread);
        Scheduler observingScheduler = AndroidSchedulers.mainThread();
        return ViewModelFactoriesModule.getFactoriesProvider().provideCharactersListVMFactory(getCharactersList, observingScheduler);
    }
}
