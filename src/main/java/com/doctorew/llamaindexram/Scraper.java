package com.doctorew.llamaindexram;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Scraper {

    public String scrapeData(String url) throws ExecutionException, InterruptedException {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            page.navigate(url);

            // Wait for the page to load
            page.waitForLoadState(LoadState.NETWORKIDLE);

            // Extract JSON content from a <pre> tag (similar to the Python example)
            String jsonContent = page.textContent("pre");

            browser.close();
            return jsonContent;
        } catch (Exception e) {
            System.err.println("Error scraping " + url + ": " + e.getMessage());
            return "";
        }
    }

    public List<RickAndMortyCharacter> parseJson(String jsonContent) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonContent, new TypeReference<List<RickAndMortyCharacter>>() {});
        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
            return List.of();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Scraper scraper = new Scraper();
        String data = scraper.scrapeData("https://www.doctorew.com/shuttlebay/rick_and_morty_characters.json");

        List<RickAndMortyCharacter> characters = scraper.parseJson(data);
        characters.forEach(System.out::println); // Print each character
    }
}
