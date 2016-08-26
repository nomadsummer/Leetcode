package ixllearning;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IdenticalStingSets {

	public boolean allStringSetsIdentical(String[][] sets) {
		
		List<Set<String>> list = new ArrayList<Set<String>>();
		
		if(sets == null || sets.length <= 1) return true;
		
		for(String[] strs : sets) {
			//deal with duplicates and uppercase
			Set<String> set = new HashSet<String>();
			for(String s : strs) {
				set.add(s.toLowerCase());
			}
			list.add(set);
		}
		
		for(int i = 1; i < list.size(); i++) {
			if(!list.get(0).equals(list.get(i))) return false;
		}	
		return true;
	}

	public static void main(String[] args) {
		String[][][] testSet = { null, {}, { {} }, { { "a" } }, { { "a" }, {} },
				{ { "a", "b" }, { "b", "a" }, { "a", "b", "a" } }, { { "a", "b" }, { "a" }, { "b" } },
				{ { "aab", "ABA", "Aab", "aba" }, { "aaB", "AbA" } } };

		boolean[] expectRst = { true, true, true, true, true, true, false, true };

		IdenticalStingSets identical = new IdenticalStingSets();
		for (int i = 0; i < testSet.length; i++) {
			boolean res = identical.allStringSetsIdentical(testSet[i]);
			System.out.println("Epected Result: " + expectRst[i] + ", Actual Result: " + res);
		}
	}
}
