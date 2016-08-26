package ixllearning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//Take a list of candies that you have like this...
//var candies = [ "Reeses Peanut Butter Cup", "Laffy Taffy", "Snickers", "Candy Corn", "Reeses Peanut Butter Cup", "Candy Corn", "Candy Corn", "Snickers", "Black Licorice", "Apple", "Penny", "Penny"]
//A list of likes and dislikes.
//var likes = [ "Reeses Peanut Butter Cup", "Snickers"]
//var dislikes = ["Penny", "Candy Corn"].
//Write a class that can share candy based on your preferences.
//1. If someone wants a piece of candy, and you dislike it, you give
// all of that kind of candy. Remove all that candy from your list.
//2. If someone requests a piece of candy, and you neither like nor dislike it,
// give them the number of pieces of candy they requested. Remove the amount
// of candy from your list.
//3. If someone requests candies that you like, don't give them any.
public class Candy {
	public Map<String, Integer> likeMap = new HashMap<String, Integer>();
	public Map<String, Integer> dislikeMap = new HashMap<String, Integer>();
	public Map<String, Integer> neutralMap = new HashMap<String, Integer>();
	public Candy(List<String> candies, Set<String> likes, Set<String> dislikes) {
		for (String candy : candies) {
			if (likes.contains(candy)) {
				if (!likeMap.containsKey(candy)) {
					likeMap.put(candy, 1);
				} else likeMap.put(candy, likeMap.get(candy) + 1);
			} else if (dislikes.contains(candy)) {
				if (!dislikeMap.containsKey(candy)) {
					dislikeMap.put(candy, 1);
				} else dislikeMap.put(candy, dislikeMap.get(candy) + 1);
			} else {
				if (!neutralMap.containsKey(candy))
					neutralMap.put(candy, 1);
				else neutralMap.put(candy, neutralMap.get(candy) + 1);
			}
		}
	}
	public void updateCandyBag(String name, Candy target, int amount, String type) {
		if (type.equals("like")) return;
		int totalAmount = target.dislikeMap.get(name);
		if (type.equals("dislike")) {
			target.dislikeMap.remove(name);
		} else {
			if (totalAmount >= amount) {
				target.neutralMap.put(name, totalAmount - amount);
			} else {
				target.neutralMap.remove(name);
				amount = totalAmount;
			}
		}
		if(likeMap.containsKey(name)) {
			likeMap.put(name, likeMap.get(name) + amount);
		} else likeMap.put(name, totalAmount);
		if(dislikeMap.containsKey(name)) {
			dislikeMap.put(name, amount);
		} else dislikeMap.put(name, amount);
		if(neutralMap.containsKey(name)) {
			neutralMap.put(name, amount);
		} else neutralMap.put(name, amount);
	}
}
