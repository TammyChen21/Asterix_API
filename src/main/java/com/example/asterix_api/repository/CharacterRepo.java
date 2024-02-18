package com.example.asterix_api.repository;

import com.example.asterix_api.model.Character;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CharacterRepo extends MongoRepository<Character, String> {

}
