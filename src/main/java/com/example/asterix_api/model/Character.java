package com.example.asterix_api.model;

import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@With

@Document(collection = "characters")
public record Character(@Id String id, String name, int age, String profession) {

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getProfession() {
        return profession;
    }

    public String getId() {
        return id;
    }
}
