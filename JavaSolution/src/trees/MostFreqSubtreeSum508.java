package trees;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

 Examples 1
 Input:

 5
 /  \
 2   -3
 return [2, -3, 4], since all the values happen only once, return all of them in any order.
 Examples 2
 Input:

 5
 /  \
 2   -5
 return [2], since 2 happens twice, however -5 only occur once.
 Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 */


// 遍历tree，每个递归path维护一个sum，不断更新全局的count map，最后遍历map找到目标sum

public class MostFreqSubtreeSum508 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    Map<Integer, Integer> sumCount;
    public int[] findFrequentTreeSum(TreeNode root) {
        if(root == null) return new int[0]; // 注意null情况单独处理，避免后面操作countEntryList会出NPE

        sumCount = new HashMap<>();
        calcSum(root);

        // sum5, 2 sum4, 3
        List<Map.Entry<Integer, Integer>> countEntryList = sumCount.entrySet().stream().sorted((a, b) -> {
            return b.getValue() - a.getValue();
        }).collect(Collectors.toList());

        List<Integer> res = new ArrayList<>();
        int maxCount = countEntryList.get(0).getValue();
        for (Map.Entry<Integer, Integer> entry : countEntryList) {
            if(entry.getValue() < maxCount) break;
            res.add(entry.getKey());
        }

        int[] resArr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) resArr[i] = res.get(i);

        return resArr;
    }

    private int calcSum(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int left = calcSum(root.left);
        int right = calcSum(root.right);
        int sum = left + right + root.val;

        sumCount.put(sum, sumCount.getOrDefault(sum, 0) + 1);
        return sum;
    }
}
