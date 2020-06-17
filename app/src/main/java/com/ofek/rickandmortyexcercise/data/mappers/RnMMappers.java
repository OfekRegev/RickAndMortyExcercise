package com.ofek.rickandmortyexcercise.data.mappers;

import com.ofek.rickandmortyexcercise.data.objects.RnMCharacterDto;
import com.ofek.rickandmortyexcercise.domain.objects.CharacterObj;

public class RnMMappers {

    public static CharacterObj mapCharacterDtoToCharacterObj(RnMCharacterDto characterDto) {
        CharacterObj characterObj = new CharacterObj();
        characterObj.setName(characterDto.getName());
        characterObj.setGender(characterDto.getGender());
        characterObj.setSpecies(characterDto.getSpecies());
        characterObj.setImageUrl(characterDto.getImage());
        return characterObj;
    }
}
