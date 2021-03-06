package stack;

import java.util.*;

/**
Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price for the current day.

The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards) for which the price of the stock was less than or equal to today's price.

For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85], then the stock spans would be [1, 1, 1, 2, 1, 4, 6].

 

Example 1:

Input: ["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
Output: [null,1,1,1,2,1,4,6]
Explanation: 
First, S = StockSpanner() is initialized.  Then:
S.next(100) is called and returns 1,
S.next(80) is called and returns 1,
S.next(60) is called and returns 1,
S.next(70) is called and returns 2,
S.next(60) is called and returns 1,
S.next(75) is called and returns 4,
S.next(85) is called and returns 6.

Note that (for example) S.next(75) returned 4, because the last 4 prices
(including today's price of 75) were less than or equal to today's price.
 

Note:

Calls to StockSpanner.next(int price) will have 1 <= price <= 10^5.
There will be at most 10000 calls to StockSpanner.next per test case.
There will be at most 150000 calls to StockSpanner.next across all test cases.
The total time limit for this problem has been reduced by 75% for C++, and 50% for all other languages.

*/

// 如果只用1个stack，每次需要做linear的搜索，可以用另一个stack来记录”峰值“之间的span，
//遇到decreasing的数就开始从两个stack里pop，更新span，最后把新的span和price分别push进去

// 时间复杂度：O(n)，n是next被call的次数（每个元素最多会被pop和push一次）

class OnlineStockSpan901 {

	private ArrayDeque<Integer> prices;
	private ArrayDeque<Integer> spans;

    public OnlineStockSpan901() {
        prices = new ArrayDeque<>();
        spans = new ArrayDeque<>();
    }
    
    public int next(int price) {
                
        int span = 1; // 注意是从1开始
        while(!prices.isEmpty() && prices.peek() <= price) {
        	span += spans.pop();
        	prices.pop();
        }

        prices.push(price);
        spans.push(span);

        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
