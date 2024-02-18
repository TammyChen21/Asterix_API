package com.example.asterix_api.controller;

import com.example.asterix_api.model.Character;
import com.example.asterix_api.model.dtos.CharacterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.asterix_api.service.CharacterService;

import java.util.List;

@RestController
@RequestMapping("/asterix")
@RequiredArgsConstructor

public class AsterixController {

   private final CharacterService service;

   @GetMapping
    public List<Character> getAllCharacters(){
        return service.getCharacters();

    }
    @GetMapping("/noids")
    public List<CharacterDto> getAllNoIds(){
       return service.getNoIdCharacter();
    }
    @PostMapping
    public Character addNewCharacter(@RequestBody Character character){
    return service.addNesCharacter(character);

    }
    @GetMapping("/{id}")
    public Character getCharacterById(@PathVariable String id){
       return service.getCharacterById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteCharacterById(@PathVariable String id){
       Character deleted= service.deleteCharacterById(id);
         return deleted.name()+" has been deleted";
    }
    @PutMapping("/update/{id}")
    public Character updateCharacter(@RequestBody CharacterDto characterDto, @PathVariable String id){
       return service.updateCharacter(characterDto, id);
    }

}
