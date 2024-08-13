package com.doctorew.llamaindexram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration;
import org.springframework.ai.autoconfigure.vectorstore.mongo.MongoDBAtlasVectorStoreAutoConfiguration;

@SpringBootApplication(exclude = {OpenAiAutoConfiguration.class, MongoDBAtlasVectorStoreAutoConfiguration.class})
public class LlamaindexRamApplication {

    public static void main(String[] args) {
        SpringApplication.run(LlamaindexRamApplication.class, args);
    }
}
