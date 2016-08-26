package microsoft;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Point implements Comparable<Point> {
	int x;
	int y;
	double distance;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
		distance = Math.sqrt(x*x + y*y);
		
	}
	@Override
	public int compareTo(Point p) {
		
		return Double.valueOf(this.distance).compareTo(p.distance);
	}
}
public class FindKCloset {

	public static List<Point> findKCloset(List<Point> points, int k) {
		if(points == null || points.size() == 0) return null;
		if(points.size() <= k) return new ArrayList<Point>(points);
		
		List<Point> res = new ArrayList<Point>();
		
		PriorityQueue<Point> pq = new PriorityQueue<Point>(k);
		
		for(Point point : points) {
			if(pq.size() < k) {
				pq.offer(point);
			} else {
				if(pq.peek().compareTo(point) < 0) {
					pq.poll();
					pq.offer(point);
				} 
			}
		}
		while(!pq.isEmpty()) {
			res.add(pq.poll());
		}
		return res;
	}
	
	public static void main(String[] args) {
		List<Point> points = new ArrayList<Point>();
		
		Point p1 = new Point(1,5);
		points.add(p1);
		Point p2 = new Point(2,10);
		points.add(p2);
		Point p3 = new Point(2,5);
		points.add(p3);
		Point p4 = new Point(3,5);
		points.add(p4);
		
		List<Point> res = findKCloset(points, 3);
		for(Point p : res) {
			System.out.println(p.distance);
		}
		
	}
}
