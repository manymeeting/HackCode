package math;


/**
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

 Note:

 The length of both num1 and num2 is < 5100.
 Both num1 and num2 contains only digits 0-9.
 Both num1 and num2 does not contain any leading zero.
 You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class AddStrings415 {
    public String addStrings(String num1, String num2) {

        int carry = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;

        char[] num1Arr = num1.toCharArray();
        char[] num2Arr = num2.toCharArray();
        StringBuilder sb = new StringBuilder();

        while(i >= 0 || j >= 0 || carry == 1)
        {
            int a = i >= 0 ? num1Arr[i--] - '0' : 0;
            int b = j >= 0 ? num2Arr[j--] - '0' : 0;

            int sum = a + b + carry;
            sb.insert(0, sum % 10);
            carry = sum / 10;
        }

        return sb.toString();
    }
}
