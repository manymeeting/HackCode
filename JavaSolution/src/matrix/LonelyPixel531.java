package matrix;

/**
 * Given a picture consisting of black and white pixels, find the number of black lonely pixels.

 The picture is represented by a 2D char array consisting of 'B' and 'W',
 which means black and white pixels respectively.

 A black lonely pixel is character 'B' that located at a specific position
 where the same row and same column don't have any other black pixels.

 Example:
 Input:
 [['W', 'W', 'B'],
 ['W', 'B', 'W'],
 ['B', 'W', 'W']]

 Output: 3
 Explanation: All the three 'B's are black lonely pixels.
 Note:
 The range of width and height of the input 2D array is [1,500].
 */


//遍历行列两次，第一次用两个数组分别记录row和col的'B'个数，第二次遇到B以后去数组中看对应row和col的数字是不是1，是则结果+1
public class LonelyPixel531 {

    public int findLonelyPixel(char[][] picture) {
        int n = picture.length;
        int m = picture[0].length;

        int[] rowBlack = new int[n];
        int[] colBlack = new int[m];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if(picture[i][j] == 'B')
                {
                    rowBlack[i]++;
                    colBlack[j]++;
                }
            }
        }

        int lonelyPixelCount = 0;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if(picture[i][j] == 'B' && rowBlack[i] == 1 && colBlack[j] == 1)
                {
                    lonelyPixelCount++;
                }
            }
        }
        return lonelyPixelCount;
    }
}
