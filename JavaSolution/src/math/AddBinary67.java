package math;

/**
 *
 Given two binary strings, return their sum (also a binary string).

 The input strings are both non-empty and contains only characters 1 or 0.

 Example 1:

 Input: a = "11", b = "1"
 Output: "100"
 Example 2:

 Input: a = "1010", b = "1011"
 Output: "10101"
 */


// 从最右端开始，用carry和sum来处理，每轮循环往StringBuilder里加一个digit，注意最后sb的顺序是反的
public class AddBinary67 {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;


        int sum = 0, carry = 0;

        // 用while来简化下标检查
        while(i >= 0 || j >= 0) {
            sum = carry;
            if(i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
            if(j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }
            sb.append(sum % 2);
            carry = sum / 2;
        }

        // 最后check一下carry是否为0
        if(carry != 0) sb.append(carry);
        // 要注意这时sb的顺序是反的
        return sb.reverse().toString();
    }
}
