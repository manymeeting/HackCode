package arrays;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.

 Example:

 Input: 5
 Output:
 [
 [1],
 [1,1],
 [1,2,1],
 [1,3,3,1],
 [1,4,6,4,1]
 ]
 */

// 两层循环，row.add(triangle.get(i-1).get(j-1) + triangle.get(i-1).get(j));
public class PascalsTriangle118 {

    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> triangle = new ArrayList<List<Integer>>();

        if(numRows <= 0)
        {
            return triangle;
        }

        for( int i = 0; i < numRows; i++)
        {
            List<Integer> row = new ArrayList<>();
            for(int j = 0; j < i + 1; j++)
            {
                if(j == 0 || j == i)
                {
                    row.add(1);
                }
                else
                {
                    row.add(triangle.get(i-1).get(j-1) + triangle.get(i-1).get(j));
                }
            }
            triangle.add(row);
        }

        return triangle;
    }
}
