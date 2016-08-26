package google.phone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * 打印接收messge 的log system。message 包含id， time 以及是否结束。 打印出来id，开始时间，结束时间。 
 * 用priorityqueue 做的。
 * 没有输入 . 打印要求按end time排序。 这个function包含一个listener method接收messege, 打印method被调用打印. 
 * 譬如 每分钟打印一次 。message可能有up to 1min的delay。所以打印时要考虑当前时间减去delay 时间
 */
public class LogSystem {

	class Message {
		private int id;
		private int start;
		private int end;
		private boolean isFinished;
		
		public Message(int id, int start, int end, boolean isFinished) {
			this.id = id;
		}
	}
	private PriorityQueue<Message> pq;
	Map<Integer,Message> map;
	public LogSystem(){
		pq = new PriorityQueue<Message>(new Comparator<Message>(){
			@Override
			public int compare(Message m1, Message m2) {
				if(m1.isFinished && m2.isFinished) {
					return m1.end-m2.end;
				} else if(m1.isFinished) return 1;
				else if(m2.isFinished) return -1;
				else return m1.start-m2.start;
			}
		});
		map = new HashMap<Integer, Message>();
	}
	
	public void listen(String message) {
		String[] strs = message.split(",");
		if(strs[2].equals("false")) {
			int id = Integer.parseInt(strs[0]);
			int time = Integer.parseInt(strs[1]);
			Message m = new Message(id, time, Integer.MAX_VALUE, false);
			pq.add(m);
			map.put(id, m);
		} else {
			Message m = map.get(Integer.parseInt(strs[0]));
			pq.remove(m);
			map.remove(m.id);
			m = new Message(m.id,m.start,Integer.parseInt(strs[1]), true);
			pq.add(m);
			map.put(m.id, m);
		}	
	}
	public void print(int time) {
		
		while(!pq.isEmpty() && pq.peek().isFinished && time > pq.peek().end + 60) {
			printValue(pq.peek());
		}
	}

	private void printValue(Message m) {
		// TODO Auto-generated method stub
		System.out.print("Message id: " + m.id + "is started at time: " + m.start + "and is end at: "+ m.end);;
	}
}
