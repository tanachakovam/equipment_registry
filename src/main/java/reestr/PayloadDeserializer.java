package reestr;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import reestr.model.*;

import java.io.IOException;

public class PayloadDeserializer extends StdDeserializer<ModelDetails> {
    protected PayloadDeserializer() {
        this(null);
    }

    protected PayloadDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ModelDetails deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        final JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String name = node.get("name").asText();
        String colour = node.get("colour").asText();
        Double size = node.get("size").asDouble();
        Double price = node.get("price").asDouble();
        Boolean isAvailable = node.get("isAvailable").asBoolean();

        ModelDetails model = null;

        if (node.has("doors")) {
            Integer doorCount = (Integer) node.get("doors").numberValue();
            String compressor = node.get("compressor").asText();

            model = new Refrigerator(name, colour, size, price, isAvailable, doorCount, compressor);

        } else if (node.has("memory")) {
            Integer memory = (Integer) node.get("memory").numberValue();
            Integer cameraCount = (Integer) node.get("cameraCount").numberValue();

            model = new Smartphone(name, colour, size, price, isAvailable, memory, cameraCount);
        } else if (node.has("processor")) {
            String category = node.get("category").asText();
            String processor = node.get("processor").asText();

            model = new PC(name, colour, size, price, isAvailable, category, processor);
        } else if (node.has("collectorVolume")) {
            Double collectorVolume = node.get("collectorVolume").asDouble();
            Integer regimeCount = (Integer) node.get("regimeCount").numberValue();

            model = new VacuumCleaner(name, colour, size, price, isAvailable, collectorVolume, regimeCount);
        } else if (node.has("technology")) {
            String technology = node.get("technology").asText();
            String category = node.get("category").asText();

            model = new TV(name, colour, size, price, isAvailable, technology, category);
        }
        return model;
    }
}
