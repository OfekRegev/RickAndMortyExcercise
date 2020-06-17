package com.ofek.rickandmortyexcercise.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ofek.rickandmortyexcercise.R;
import com.ofek.rickandmortyexcercise.presentation.objects.UiCharacter;
import com.ofek.rickandmortyexcercise.ui.character_details_screen.CharacterDetailFrag;
import com.ofek.rickandmortyexcercise.ui.characters_screen.CharactersListFragment;
import com.ofek.rickandmortyexcercise.ui.characters_screen.CharactersRvAdapter;

public class MainActivity extends AppCompatActivity implements CharactersRvAdapter.OnCharacterSelectedListener {
    private static final String CHARACTERS_LIST_FRAG_TAG = "characters_list";
    private static final String CHARACTER_DETAILS_FRAG_TAG = "details";
    private CharactersListFragment charactersListFragment = new CharactersListFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,charactersListFragment).commit();
    }


    @Override
    public void onCharacterSelected(UiCharacter uiCharacter) {
        getSupportFragmentManager().beginTransaction().add(R.id.main_container, CharacterDetailFrag.newInstance(uiCharacter))
                .addToBackStack(CHARACTER_DETAILS_FRAG_TAG)
                .commit();
    }
}
