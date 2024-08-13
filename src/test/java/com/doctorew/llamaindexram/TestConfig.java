package com.doctorew.llamaindexram;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.autoconfigure.vectorstore.mongo.MongoDBAtlasVectorStoreAutoConfiguration;

import java.util.List;

@Configuration
@EnableAutoConfiguration(exclude = MongoDBAtlasVectorStoreAutoConfiguration.class)
public class TestConfig {

    @Bean
    public EmbeddingModel embeddingModel() {
        // Return a dummy or mock EmbeddingModel instance
        return new EmbeddingModel() {
            @Override
            public EmbeddingResponse call(EmbeddingRequest request) {
                // Return a dummy response or null if not needed
                return null;
            }

            @Override
            public List<Double> embed(Document document) {
                // Return a dummy embedding
                return List.of(0.0, 0.0, 0.0); // Example dummy data
            }
        };
    }
}
