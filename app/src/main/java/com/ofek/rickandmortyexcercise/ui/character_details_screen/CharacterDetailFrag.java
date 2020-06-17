package com.ofek.rickandmortyexcercise.ui.character_details_screen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ofek.rickandmortyexcercise.R;
import com.ofek.rickandmortyexcercise.presentation.objects.UiCharacter;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterDetailFrag extends Fragment {

    private static final String CHARACTER_KEY = "character";
    private TextView name,gender,species;
    private ImageView photoIv;
    public CharacterDetailFrag() {
        // Required empty public constructor
    }

    public static CharacterDetailFrag newInstance(UiCharacter character) {

        Bundle args = new Bundle();
        args.putParcelable(CHARACTER_KEY,character);
        CharacterDetailFrag fragment = new CharacterDetailFrag();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View itemView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(itemView, savedInstanceState);
        if (getArguments() != null && getArguments().getParcelable(CHARACTER_KEY) != null) {
            UiCharacter character = getArguments().getParcelable(CHARACTER_KEY);
            name = itemView.findViewById(R.id.name_tv);
            gender = itemView.findViewById(R.id.gender_tv);
            species = itemView.findViewById(R.id.species_tv);
            photoIv = itemView.findViewById(R.id.photo_iv);
            name.setText(character.getName());
            gender.setText(character.getGender());
            species.setText(character.getSpecies());
            Picasso.get().load(character.getImageUrl()).into(photoIv);
        }
        itemView.findViewById(R.id.back_btn).setOnClickListener(v -> {
            // clicking on back button does the same effect as the device back button
            getActivity().onBackPressed();
        });
    }
}
