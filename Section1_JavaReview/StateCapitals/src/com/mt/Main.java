package com.mt;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	    Map<String, String> stateCapitals = new HashMap();
        stateCapitals.put("Alabama", "Montgomery");
        stateCapitals.put("Alaska", "Juneau");
        stateCapitals.put("Arizona", "Phoenix");
        stateCapitals.put("Arkansas", "Little Rock");

        System.out.println("STATES:");
        for(String state: stateCapitals.keySet()){
            System.out.println(state);
        }

        System.out.println("\nCAPITALS:");
        for(String capital: stateCapitals.values()){
            System.out.println(capital);
        }
        System.out.println("\nSTATE/CAPITAL PAIRS:");
        for(String state: stateCapitals.keySet()){
            String capital = stateCapitals.get(state);
            System.out.println(state  + " - " + capital);
        }

    }
}
