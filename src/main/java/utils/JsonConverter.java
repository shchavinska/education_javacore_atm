package utils;

import java.io.IOException;
import java.nio.file.*;

import card.DataBase;
import com.fasterxml.jackson.core.util.*;
import com.fasterxml.jackson.databind.*;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;


public class JsonConverter {
    private final static Logger LOGGER = Logger.getLogger(JsonConverter.class);
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

    public void convertToJsonFile(Object obj, String pathToFile) {
        LOGGER.info("convertToJsonFile caused");
        try {
            writer.writeValue(Paths.get(pathToFile).toFile(), obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataBase readJsonToCard(String file) throws IOException {
        LOGGER.info("convertToJsonFile caused");
        String jsonStr = new String(Files.readAllBytes(Paths.get(file)));
        DataBase cards = null;
        try {
            cards  = mapper.readValue(jsonStr, DataBase.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return cards;
    }
}
