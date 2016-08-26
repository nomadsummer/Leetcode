package microsoft;
import java.util.Comparator;
import java.util.PriorityQueue;

class Element {
	int[] arr;
	int index;
	//This Element indicates which position at which array
	 Element(int[] arr, int index) {
		this.arr = arr;
		this.index = index;
	}
}
public class Merge3Arrays {
	public static void merge(int[] a, int[] b, int c[], int[] output) {
		int lenA = a.length-1, lenB = b.length-1, lenC = c.length-1;
		int len = lenA + lenB + lenC;
		
		PriorityQueue<Element> pq = new PriorityQueue<Element>(len, new Comparator<Element>(){
			@Override
			public int compare(Element e1, Element e2) {
				return e1.arr[e1.index] - e2.arr[e2.index];
			}
		});
		
		//add elements into min heap
		if(a.length > 0) {
			pq.add(new Element(a, 0));
		}
		if(b.length > 0) {
			pq.add(new Element(b, 0));
		}
		if(c.length > 0) {
			pq.add(new Element(c, 0));
		}
		int pos = 0;
		while(!pq.isEmpty()) {
			Element curr = pq.poll();
			output[pos++] = curr.arr[curr.index];
			if(curr.index < curr.arr.length - 1) {
				pq.add(new Element(curr.arr, curr.index + 1));
			}
		}	
	}
	
	public static void main(String[] args) {
		int[] a = new int[]{1,5,21};
		int[] b = new int[]{2,4,7,9,11,15};
		int[] c = new int[]{6,8,12,20};
		int lenA = a.length, lenB = b.length, lenC = c.length;
		
		int[] output = new int[lenA + lenB + lenC];
		
		merge(a, b, c, output);
		for(int i : output) {
			System.out.println(i);
		}
		
	}
}
