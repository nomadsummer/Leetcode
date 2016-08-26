package google.phone;

import java.util.HashMap;
import java.util.Map;

public class DataStream {
/*
 * Design a interface for find the running mode in a stream of values:
   随时返回most frequent word
   先写函数get();add(String input);
   再问怎么实现这个两个函数：先说了Heap, get()-->O(1),add()--O(n) 
   然后她说优化add(),我说用HashMap+ counter，这样get()-->O(1),add()--->O(1)
 */
  Map<String, Integer> map = new HashMap<String, Integer>();
  int frequent = 0;
  String max = "";
  
  public void add(String input) {
	  if(!map.containsKey(input)) {
		  map.put(input, 1);
	  } else map.put(input, map.get(input) + 1);
	  
	  if(map.get(input) > frequent) {
		  frequent = map.get(input);
		  max = input;
	  }
  }
  public String getMostFrequent() {
	  return max;
  }
}
