package datastruct.treemap;

import java.util.*;


/**
 * 
 * You are given a stream of records about a particular stock. Each record contains a timestamp and the corresponding price of the stock at that timestamp.

Unfortunately due to the volatile nature of the stock market, the records do not come in order. Even worse, some records may be incorrect. Another record with the same timestamp may appear later in the stream correcting the price of the previous wrong record.

Design an algorithm that:

Updates the price of the stock at a particular timestamp, correcting the price from any previous records at the timestamp.
Finds the latest price of the stock based on the current records. The latest price is the price at the latest timestamp recorded.
Finds the maximum price the stock has been based on the current records.
Finds the minimum price the stock has been based on the current records.
Implement the StockPrice class:

StockPrice() Initializes the object with no price records.
void update(int timestamp, int price) Updates the price of the stock at the given timestamp.
int current() Returns the latest price of the stock.
int maximum() Returns the maximum price of the stock.
int minimum() Returns the minimum price of the stock.


思路：难点在于可以改之前的值，而且要随时更新全局当前的max（可能出现在之前任何时刻），所以想到需要一个一直维持排序的数据结构。
用一个treemap来记录反向的price -> times的关系，这样可以做到
update -> logN
max -> 1
min -> 1
curr -> 1
 */
public class StockPriceFluctuation2034 {
    int currTimestamp;
    TreeMap<Integer, Set<Integer>> priceTime;
    Map<Integer, Integer> timePrice;
 
    public StockPriceFluctuation2034() {
        currTimestamp = 0;
        priceTime = new TreeMap<>();
        timePrice = new HashMap<>();
    }
    
    public void update(int timestamp, int price) {
        
        if (timePrice.containsKey(timestamp)) {
            // Update price
            int oldPrice = timePrice.get(timestamp);
            if (oldPrice != price) {
                timePrice.put(timestamp, price);
                priceTime.get(oldPrice).remove(timestamp);
                if (priceTime.get(oldPrice).isEmpty()) {
                    priceTime.remove(oldPrice);
                }

                Set<Integer> times = priceTime.getOrDefault(price, new HashSet<>());
                times.add(timestamp);
                priceTime.put(price, times);
            }
        } else {
            timePrice.put(timestamp, price);
            Set<Integer> times = priceTime.getOrDefault(price, new HashSet<>());
            times.add(timestamp);
            priceTime.put(price, times);
        }
        
        currTimestamp = Math.max(currTimestamp, timestamp);
    }
    
    public int current() {
        return timePrice.get(currTimestamp);
    }
    
    public int maximum() {
        return priceTime.lastKey();
    }
    
    public int minimum() {
        return priceTime.firstKey();
    }
}
