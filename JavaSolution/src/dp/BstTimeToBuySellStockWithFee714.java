package dp;

/**
 * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.

 You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

 Return the maximum profit you can make.

 Example 1:
 Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
 Output: 8
 Explanation: The maximum profit can be achieved by:
 Buying at prices[0] = 1
 Selling at prices[3] = 8
 Buying at prices[4] = 4
 Selling at prices[5] = 9
 The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 Note:

 0 < prices.length <= 50000.
 0 < prices[i] < 50000.
 0 <= fee < 50000.
 */

// 详细分析：https://www.jianshu.com/p/9fa0faff99da
// 每个时间点有两种可能：hold或sell（当天股票在手中或不在手中），分别取
// sold = max（前一天sold，前一天hold并且今天sell）
// hold = max（前一天hold，前一天sell并且今天buy）

public class BstTimeToBuySellStockWithFee714 {
    public int maxProfit(int[] prices, int fee) {

        int hold = -prices[0]; // 初始化一个负值，代表不存在的状态
        int sold = 0;

        for(int i = 1; i < prices.length; i++) {
            sold = Math.max(sold, hold + prices[i] - fee);
            // 同一天可以进行多次交易，因此先算sold还是hold都可以
            hold = Math.max(hold, sold - prices[i]); // buy的时候不用交fee
        }

        return sold;
    }
}
