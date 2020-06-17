package com.ofek.rickandmortyexcercise.presentation.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class UiCharacter implements Parcelable {
    private String name;
    private String species;
    private String gender;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public UiCharacter setName(String name) {
        this.name = name;
        return this;
    }

    public String getSpecies() {
        return species;
    }

    public UiCharacter setSpecies(String species) {
        this.species = species;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public UiCharacter setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UiCharacter setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.species);
        dest.writeString(this.gender);
        dest.writeString(this.imageUrl);
    }

    public UiCharacter() {
    }

    protected UiCharacter(Parcel in) {
        this.name = in.readString();
        this.species = in.readString();
        this.gender = in.readString();
        this.imageUrl = in.readString();
    }

    public static final Parcelable.Creator<UiCharacter> CREATOR = new Parcelable.Creator<UiCharacter>() {
        @Override
        public UiCharacter createFromParcel(Parcel source) {
            return new UiCharacter(source);
        }

        @Override
        public UiCharacter[] newArray(int size) {
            return new UiCharacter[size];
        }
    };
}
