package greedy;

import java.util.Arrays;

/**
 * You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:

numberOfBoxesi is the number of boxes of type i.
numberOfUnitsPerBoxi is the number of units in each box of the type i.
You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck. You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.

Return the maximum total number of units that can be put on the truck.

 

Example 1:

Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
Output: 8
Explanation: There are:
- 1 box of the first type that contains 3 units.
- 2 boxes of the second type that contain 2 units each.
- 3 boxes of the third type that contain 1 unit each.
You can take all the boxes of the first and second types, and one box of the third type.
The total number of units will be = (1 * 3) + (2 * 2) + (1 * 1) = 8.


思路：比较简单，直接按照box unit排序，然后放满可用的size即可。
 */
public class MaxUnitsOnTruck1710 {
    public int maximumUnits(int[][] boxTypes, int truckSize) {

        // Sort all box types by number of units
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        
        // Fill the truck from boxes with the highest number of units
        int maxUnits = 0;
        for (int i = 0; i < boxTypes.length; i++) {
            int[] currentBoxMeta = boxTypes[i];
            int numOfBoxToPut = Math.min(truckSize, currentBoxMeta[0]);
            
            maxUnits += numOfBoxToPut * currentBoxMeta[1];
            truckSize -= numOfBoxToPut;
            if (truckSize == 0) {
                return maxUnits;
            }
        }        
        return maxUnits;
    }
    
}
