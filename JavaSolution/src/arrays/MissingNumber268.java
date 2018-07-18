package arrays;

/**
 *
 Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
 find the one that is missing from the array.

 Example 1:

 Input: [3,0,1]
 Output: 2
 Example 2:

 Input: [9,6,4,2,3,5,7,0,1]
 Output: 8
 Note:
 Your algorithm should run in linear runtime complexity.
 Could you implement it using only constant extra space complexity?


 */
public class MissingNumber268 {

    // 把数字放到index的位置上
    // 注意要用while循环，这样swap之后还能在相同位置接着检查，直到满足index++的条件为止

    public int missingNumber(int[] nums) {

        int i = 0;
        while(i < nums.length)
        {
            if(i != nums[i] && nums[i] < nums.length)
            {
                swap(nums, i, nums[i]);
            }
            else
            {
                i++;
            }

        }

        int j = 0;
        while(j < nums.length)
        {
            if(j != nums[j])
            {
                return j;
            }
            j++;
        }


        return j;

    }

    public void swap(int[] nums, int a, int b)
    {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

}
