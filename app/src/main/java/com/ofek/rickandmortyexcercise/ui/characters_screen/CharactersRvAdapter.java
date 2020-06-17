package com.ofek.rickandmortyexcercise.ui.characters_screen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.ofek.rickandmortyexcercise.R;
import com.ofek.rickandmortyexcercise.presentation.objects.UiCharacter;

import java.util.List;

public class CharactersRvAdapter extends RecyclerView.Adapter<CharactersRvAdapter.ViewHolder> {
    @NonNull
    private List<UiCharacter> uiCharacters;
    @Nullable
    private final OnCharacterSelectedListener listener;

    public CharactersRvAdapter(@NonNull List<UiCharacter> uiCharacter, @Nullable OnCharacterSelectedListener listener) {
        this.uiCharacters = uiCharacter;
        this.listener = listener;
    }

    public CharactersRvAdapter setUiCharacters(@NonNull List<UiCharacter> uiCharacters) {
        this.uiCharacters = uiCharacters;
        return this;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.character_single_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UiCharacter item = uiCharacters.get(position);
        holder.name.setText(item.getName());
        holder.gender.setText(item.getGender());
        holder.species.setText(item.getSpecies());
    }

    @Override
    public int getItemCount() {
        return uiCharacters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,gender,species;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            gender = itemView.findViewById(R.id.gender);
            species = itemView.findViewById(R.id.species);
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onCharacterSelected(uiCharacters.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnCharacterSelectedListener {
        void onCharacterSelected(UiCharacter uiCharacter);
    }
}
