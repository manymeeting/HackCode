package math;

/**
 * Count the number of prime numbers less than a non-negative number, n.

 Example:

 Input: 10
 Output: 4
 Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */


// 技巧：用一个boolean数组表示某index的数是否为素数，
// 遇到是素数时更新count，同时直接从2开始分别用每个数乘上当前数（乘积小于n），把相应的位置标为非素数

public class CountPrimes204 {
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {

            if(!notPrime[i]) {
                count++;
                for (int j = 2; j * i < n; j++) {
                    notPrime[j * i] = true;
                }
            }
        }

        return count;
    }
}
