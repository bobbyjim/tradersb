package org.json.simple.util;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.util.HashMap;

public class Mapper {
    private static JSONParser parser = new JSONParser();

    public static HashMap<String, Object> decode(String json) {
        try {
            return (HashMap<String, Object>) parser.parse(json);
        } catch (ParseException pe) {
            HashMap<String, Object> out = new HashMap<>();
            out.put("exception", pe.getMessage() + "\n" + pe.getStackTrace());
            return out;
        }
    }

    public static HashMap<String, Object> decode(BufferedReader reader) {
        try {
            return (HashMap<String, Object>) parser.parse(reader);
        } catch (Exception pe) {
            HashMap<String, Object> out = new HashMap<>();
            out.put("exception", pe.getMessage() + "\n" + pe.getStackTrace());
            return out;
        }
    }
}
