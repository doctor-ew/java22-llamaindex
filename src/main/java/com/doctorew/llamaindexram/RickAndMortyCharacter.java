package com.doctorew.llamaindexram;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true) // This will ignore any fields in the JSON that are not mapped in the class
public class RickAndMortyCharacter {

    // Getters and Setters
    @Setter
    @Getter
    private int id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String status;
    @Setter
    @Getter
    private String species;
    @Getter
    private String type;
    private String gender;
    private Map<String, String> origin;
    private Map<String, String> location;
    private String image;
    private List<String> episode;
    private String url;
    private String created;
}
