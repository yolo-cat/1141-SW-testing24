package medal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MedalReader {
    public static void main(String[] args) {
        try {
            // Load the JSON file
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File("src/main/resources/medals.json"));

            // Initialize list to store medal information
            List<Medal> medalsList = new ArrayList<>();

            // Loop through each medal entry
            for (JsonNode node : rootNode.get("medals")) {
                String[] parts = node.asText().split(" ");
                String country = parts[0].replace("-", " "); // replace hyphen with space in country names
                int gold = Integer.parseInt(parts[1]);
                int silver = Integer.parseInt(parts[2]);
                int bronze = Integer.parseInt(parts[3]);

                // Create and add the Medal object to the list
                Medal medal = new Medal(country, gold, silver, bronze);
                medalsList.add(medal);
            }

            // Output the parsed results
            for (Medal medal : medalsList) {
                System.out.println(medal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
