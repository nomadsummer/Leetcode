package microsoft;

public class PermutationSequence {
	public String getPermutation(int n, int k) {

		int factor = 1;
		for (int i = 1; i < n; i++) {
			factor *= i;
		}
		k--;
		boolean[] used = new boolean[n];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int index = k / factor;
			k = k % factor;
			for (int j = 0; j < n; j++) {
				if (!used[j]) {
					if (index == 0) {
						used[j] = true;
						sb.append(String.valueOf(j + 1));
						break;
					} else {
						index--;
					}
				}
			}
			if (i < n - 1) {
				factor = factor / (n - 1 - i);
			}
		}
		return sb.toString();

	}
}