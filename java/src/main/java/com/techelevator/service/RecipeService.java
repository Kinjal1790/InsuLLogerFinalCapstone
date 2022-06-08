package com.techelevator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.FoodDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Component
public class RecipeService {

    @Value("${food.api.url}")
    private String apiURL;

    @Value("${food.api.key}")
    private String key;

    public List<Integer> getSearchResults(int carbs) {
        String url = apiURL  + "findByNutrients" + "?maxCarbs=" + carbs;
        System.out.println(url);
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", key);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        System.out.println(response);
        JsonNode jsonNode;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Integer> recipeIds = new ArrayList<>();


        try {
            jsonNode = objectMapper.readTree(response.getBody());

            for(int i = 0; i < jsonNode.size(); i++) {
                int id = Integer.parseInt(jsonNode.path(i).path("id").asText()) ;
                recipeIds.add(id);

            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return recipeIds;
    }

    public FoodDTO getFoodInfo(int id) {
        String url = apiURL  + id + "/information";
        System.out.println(url);
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", key);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                String.class
        );

//        System.out.println(response);
        JsonNode jsonNode;
        ObjectMapper objectMapper = new ObjectMapper();
        FoodDTO recipeInfo = new FoodDTO();


        try {
            jsonNode = objectMapper.readTree(response.getBody());
            recipeInfo.setTitle(jsonNode.path("title").asText());
            recipeInfo.setImage(jsonNode.path("image").asText());
            recipeInfo.setSummary(jsonNode.path("summary").asText());
            recipeInfo.setInstructions(jsonNode.path("instructions").asText());
            List<String> ingredients = new ArrayList<>();
            JsonNode root = jsonNode.path("extendedIngredients");
            for(int i = 0; i < root.size(); i++) {
                String ingredient = root.path(i).path("name").asText();
                ingredients.add(ingredient);
            }
            recipeInfo.setIngredients(ingredients);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return recipeInfo;
    }

}
