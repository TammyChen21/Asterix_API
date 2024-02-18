package com.example.asterix_api.service;

import com.example.asterix_api.model.Character;
import com.example.asterix_api.model.dtos.CharacterDto;
import com.example.asterix_api.repository.CharacterRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CharacterServiceTest {
    private final CharacterRepo currywurst= mock(CharacterRepo.class);
    private final IdService idMock= mock(IdService.class);
    CharacterService service = new CharacterService(currywurst, idMock);
    @Test

    void getAllCharacters_shouldReturnEmptyList_WhenCalledInitially() {
        //GIVEN
        List<Character> expected = new ArrayList<>();
        when(currywurst.findAll()).thenReturn(new ArrayList<>());
        //WHEN
        List<Character> actual = service.getCharacters();
        //THEN
        assertEquals(expected, actual);
    }

    @Test

    void getNoIdCharacter(){
        //GIVEN
        Character c1 = new Character("1!","Asterix", 35, "Warrior");
        Character c2 = new Character("2","Obelix", 35, "Blacksmith");
        List<Character> characters=List.of(c1,c2);
        when(currywurst.findAll()).thenReturn(characters);

        CharacterDto cdto1 = new CharacterDto("Asterix", 35, "Warrior");
        CharacterDto cdto2 = new CharacterDto("Obelix", 35, "Blacksmith");
        List<CharacterDto> expected = new ArrayList<>();
        expected.add(cdto1);
        expected.add(cdto2);
        //WHEN
        List<CharacterDto> actual = service.getNoIdCharacter();
        //THEN
        assertEquals(expected, actual);
        verify(currywurst).findAll();

    }

    @Test
    void addNewCharacter(){
        //GIVEN
        Character characterToAdd = new Character("jhg","Asterix", 35, "Warrior");
        Character expected = new Character("1","Asterix", 35, "Warrior");
        when(idMock.generateUUID()).thenReturn("1");
        when(currywurst.findById("1")).thenReturn(java.util.Optional.of(expected));

        //WHEN
        Character actual = service.addNesCharacter(characterToAdd);
        //THEN
        assertEquals(expected, actual);

        verify(idMock).generateUUID();
        verify(currywurst).findById("1");
    }

    @Test
    void getCharacterById(){
        //GIVEN
        Character expected = new Character("1","Asterix", 35, "Warrior");
        when(currywurst.findById("1")).thenReturn(java.util.Optional.of(expected));
        //WHEN
        Character actual = service.getCharacterById("1");
        //THEN
        assertEquals(expected, actual);
        verify(currywurst).findById("1");
    }
    @Test
    void deleteCharacterById(){
        //GIVEN
        Character expected = new Character("1","Asterix", 35, "Warrior");
        when(currywurst.findById("1")).thenReturn(java.util.Optional.of(expected));
        //WHEN
        Character actual = service.deleteCharacterById("1");
        //THEN
        assertEquals(expected, actual);
        verify(currywurst).deleteById("1");
    }
    @Test
    void updateCharacter(){
        //GIVEN
        CharacterDto characterDto = new CharacterDto("Asterix", 35, "Warrior");
        Character expected = new Character("1","Asterix", 35, "Warrior");
        when(currywurst.existsById("1")).thenReturn(true);
        when(currywurst.save(expected)).thenReturn(expected);
        //WHEN
        Character actual = service.updateCharacter(characterDto, "1");
        //THEN
        assertEquals(expected, actual);
        verify(currywurst).existsById("1");
        verify(currywurst).save(expected);
    }
}
