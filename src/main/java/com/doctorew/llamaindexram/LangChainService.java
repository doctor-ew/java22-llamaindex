package com.doctorew.llamaindexram;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

@Service
public class LangChainService {

    private final MongoCollection<Document> collection;
    private final OpenAiEmbeddingModel embeddingModel;

    public LangChainService() {
        // Load the API key from environment variables
        String apiKey = System.getenv("OPENAI_API_KEY");

        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("OpenAI API key is not set in environment variables.");
        }

        // Initialize MongoDB client
        var mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("test");

        // Get the MongoDB collection
        collection = database.getCollection("vector_store");

        // Initialize OpenAI Embedding Model
        embeddingModel = OpenAiEmbeddingModel.builder()
                .apiKey(apiKey)
                .build();
    }

    public String performQuery(String query) {
        // Generate embeddings for the query
        Embedding embedding = embeddingModel.embed(query).content();

        // Here you'd write code to query the MongoDB collection using the embedding
        // This part may involve custom logic since `langchain4j` may not provide a direct API for this
        // For now, just return a placeholder response
        return "This would be the response from MongoDB after querying with the embedding.";
    }

    public void exampleQueries() {
        List<String> queries = List.of(
                "Which Paper Morty has the strongest defenses?",
                "Which Rock Morty has the strongest Attack?",
                "Which Morty might win a matchup?"
        );

        for (String query : queries) {
            String response = performQuery(query);
            System.out.println("Query: " + query);
            System.out.println("Response: " + response);
        }
    }

    public static void main(String[] args) {
        LangChainService service = new LangChainService();
        service.exampleQueries();
    }
}
