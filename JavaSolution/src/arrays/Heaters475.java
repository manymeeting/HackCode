package arrays;

import java.util.TreeSet;

/**
 * Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

 Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

 So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.

 Note:
 Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
 Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
 As long as a house is in the heaters' warm radius range, it can be warmed.
 All the heaters follow your radius standard and the warm radius will the same.
 Example 1:
 Input: [1,2,3],[2]
 Output: 1
 Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
 Example 2:
 Input: [1,2,3,4],[1,4]
 Output: 1
 Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
 */

// TreeSet的使用，把所有heater位置放到treeSet里，
// 用ceiling()和floor()来取得一个值的左右相邻值，与house位置比较，更新minRadius

public class Heaters475 {
    public int findRadius(int[] houses, int[] heaters) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int heater : heaters) {
            treeSet.add(heater);
        }

        int minRadius = 0;
        for (int house : houses) {
            Integer ceiling = treeSet.ceiling(house);
            Integer floor = treeSet.floor(house);

            // 根据ceiling和floor来更新minRadius，注意先检查null，同时用Math.max来更新
            if(ceiling == null) {
                minRadius = Math.max(minRadius, house - floor);

            }
            else if(floor == null) {
                minRadius = Math.max(minRadius, ceiling - house);
            }
            else {
                minRadius = Math.max(minRadius, Math.min(ceiling - house, house - floor));
            }
        }

        return minRadius;
    }
}
