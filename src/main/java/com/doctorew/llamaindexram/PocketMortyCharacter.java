package com.doctorew.llamaindexram;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PocketMortyCharacter {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("rarity")
    private String rarity;

    @JsonProperty("base_xp")
    private Integer baseXp;

    @JsonProperty("base_hp")
    private Integer baseHp;

    @JsonProperty("base_atk")
    private Integer baseAtk;

    @JsonProperty("base_def")
    private Integer baseDef;

    @JsonProperty("base_spd")
    private Integer baseSpd;

    @JsonProperty("stat_total")
    private Integer statTotal;

    @JsonProperty("number_to_evolve")
    private Integer numberToEvolve;

    @JsonProperty("badges_required")
    private String badgesRequired;

    @JsonProperty("dimension")
    private String dimension;

    @Override
    public String toString() {
        return "PocketMortyCharacter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", rarity='" + rarity + '\'' +
                ", baseXp=" + baseXp +
                ", baseHp=" + baseHp +
                ", baseAtk=" + baseAtk +
                ", baseDef=" + baseDef +
                ", baseSpd=" + baseSpd +
                ", statTotal=" + statTotal +
                ", numberToEvolve=" + numberToEvolve +
                ", badgesRequired=" + badgesRequired +
                ", dimension='" + dimension + '\'' +
                '}';
    }
}
