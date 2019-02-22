package graphs;

/**
 * N couples sit in 2N seats arranged in a row and want to hold hands. We want to know the minimum number of swaps so that every couple is sitting side by side. A swap consists of choosing any two people, then they stand up and switch seats.

 The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in order, the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).

 The couples' initial seating is given by row[i] being the value of the person who is initially sitting in the i-th seat.

 Example 1:

 Input: row = [0, 2, 1, 3]
 Output: 1
 Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
 Example 2:

 Input: row = [3, 2, 0, 1]
 Output: 0
 Explanation: All couples are already seated side by side.
 Note:

 len(row) is even and in the range of [4, 60].
 row is guaranteed to be a permutation of 0...len(row)-1.
 */

//
// Union-Find 思路：
// 每次取两个数，计算其群组号，并调用find函数，
// 如果这两个数的群组号相同，那么find函数必然会返回同样的值，不用做什么额外动作，因为本身就是一对儿。
// 如果两个数不是一对儿，那么其群组号必然不同，此时将二者归为一组，并且cnt自减1 (cnt初始化为总群组数，即 n/2)
// 那么最终cnt减少的个数就是交换的步数

// 举例：
/**
 *
 * [3   1   4   0   2   5]

 最开始的群组关系是：

 群组0：0，1

 群组1：2，3

 群组2：4，5

 取出前两个数字3和1，其群组号分别为1和0，带入find函数返回不同值，则此时将群组0和群组1链接起来，变成一个群组，则此时只有两个群组了，cnt自减1，变为了2。

 群组0 & 1：0，1，2，3

 群组2：4，5

 此时取出4和0，其群组号分别为2和0，带入find函数返回不同值，则此时将群组0&1和群组2链接起来，变成一个超大群组，cnt自减1，变为了1。

 群组0 & 1 & 2：0，1，2，3，4，5

 此时取出最后两个数2和5，其群组号分别为1和2，因为此时都是一个大组内的了，带入find函数返回相同的值，不做任何处理。最终交换的步数就是cnt减少值，为2
 */

public class CouplesHoldingHands765 {

    private int count; // count of ideal number of couples
    private int[] con;
    public int minSwapsCouples(int[] row) {
        count = row.length / 2;
        con = new int[count];
        int N = count; // count will change, so N represents the original val
        for (int i = 0; i < N; i++) {
            con[i] = i;
        }

        for ( int i = 0; i < N; i++ ){
            int p1 = row[i * 2];
            int p2 = row[i * 2 + 1];

            union(p1 / 2, p2 / 2);
        }

        return N - count;


    }

    private int find(int i) {
        if(con[i] != i) {
            con[i] = find(con[i]);
            return con[i];
        }
        return con[i];
    }

    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX != rootY) {
            con[rootX] = rootY;
            count--; // Update connection number
        }

    }
}
