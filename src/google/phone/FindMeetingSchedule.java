package google.phone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FindMeetingSchedule {
	class Interval {
		int start;
		int end;
		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	public int findMeetingTime(List<Interval> intervals1, List<Interval> intervals2, int time) {
		intervals1 = mergeInterval(intervals1);
		intervals2 = mergeInterval(intervals2);
		List<Interval> newIntervals = new ArrayList<Interval>(intervals1);
		for(Interval it : intervals2) {
			newIntervals.add(it);
		}
		Collections.sort(newIntervals, new Comparator<Interval>(){
			@Override
			public int compare(Interval i1, Interval i2) {
				if(i1.start != i2.start) {
					return i1.start - i2.start;
				} else return i1.end - i2.end;
			}
		});
		
		newIntervals = mergeInterval(newIntervals);
		Interval pre = newIntervals.get(0);
		for(int i = 1; i < newIntervals.size(); i++) {
			Interval curr = newIntervals.get(i);
			if(curr.start - pre.end >= time) {
				return pre.end;
			}
			pre = curr;
		}
		return pre.end;
		
		
	}
	public List<Interval> mergeInterval(List<Interval> intervals) {
		List<Interval> rst = new ArrayList<Interval>();
		if(intervals.size() == 0) return rst;
		
		Interval pre = intervals.get(0);
		for(int i = 1; i < intervals.size(); i++) {
			Interval curr = intervals.get(i);
			if(pre.end < curr.start) {
				rst.add(pre);
				pre = curr;
			} else {//overlapped, need to merge
				pre.end = Math.max(pre.end, curr.end);
			}
		}
		rst.add(pre);
		return rst;
	}
	public int findMeetingTime2(Interval[] intervals1, Interval[] intervals2, int time) {
		List<Interval> newIntervals = new ArrayList<Interval>();
		int i = 0, j = 0;
		//merge two intervals
		while(i < intervals1.length && j < intervals2.length) {
			if(intervals1[i].start < intervals2[j].start) {
				newIntervals.add(intervals1[i++]);
			} else if(intervals1[i].start >= intervals2[j].start){
				newIntervals.add(intervals2[j++]);
			}
		}
		while(i < intervals1.length) {
			newIntervals.add(intervals1[i++]);
		}
		while(j < intervals2.length) {
			newIntervals.add(intervals2[j++]);
		}
		
		List<Interval> merged = mergeInterval(newIntervals);
		Interval pre = merged.get(0);
		for(int k = 1; k < merged.size(); i++) {
			Interval curr = merged.get(k);
			if(curr.start - pre.end >= time){
				return pre.end;
			}
			pre = curr;
		}
		return pre.end;
	}
}
