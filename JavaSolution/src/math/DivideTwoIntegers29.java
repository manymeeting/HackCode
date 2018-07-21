package math;


/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

 Return the quotient after dividing dividend by divisor.

 The integer division should truncate toward zero.

 Example 1:

 Input: dividend = 10, divisor = 3
 Output: 3
 Example 2:

 Input: dividend = 7, divisor = -3
 Output: -2
 Note:

 Both dividend and divisor will be 32-bit signed integers.
 The divisor will never be 0.
 Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
 For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.

 */
// 先用long来计算，最后根据sign来转换回int
// 计算时用divisor的2n次方来计算，加快速度
public class DivideTwoIntegers29 {

    public int divide(int dividend, int divisor) {

        int sign = 1;
        if((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
        {
            sign = -1;
        }

        long lresult = ldivider(Math.abs((long) dividend), Math.abs((long) divisor));

        // handle overflow
        int result = 0;
        if(lresult > Integer.MAX_VALUE)
        {
            result = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        else
        {
            result = (int) (sign * lresult);
        }

        return result;
    }


    public long ldivider(long dividend, long divisor) {

            // recursively take 2^x stride
            // Find the largest multiple so that (divisor * multiple <= dividend)

            if(dividend < divisor) return 0;

            long sum = divisor;

            long multiple = 1;

            while(sum+sum <= dividend)
            {
                sum += sum;
                multiple += multiple;
            }

            return multiple + ldivider(dividend - sum, divisor);

        /* Using simple loop will exceed time limit for very big number
        if(dividend < divisor)
        {
            return 0;
        }

        long quotient = 0;
        while(dividend >= divisor)
        {
            dividend -= divisor;
            quotient++;
        }

        return quotient;
        */
    }

}
