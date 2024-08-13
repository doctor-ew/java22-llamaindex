package com.doctorew.llamaindexram;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public class Scraper {

    public static void main(String[] args) {
        Scraper scraper = new Scraper();
        String url = "https://www.doctorew.com/shuttlebay/pocketmortys_data.json";
        String jsonData = scraper.scrapeJsonData(url);

        if (!jsonData.isEmpty()) {
            List<PocketMortyCharacter> characters = scraper.parseJson(jsonData);
            characters.forEach(System.out::println);
            scraper.insertIntoMongoDB(characters);
        }
    }

    public String scrapeJsonData(String url) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            page.navigate(url);

            // Check if the content is inside a <pre> tag
            boolean hasPreTag = (boolean) page.evaluate("document.querySelector('pre') !== null");

            String jsonContent;
            if (hasPreTag) {
                // Extract the JSON content from the <pre> tag
                jsonContent = page.evaluate("document.querySelector('pre').textContent").toString();
            } else {
                // Otherwise, assume the page content is plain JSON
                jsonContent = page.content();
            }

            // Log the extracted content
            System.out.println("Fetched content:");
            System.out.println(jsonContent);

            return jsonContent;
        }
    }

    public List<PocketMortyCharacter> parseJson(String jsonData) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonData, new TypeReference<List<PocketMortyCharacter>>() {});
        } catch (JsonProcessingException e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
            return List.of(); // Return an empty list on error
        }
    }

    public void insertIntoMongoDB(List<PocketMortyCharacter> characters) {
        try (var mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("vector_store");

            for (PocketMortyCharacter character : characters) {
                Document doc = new Document("id", character.getId())
                        .append("name", character.getName())
                        .append("type", character.getType())
                        .append("rarity", character.getRarity())
                        .append("base_xp", character.getBaseXp())
                        .append("base_hp", character.getBaseHp())
                        .append("base_atk", character.getBaseAtk())
                        .append("base_def", character.getBaseDef())
                        .append("base_spd", character.getBaseSpd())
                        .append("stat_total", character.getStatTotal())
                        .append("number_to_evolve", character.getNumberToEvolve())
                        .append("badges_required", character.getBadgesRequired())
                        .append("dimension", character.getDimension());

                collection.insertOne(doc);
            }

            System.out.println("Data inserted into MongoDB successfully.");
        } catch (Exception e) {
            System.err.println("Error inserting data into MongoDB: " + e.getMessage());
        }
    }
}
