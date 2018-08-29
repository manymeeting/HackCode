package strings;

import java.util.*;

/**
 * Given a string s, return all the palindromic permutations (without duplicates) of it.
 * Return an empty list if no palindromic permutation could be form.

 Example 1:
 Input: "aabb"
 Output: ["abba", "baab"]

 Example 2:
 Input: "abc"
 Output: []
 */

//1. 先计算每个ch出现频率，用map来存，如果odd count大于1则不为palindrome
//2. 用swap+backtracking思路（类似于No.47）来获取permutation

public class PalindromePermutationII267 {
    public List<String> generatePalindromes(String s) {

        List<String> res = new ArrayList<>();
        List<Character> chList = new ArrayList<>();
        Map<Character, Integer> occur = new HashMap<>();
        int oddCnt = 0;
        for (int i = 0; i < s.length(); i++)
        {
            char ch = s.charAt(i);
            oddCnt += occur.containsKey(ch) ? -1 : 1;
            occur.put(ch, occur.getOrDefault(ch, 0) + 1);

        }
        if(oddCnt > 1)
        {
            return res;
        }

        // add half characters to a list
        String midCh = "";
        for (Map.Entry<Character, Integer> entry : occur.entrySet())
        {
            if(entry.getValue() % 2 == 1)
            {
                midCh = String.valueOf(entry.getKey());
            }

            for (int i = 0; i < entry.getValue() / 2; i++)
            {
                chList.add(entry.getKey());
            }
        }

        getPermutation(midCh, chList, res, 0);

        return res;

    }

    private void getPermutation(String mid, List<Character> chList, List<String> res, int start)
    {

        if(start == chList.size())
        {
            StringBuilder tempStr = new StringBuilder();
            for(Character ch : chList)
            {
                tempStr.append(ch);
            }
            res.add(tempStr.toString() + mid + tempStr.reverse().toString());
            return;
        }

        Set<Character> usedCh = new HashSet<>();
        for (int i = start; i < chList.size(); i++)
        {
            if(usedCh.add(chList.get(i)))
            {
                Collections.swap(chList, start, i);
                getPermutation(mid, chList, res, start+1);
                Collections.swap(chList, start, i);
            }

        }

    }

}
