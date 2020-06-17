package com.ofek.rickandmortyexcercise.presentation.characters_list;

import com.google.common.collect.ImmutableList;
import com.ofek.rickandmortyexcercise.presentation.objects.UiCharacter;

import java.util.List;

/**
 * this state represents the characters list screen
 * state can hold only immutable values as it should not be changed once it has been created
 * the only way to change a state is to build new one with the copy builder
 */
public class CharactersListState {
    private final ImmutableList<UiCharacter> characterObjs;
    private final int lastPageLoaded;
    private final int maxPage;
    private final boolean isLoading;
    private final boolean isRefreshing;

    private CharactersListState(ImmutableList<UiCharacter> characterObjs, int lastPageLoaded, int maxPage, boolean isLoading, boolean isRefreshing) {
        this.characterObjs = characterObjs;
        this.lastPageLoaded = lastPageLoaded;
        this.maxPage = maxPage;
        this.isLoading = isLoading;
        this.isRefreshing = isRefreshing;
    }

    public static CharactersListState getInitialState(){
        return new CharactersListState(ImmutableList.of(),0,0,false,false);
    }

    public CharactersListState.CopyBuilder getCopyBuilder(){
        return new CopyBuilder(this);
    }

    public ImmutableList<UiCharacter> getCharacterObjs() {
        return characterObjs;
    }

    public int getLastPageLoaded() {
        return lastPageLoaded;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public static class CopyBuilder {
        private ImmutableList<UiCharacter> characterObjs;
        private int lastPageLoaded;
        private int maxPage;
        private boolean isLoading;
        private boolean isRefreshing;

        private CopyBuilder(CharactersListState toCopy) {
            this.characterObjs = toCopy.characterObjs;
            this.lastPageLoaded = toCopy.lastPageLoaded;
            this.maxPage = toCopy.maxPage;
            this.isLoading = toCopy.isLoading;
            this.isRefreshing = toCopy.isRefreshing;
        }

        public CopyBuilder addCharacters(List<UiCharacter> characterObjs) {
            // creates a new immutable list from the last state list and the new added list
            this.characterObjs = ImmutableList.<UiCharacter>builder().addAll(characterObjs).addAll(characterObjs).build();
            return this;
        }

        public CopyBuilder setLastPageLoaded(int lastPageLoaded) {
            this.lastPageLoaded = lastPageLoaded;
            return this;
        }

        public CopyBuilder setMaxPage(int maxPage) {
            this.maxPage = maxPage;
            return this;
        }

        public CopyBuilder setLoading(boolean loading) {
            isLoading = loading;
            return this;
        }

        public CopyBuilder setRefreshing(boolean refreshing) {
            isRefreshing = refreshing;
            return this;
        }
        public CharactersListState build() {
            return new CharactersListState(characterObjs,lastPageLoaded,maxPage,isLoading,isRefreshing);
        }
    }
}
