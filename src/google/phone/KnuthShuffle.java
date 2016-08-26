package google.phone;

/*
 * The Knuth class provides a client for reading in a 
 *  sequence of strings and <em>shuffling</em> them using the Knuth (or Fisher-Yates)
 *  shuffling algorithm. This algorithm guarantees to rearrange the
 *  elements in uniformly random order, under
 *  the assumption that Math.random() generates independent and
 *  uniformly distributed numbers between 0 and 1.
 */
public class KnuthShuffle {

	public void shuffle(int max) {
		int[] arr = new int[max];
		for(int i = 0; i < max; i++) {
			//choose index uniformly in [i,max-i]
			//Random random new Random();
			//int next = i + random.nextInt(max-i);
			int r = i + (int)(Math.random() * (max-i));
			int tmp  = arr[r];
			arr[r] = arr[i];
			arr[i] = tmp;
		}
	}
}
