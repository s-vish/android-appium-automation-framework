package dev.vishx.qa.framework.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

public class TestDataReader {

    private final JsonNode rootNode;

    public TestDataReader(String resourcePath){
        ObjectMapper objectMapper = new ObjectMapper();

        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)){
            if(inputStream==null){
                throw new IllegalArgumentException("Test data resource not found: "+resourcePath);
            }
            rootNode = objectMapper.readTree(inputStream);
        } catch (IOException e){
            throw new RuntimeException("Failed to read test data resource: "+ resourcePath, e);
        }
    }

    public String getValue(String scenario, String key){
        JsonNode scenarioNode = rootNode.path(scenario);
        JsonNode valueNode = scenarioNode.path(key);

        if(scenarioNode.isMissingNode() || valueNode.isMissingNode() || valueNode.isNull()){
            throw new IllegalArgumentException(
                    "Test data not found for scenario: "
                            + scenario + ", Key: "+key);
        }
        return valueNode.asText();
    }
}
