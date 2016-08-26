package google.phone;

/*
 * 设计查询系统(最大值，最小值，最新加入值)
class System {
  int getMax();
  int getMin()
  int getRecent()
  
  void add(long time, int price)
  void update(long time, int price)
  void remove(long time);      
}

例子如下
add(1,4) max:4, min:4, recent:4
add(4,7) max:7, min:4, recent:7

add(2,5) max:7, min:4, recent:7
 */
//class QuerySystem { // assume time being used is unique
//
//    int getMax() {
//        return priceIndex_.rbegin()->first;
//    }
//    int getMin() {
//        return priceIndex_.begin()->first;
//    }
//    int getRecent() {
//        return timeIndex_.rbegin()->second->second;
//    }
//    
//    void printCurrentStat() {
//        printf("max: %d\t min: %d\t recent: %d\n", getMax(), getMin(), getRecent());
//    }.
//    
//    void add(long time, int price) {
//        // assume the time being added here is a distinct value
//        const auto& it = list_.emplace(list_.end(), time, price);
//        timeIndex_[time] = it;
//        priceIndex_[price] += 1;
//    }
//   
//    void update(long time, int price) {
//        // assume the time being updated here exists
//        auto& it = timeIndex_[time];
//        auto& oldPrice = it->second;
//        if (--priceIndex_[oldPrice] == 0) {
//            priceIndex_.erase(oldPrice);
//        }
//        oldPrice = price;
//        priceIndex_[oldPrice] += 1;
//    }
//    
//    void remove(long time) {
//        // assume the time being updated here exists
//        auto& it = timeIndex_[time];
//        int price = it->second;
//        list_.erase(it);
//        timeIndex_.erase(time);
//        if (--priceIndex_[price] == 0) {
//            priceIndex_.erase(price);
//        }
//    }
//
//private:
//    list<pair<long, int>> list_;
//    map<long, list<pair<long, int>>::iterator> timeIndex_;
//    map<int, int> priceIndex_;
//};
//最简单粗暴的做法就是可以这么来设计，底层storage可以用doubly linked list（remove的时候可以O(1)），存的是time和value的pair。然后用两个map对time field和value field分别建index，这样所有mutation操作都能bound在O(logn)，所有get操作都是O(1)
//代码随便写了写，每个方法都写上了简化实现的假设，maybe有可能有corner case，但是这个是我觉得最容易直观的思考方向了
