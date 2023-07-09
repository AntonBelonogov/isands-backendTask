package ru.isands.BackendTask.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.isands.BackendTask.exception.EntityNotValidException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Converter(autoApply = true)
public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        String attributeInfoJson = null;
        try {
            attributeInfoJson = objectMapper.writeValueAsString(attribute);
        } catch (final JsonProcessingException e) {
            throw new EntityNotValidException("Json not valid.");
        }

        return attributeInfoJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        Map<String, Object> attributeInfo = null;
        try {
            attributeInfo = objectMapper.readValue(dbData, new TypeReference<HashMap<String, Object>>() {});
        } catch (final IOException e) {
            throw new EntityNotValidException("String not valid.");
        }

        return attributeInfo;
    }
}
