package com.doctorew.llamaindexram;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomMongoQueryService {

    private final MongoCollection<Document> collection;

    public CustomMongoQueryService() {
        var mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("test");
        this.collection = database.getCollection("vector_store");
    }

    public List<Document> findTopMortysByAttribute(String attribute, int limit) {
        return collection.find()
                .sort(new Document(attribute, -1))  // Sort by the attribute in descending order
                .limit(limit)
                .into(new ArrayList<>());
    }

    public List<Document> findMortysByType(String type) {
        return collection.find(new Document("type", type))
                .into(new ArrayList<>());
    }

    public Document findStrongestMorty() {
        return collection.find()
                .sort(new Document("base_atk", -1))
                .first();
    }

    // Example usage
    public static void main(String[] args) {
        CustomMongoQueryService service = new CustomMongoQueryService();

        System.out.println("Top 5 strongest Mortys:");
        List<Document> strongestMortys = service.findTopMortysByAttribute("base_atk", 5);
        strongestMortys.forEach(System.out::println);

        System.out.println("Mortys of type 'Rock':");
        List<Document> rockMortys = service.findMortysByType("Rock");
        rockMortys.forEach(System.out::println);

        System.out.println("Morty with the highest attack:");
        Document strongestMorty = service.findStrongestMorty();
        System.out.println(strongestMorty);
    }
}
