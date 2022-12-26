/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author anmon
 */
public class jsonConversor {

    public static Map<String, ArrayList<Long>> getRequirements(String requirements) throws JsonProcessingException {
        Map<String, ArrayList<Long>> result = new ObjectMapper().readValue(requirements, HashMap.class);
        return result;
    }
}
