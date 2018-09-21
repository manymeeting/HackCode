package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

 Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

 Example 1:
 Input: flowerbed = [1,0,0,0,1], n = 1
 Output: True
 Example 2:
 Input: flowerbed = [1,0,0,0,1], n = 2
 Output: False
 Note:
 The input array won't violate no-adjacent-flowers rule.
 The input array size is in the range of [1, 20000].
 n is a non-negative integer which won't exceed the input array size.

 */

// 给flowerbed两端加0，然后获取所有slots，每个slots能放的花是(slotWidth-1)/2，最后加起来跟n比较

public class CanPlaceFlowers605 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int[] paddedFlowerBed = new int[flowerbed.length + 2];
        paddedFlowerBed[0] = 0; paddedFlowerBed[paddedFlowerBed.length-1] = 0;
        for (int i = 1; i < paddedFlowerBed.length-1; i++) paddedFlowerBed[i] = flowerbed[i-1];
        flowerbed = paddedFlowerBed;

        List<Integer> slots = new ArrayList<>();
        int currSlotWidth = 0;
        int idx = 0;
        while(idx < flowerbed.length) {
            if(flowerbed[idx] == 0)
            {
                while(flowerbed[idx] == 0) {
                    idx++;
                    currSlotWidth++;
                }
            }
            else {
                if(currSlotWidth > 0){
                    slots.add(currSlotWidth);
                    currSlotWidth = 0;
                }
                idx++;
            }

        }

        if(currSlotWidth > 0){
            slots.add(currSlotWidth);
        }

        int placedFlowers = 0;
        for (int i = 0; i < slots.size(); i++) {
            int slot = slots.get(i);
            placedFlowers += ((slot-1) / 2);

        }
        return placedFlowers >= n;
    }
}
