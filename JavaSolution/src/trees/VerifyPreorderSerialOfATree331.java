package trees;

/**
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:

Input: "9,3,4,#,#,1,#,#,2,#,6,#,#"
Output: true
Example 2:

Input: "1,#"
Output: false
Example 3:

Input: "9,#,#,1"
Output: false
 */

// 用数degree的方式来判断，初始设为1，每次处理一个node时degree-1，如果是非空node则degree+2，看最后degree是否为0
// 另外一旦degree变成负数则说明invalid

public class VerifyPreorderSerialOfATree331 {
    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        int degreeDiff = 1; // 空node总比非空node多1
        for (String node : nodes) {
            degreeDiff--;
            if(degreeDiff < 0) return false;

            if(!node.equals("#")) {
                degreeDiff+=2;
            }
        }

        return degreeDiff == 0;
    }
}
