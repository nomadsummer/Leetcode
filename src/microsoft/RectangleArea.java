package microsoft;

public class RectangleArea {

	public int rectangleArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		
		int areaA = (D-B) * (C-A);
		int areaB = (G-E) * (H-F);
		int height = 0, width = 0;
		if(E >= C || G <= A || F >= D || H <= B) {
			//if overlapping area
			return areaA + areaB;
		} else {
		    width = Math.min(C, G) - Math.max(A,E);
		    height = Math.min(D,H) - Math.max(B,F);
			int area = height * width;
			return areaA + areaB - area;
		}
	}
}
