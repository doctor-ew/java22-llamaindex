package com.doctorew.llamaindexram;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestConfig.class)
class LlamaindexRamApplicationTests {

    @Test
    void contextLoads() {
        // Test to ensure the context loads without issues
    }
}
