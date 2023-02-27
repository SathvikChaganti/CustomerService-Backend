package com.ivoyant.customerservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StateConversion {

    @Value ("#{${states}}")
    private Map<String, String> states;

    public String convertState(String state) {
        if (states.containsKey(state)) {
            return states.get(state);
        } else if (states.containsValue(state)) {
            return state;
        } else {
            return ("Invalid state provided: " + state);
        }
    }
}