package pocketGems;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PathFinder {
    public static void main(String[] args)
            throws FileNotFoundException, IOException {
        String filename = "input_1.txt";
        if (args.length > 0) {
        	filename = args[0];
        }
        
        List<String> answer = parseFile(filename);
        System.out.println(answer);
    }
    
    static List<String> parseFile(String filename)
    		throws FileNotFoundException, IOException {
    	/*
    	 *  Don't modify this function
    	 */
        BufferedReader input = new BufferedReader(new FileReader(filename));
        List<String> allLines = new ArrayList<String>();
        String line;
        while ((line = input.readLine()) != null) {
        	allLines.add(line);
        }
        input.close();

        return parseLines(allLines);    	
    }
    
    static List<String> parseLines(List<String> lines) {
    	/*
    	 * 
    	 *  Your code goes here
    	 *  
    	 */
        Map<String, List<String>> map = new HashMap<String, List<String>>();

        String[] startEnd = lines.get(0).split(" ");
        String start = startEnd[0], end = startEnd[1];

        for(int i = 1; i < lines.size(); i++) {
            String[] strs = lines.get(i).split(":");
            String vertex = strs[0];
            String[] nodes = strs[1].split(" ");
            List<String> neighbors;
            for(String node : nodes) {
                if(map.containsKey(vertex)) {
                    neighbors = map.get(vertex);
                    neighbors.add(node);
                    map.put(vertex, neighbors);
                } else {
                    neighbors = new ArrayList<String>();
                    neighbors.add(node);
                    map.put(vertex, neighbors);
                }
            }
        }
        LinkedList<String> visited = new LinkedList<String>();
        visited.add(start);
        
        List<String> paths = new ArrayList<String>();
        pathFindHelper(map, paths, visited, end);
        return paths;
    }
    
    private static void pathFindHelper(Map<String, List<String>> map, List<String> paths, LinkedList<String> visited, String end) {
    	if(!map.containsKey(visited.getLast())) return;
    	
    	List<String> neighbors = map.get(visited.getLast());
    	for(String neighbor : neighbors) {
    		if(visited.contains(neighbor)) continue;
    		else if(end.equals(neighbor)) {
    			visited.add(neighbor);
    			String path = "";
    			for(String visit : visited) {
    				path += visit;
    			}
    			paths.add(path);
    			return;
    		} else {
    			visited.addLast(neighbor);
    			pathFindHelper(map, paths, visited, end);
    			visited.removeLast();
    		}
    	}
    }
}
