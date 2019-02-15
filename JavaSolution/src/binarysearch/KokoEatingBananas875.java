package binarysearch;

/**
 * Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.  The guards have gone and will come back in H hours.

 Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas, and eats K bananas from that pile.  If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.

 Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.

 Return the minimum integer K such that she can eat all the bananas within H hours.



 Example 1:

 Input: piles = [3,6,7,11], H = 8
 Output: 4
 Example 2:

 Input: piles = [30,11,23,4,20], H = 5
 Output: 30
 Example 3:

 Input: piles = [30,11,23,4,20], H = 6
 Output: 23


 Note:

 1 <= piles.length <= 10^4
 piles.length <= H <= 10^9
 1 <= piles[i] <= 10^9
 */

// 问题可以转换为，在1和piles里最大的pile之间，找一个最小的满足条件的数，二分法判断+找出目标数字

public class KokoEatingBananas875 {
    public int minEatingSpeed(int[] piles, int H) {
        int low = 1; int high = 0;
        for (int pile : piles) high = Math.max(pile, high);


        while(low < high) {
            int mid = low + (high - low) / 2;
            if(canEatAllPiles(H, mid, piles)) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }


        return low;

    }

    private boolean canEatAllPiles(int H, int K, int[] piles) {
        int h = 0;
        for (int pile : piles) {
            h += pile / K;
            if (pile % K != 0) h++;
        }

        return h <= H;
    }
}
