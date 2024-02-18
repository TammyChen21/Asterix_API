package com.example.asterix_api.service;

import com.example.asterix_api.model.Character;
import com.example.asterix_api.model.dtos.CharacterDto;
import com.example.asterix_api.repository.CharacterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepo characterRepo;
    private final IdService idService;

    public List<Character> getCharacters() {
        return characterRepo.findAll();
    }

    public List<CharacterDto> getNoIdCharacter() {

        List<Character> temp = characterRepo.findAll();
        List<CharacterDto> dtoList = new ArrayList<>();
        for (Character c : temp) {
            CharacterDto cdto = new CharacterDto(c.getName(), c.getAge(), c.getProfession());
            dtoList.add(cdto);
        }
        return dtoList;
    }

    public Character addNesCharacter(Character character) {
        Character temp=character.withId(idService.generateUUID());
        characterRepo.save(temp);
        return characterRepo.findById(temp.getId()).orElseThrow();
    }

    public Character getCharacterById(String id) {
        return characterRepo.findById(id).orElseThrow();
    }

    public Character deleteCharacterById(String id) {
        Character temp = characterRepo.findById(id).orElseThrow();
        characterRepo.deleteById(id);
        return temp;
    }

    public Character updateCharacter(CharacterDto characterDto, String id) {
        if (characterRepo.existsById(id)) {
            return characterRepo.save(new Character(id, characterDto.getName(), characterDto.getAge(), characterDto.getProfession()));
        }
        throw new IllegalArgumentException("Character with id " + id + " does not exist");
    }


}

