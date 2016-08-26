package google.phone;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Stock  {
	class StockPrice implements Comparable<StockPrice>{
		long timeStamp;
		double price;

		StockPrice(double price, long timeStamp) {
			this.price = price;
			this.timeStamp = timeStamp;
		}

		@Override
		public int compareTo(StockPrice o) {
			// TODO Auto-generated method stub
			if(this.price == o.price) {
				return (int) (this.timeStamp-o.timeStamp);
			}else return (int) (this.price - o.price);
		}
	}
	/*
	 * onUpdate(timestamp, price) 和 onCorrect(temistamp, price). 
	 * 可以理解为有一个时间流，每一个timestamp都对应一个股票的时间，每次调用一次onUpdate的时候，
	 * 就对我们设计的那个类更新对应的timestamp和price， onCorrect就是修改之前的一个timestamp的price。
	 * 最后，我们的类要能返回latest price， max price 和 min price
	 * 
	 */
	Map<Long, Double> timeToPriceMap = new HashMap<Long, Double>();
	SortedSet<StockPrice> sortedPrices = new TreeSet<StockPrice>();
	
	public void add(long timestamp, double price) {
		timeToPriceMap.put(timestamp, price);
		sortedPrices.add(new StockPrice(price, timestamp));
	}
	public void update(long timestamp, double price) {
		StockPrice previous = new StockPrice(timeToPriceMap.get(timestamp),timestamp);
		sortedPrices.remove(previous);
		sortedPrices.add(new StockPrice(price,timestamp));
		timeToPriceMap.put(timestamp,price);
	}
	public double getMax() {
		return sortedPrices.last().price;
	}
	public double getMin() {
		return sortedPrices.first().price;
	}
	
	/*
	 * 一个stock provider, 每一个second会发送stock prices.(考虑到reliability，不是每秒钟都发)，
	 * 写一个getAverage 函数(public double getAverage(double price, long timestamp)),
	 * 返回不早于系统当前时间一分钟前的所有股票价格的平均值
	 */
	Deque<StockPrice> deque = new ArrayDeque<StockPrice>();
	double sum = 0;
	public double getAverage(double price, long timestamp) {
		while(!deque.isEmpty() && timestamp > deque.peekFirst().timeStamp + 60) {
			sum -= deque.peekFirst().price;
			deque.pollFirst();
		}
		sum += price;
		deque.add(new StockPrice(price,timestamp));
		return sum/deque.size();
	} 
	/*
	 * 个股票的stream，每个时间点(timeline)都有股票的更新，实现两个function： 1.给一个timeline，删掉当前点的股票
	 * 2.给你一个timeline让你求出历史股票数据的最大值和最小值
	 */

	SortedSet<StockPrice> sortedPrice = new TreeSet<>();
	Map<Long, StockPrice> prices = new HashMap<Long, StockPrice>();
	double maxPrice = -1;
	double minPrice = Double.MAX_VALUE;

	// O(logn)
	public void deleteStockPrice2(int time) {
        StockPrice sp = prices.get(time);
        sortedPrice.remove(prices.get(time));
        if(sortedPrice.isEmpty())
        {
            maxPrice = -1;
            minPrice = Integer.MAX_VALUE;
        }

        if(sp.price == maxPrice)
            maxPrice = sortedPrice.last().price;
            minPrice = sortedPrice.first().price;
    }

	// O(logn)
	public void addStockPrice2(StockPrice sp){
        prices.put(sp.timeStamp, sp);
        sortedPrice.add(sp);
        if(maxPrice < sp.price)
            maxPrice = sp.price;
        if(minPrice > sp.price)
            minPrice = sp.price;
    }

	// O(1)
	public double getMax2() {
		return maxPrice;
	}

	// O(1)
	public double getMin2() {
		return minPrice;
	}

}
