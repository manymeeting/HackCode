package arrays.prefixsum;

import java.util.Arrays;

/** 
 *  * 
You are given a 2D integer array tiles where tiles[i] = [li, ri] represents that every tile j in the range li <= j <= ri is colored white.

You are also given an integer carpetLen, the length of a single carpet that can be placed anywhere.

Return the maximum number of white tiles that can be covered by the carpet.

Input: tiles = [[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
Output: 9
Explanation: Place the carpet starting on tile 10. 
It covers 9 white tiles, so we return 9.
Note that there may be other places where the carpet covers 9 white tiles.
It can be shown that the carpet cannot cover more than 9 white tiles.


TODO：思路补充
 */
public class MaxWhiteTilesCovered2271 {
    
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        
        Arrays.sort(tiles, (a, b) -> a[0] - b[0]);

        int[] prefixSum = new int[tiles.length];
        prefixSum[0] = tiles[0][1] - tiles[0][0] + 1;
        for (int i = 1; i < tiles.length; i++) {
            prefixSum[i] = prefixSum[i-1] + tiles[i][1] - tiles[i][0] + 1;
        }


        int maxCovered = 0;
        for (int i = 0; i < tiles.length; i++) {
            int carpetEndPosition = tiles[i][0] + carpetLen - 1;
            int lastTileIndex = binSearchForClosetLessThanOrEqual(tiles, i, tiles.length - 1, carpetEndPosition);

            int lastTileCovered = 0;
            if (tiles[lastTileIndex][1] >= carpetEndPosition) {
                lastTileCovered = carpetEndPosition - tiles[lastTileIndex][0] + 1;
            }
            else {
                lastTileCovered = tiles[lastTileIndex][1] - tiles[lastTileIndex][0] + 1;
            }

            int currCovered = (lastTileIndex >= 1 ? prefixSum[lastTileIndex - 1] : 0) - (i >= 1 ? prefixSum[i-1] : 0) + lastTileCovered;
            maxCovered = Math.max(maxCovered, currCovered); 
        }

        return maxCovered;
    }

    private int binSearchForClosetLessThanOrEqual(int[][] tiles, int left, int right, int val) {
        int mid = 0;
        int res = left;
        while(left <= right) {
            mid = left + (right - left) / 2;
            if (tiles[mid][0] > val) {
                right = mid -1;
            }
            else {
                // 此处不可缺少，因为mid值可能会一直比target大，不能直接return mid。
                res = mid;
                left = mid + 1;
            }
        }

        return res;
    }
}
