package ixllearning;

import java.util.HashMap;
import java.util.Map;

//Explanation: Instead of hard coding all the 50 states to create state select list,
//the following code store the state full name and its corresponding two-letter combination 
//in a 2d array (or more ideal way is to store in an external file, and populate STATES from the file) 
//in purpose of mapping. And construct a hash map to store relationship between state name and its abbreviation.

//Improvement:
//1. In createSelectStateList function, generate the HTML "SELECT" by iterating through STATES array and populating
//   values into a StringBuilder. And reuse the same control words (e.g. </option>) instead repeating 50 times.
//2. By constructing the map, the running time of parseSelectedState is O(1) because lookup in hash map takes constant time, instead of O(n) previously.
//3. Though displayStateFullName still takes O(n) because we need to iterate through the map, but this can be optimized to 
// O(1) easily by adding an extra hash map (extra space cost O(n)), where state abbreviation as key and state full name as it's value.
// If we know this function will be called multiple times, this preprocessing will reduce running time substantially.
//4 Generally, this rewrite improve readability, maintainability and performance.

public class StateUtils {
	
	//State full name and State abbreviation
	private static final String[][] STATES = {
			{"Alabama", "AL"},
			{"Alaska", "AK"},
			{"Arizona", "AZ"},
			{"Arkansas", "AR"},
			{"California", "CA"}
			//more states here for real version
			};
	
	public static Map<String, String> stateMap = new HashMap<String, String>();
	
	/*
	 * Build the stateMap, populate state full name as key, and state two-letter abbreviation as value
	 */
	public static void constructStateMap() {
		
		for(int i = 0; i < STATES.length; i++) {
			stateMap.put(STATES[i][0], STATES[i][1]);
		}
	}
	
	/*
	 * Generates an HTML select list that can be used to select a specific U.S. state.
	 */
	public static String createStateSelectList() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("<select name=\"state\">\n");
		for(int i = 0; i < STATES.length; i++) {
			sb.append("<option value=\"" + STATES[i][0] + "\">" + STATES[i][0] + "</option>\n");
		}
		sb.append("</select>\n");
		return sb.toString();
	}
	
	/*
	 * Parses the state from an HTML form submission, converting it to
	 * the two-letter abbreviation.
	 */
	public static String parseSelectedState(String s) {
		
		return stateMap.containsKey(s) ? stateMap.get(s) : null;
	}
	
	/*
	 * Displays the full name of the state specified by the two-letter code.
	 */
	public static String displayStateFullName(String abbr) {
	
		//Since the State full name and State abbreviation are in one to one relationship
		//we can return the first matched key of the abbreviation
		for(Map.Entry<String, String> entry : stateMap.entrySet()) {
			if(abbr.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		//if the input abbreviation is invalid, return null
		return null;
	}
	
	public static void main(String[] args) {
		constructStateMap();
		
		final String CALIFORNIA = "California";
		System.out.println("Expected Abbreviation: CA, Actual Abbreviation: " + parseSelectedState(CALIFORNIA));
		
		final String ALASKA = "Alaska";
		System.out.println("Expected Abbreviation: AK, Actual Abbreviation: " + parseSelectedState(ALASKA));
		
		final String CA = "CA";
		System.out.println("Expected State: California, Actual State: " + displayStateFullName(CA));
		
		final String AZ = "AZ";
		System.out.println("Expected State: Arizona, Actual State: " + displayStateFullName(AZ));
		
		final String UNKOWN= "UNKOWN";
		System.out.println(displayStateFullName(UNKOWN));
		System.out.println(parseSelectedState(UNKOWN));
	}
}
