package hashtable;

import java.util.*;

public class CntDistinctIntAfterReverseOperations2442 {
    public int countDistinctIntegers(int[] nums) {
        HashSet<Integer> st = new HashSet<>();

        for (int i : nums)  {
            st.add(i);
            st.add(reverseInt(i));
        }
        return st.size();
    }

    private int reverseInt(int x) {
        int rev = 0;
        while (x > 0) {
            rev = rev * 10 + (x % 10);
            x = x / 10; 
        }
        return rev;
    }
    
}
