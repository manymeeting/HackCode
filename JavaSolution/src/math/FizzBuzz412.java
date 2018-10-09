package math;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Write a program that outputs the string representation of numbers from 1 to n.

 But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.

 Example:

 n = 15,

 Return:
 [
 "1",
 "2",
 "Fizz",
 "4",
 "Buzz",
 "Fizz",
 "7",
 "8",
 "Fizz",
 "Buzz",
 "11",
 "Fizz",
 "13",
 "14",
 "FizzBuzz"
 ]
 */
// 可以不用%，用两个counter，都从1开始，每次++，第一个遇到3出Fizz，第二个遇到5出Buzz，同时满足出FizzBuzz，
// 注意if的顺序，写成互斥的话，同时满足在前

public class FizzBuzz412 {
    public List<String> fizzBuzz(int n) {
        int fizz = 1, buzz = 1;
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++, fizz++, buzz++)
        {
            if(fizz == 3 && buzz == 5) {
                res.add("FizzBuzz");
                fizz = 0;
                buzz = 0;
            }
            else if(fizz == 3) {
                res.add("Fizz");
                fizz = 0;
            }
            else if(buzz == 5) {
                res.add("Buzz");
                buzz = 0;
            }
            else {
                res.add(String.valueOf(i));
            }

        }

        return res;
    }
}
