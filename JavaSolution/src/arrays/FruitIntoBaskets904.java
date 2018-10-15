package arrays;

import java.util.Arrays;

/**
 * In a row of trees, the i-th tree produces fruit with type tree[i].

 You start at any tree of your choice, then repeatedly perform the following steps:

 Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
 Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
 Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.

 You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.

 What is the total amount of fruit you can collect with this procedure?



 Example 1:

 Input: [1,2,1]
 Output: 3
 Explanation: We can collect [1,2,1].
 Example 2:

 Input: [0,1,2,2]
 Output: 3
 Explanation: We can collect [1,2,2].
 If we started at the first tree, we would only collect [0, 1].
 Example 3:

 Input: [1,2,3,2,2]
 Output: 4
 Explanation: We can collect [2,3,2,2].
 If we started at the first tree, we would only collect [1, 2].
 Example 4:

 Input: [3,3,3,1,2,1,1,2,3,3,4]
 Output: 5
 Explanation: We can collect [1,2,1,1,2].
 If we started at the first tree or the eighth tree, we would only collect 4 fruits.


 Note:

 1 <= tree.length <= 40000
 0 <= tree[i] < tree.length
 */

// 用一个basket来记录最后出现的两个不同元素，保持位置关系和最近出现次序一致，
// 每次遇到不再basket里的元素就更新basket，计算长度

public class FruitIntoBaskets904 {
    public int totalFruit(int[] tree) {

        if (tree.length <= 2) {
            return tree.length;
        }


        int[] basket = new int[2];
        Arrays.fill(basket, -1);

        int start1 = 0;
        int start2 = 1;

        // 初始化basket，直到两个元素不相等
        while(start2 < tree.length && tree[start1] == tree[start2]) {
            start2++;
        }
        basket[0] = tree[start1];
        basket[1] = start2 >= tree.length ? basket[0] : tree[start2];  // 注意可能出现越界，要先检查

        int max = start2;

        for (int i = start2; i < tree.length; i++) {
            int curr = tree[i];
            if(curr != basket[0] && curr!= basket[1]) { // 遇到不在basket中的元素

                max = Math.max(max, i - start1);

                // 找到basket[1]中最近连续出现的起始位置（比如2 3 3 3 3 4，找第一个3的位置）
                int temp = i;
                while(temp > start1 && tree[temp - 1] == basket[1]) {
                    temp--;
                }
                start1 = temp;

                basket[0] = basket[1];
                basket[1] = curr;
            }
            else {
                // 保持basket中的位置关系和元素最后出现顺序关系一致
                if(basket[0] == curr) {
                    basket[0] = basket[1];
                    basket[1] = curr;
                }
            }

        }

        // 最后再计算一次剩余部分的长度，和max比较
        return Math.max(max, tree.length - start1);
    }
}
