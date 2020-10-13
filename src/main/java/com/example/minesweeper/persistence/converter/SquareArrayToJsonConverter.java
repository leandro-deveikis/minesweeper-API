package com.example.minesweeper.persistence.converter;

import com.example.minesweeper.domain.Square;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.AttributeConverter;

/**
 * Used to save the grid (Square[][]) as a json in the database
 */
public class SquareArrayToJsonConverter implements AttributeConverter<Square[][], String> {
    private final static ObjectMapper objectMapper = new ObjectMapper();
    private static final Log LOGGER = LogFactory.getLog(SquareArrayToJsonConverter.class);

    @Override
    public String convertToDatabaseColumn(Square[][] meta) {
        try {
            return objectMapper.writeValueAsString(meta);
        } catch (Exception ex) {
            LOGGER.error("Error converting to json value: " + ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public Square[][] convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Square[][].class);
        } catch (Exception ex) {
            LOGGER.error("Error reading json value: " + ex.getMessage(), ex);
            return null;
        }
    }
}
