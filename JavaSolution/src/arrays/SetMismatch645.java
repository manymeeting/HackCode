package arrays;

public class SetMismatch645 {

    /**
     * The set S originally contains numbers from 1 to n. But unfortunately, due to the data error,
     * one of the numbers in the set got duplicated to another number in the set,
     * which results in repetition of one number and loss of another number.

     Given an array nums representing the data status of this set after the error.
     Your task is to firstly find the number occurs twice and then find the number that is missing.
     Return them in the form of an array.

     Example 1:
     Input: nums = [1,2,2,4]
     Output: [2,3]
     Note:
     The given array size will in the range [2, 10000].
     The given array's numbers won't have any order.

     */
    // 1 4 2 5 2
    // 1 2 4 5 2
    // 1 2 5 4 2
    // 1 2 2 4 5

    // index要从1开始，方便判断前后两个数是否重复，如果重复则直接记录为duplicate，不swap，否则会dead loop
    // 注意循环终止条件要相应改成 <= length
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];

        int i = 1;
        while(i <= nums.length)
        {
            if(nums[i - 1] != i)
            {
                int num = nums[i - 1];
                if(nums[num - 1] == nums[i - 1]) {
                    // found duplicate
                    res[0] = num;
                    i++;
                    continue;
                }
                swap(nums, i - 1 , num - 1);
            }
            else
            {
                i++;
            }
        }

        int j = 1;
        while(j <= nums.length)
        {
            if(nums[j - 1] != j)
            {
                res[1] = j;
                break;
            }

            j++;
        }
        return res;
    }

    public void swap(int[] nums, int a, int b)
    {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
