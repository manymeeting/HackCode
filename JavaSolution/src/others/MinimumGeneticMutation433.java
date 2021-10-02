package others;

import java.util.HashSet;
import java.util.Set;

/**
 * A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".

 Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.

 For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.

 Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.

 Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.

 Note:

 Starting point is assumed to be valid, so it might not be included in the bank.
 If multiple mutations are needed, all mutations during in the sequence must be valid.
 You may assume start and end string is not the same.


 Example 1:

 start: "AACCGGTT"
 end:   "AACCGGTA"
 bank: ["AACCGGTA"]

 return: 1


 Example 2:

 start: "AACCGGTT"
 end:   "AAACGGTA"
 bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

 return: 2


 Example 3:

 start: "AAAAACCC"
 end:   "AACCCCCC"
 bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]

 return: 3

 */

// 与wordladder127类似，几个注意点，
// 1. 每次的reached要被替换为一个新的set
// 2. 每次在bank里找个一个match后要从bank里remove对于的str，
// 3. 最后比较end和当前str时要用equals

public class MinimumGeneticMutation433 {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> reached = new HashSet<>();
        Set<String> bankSet = new HashSet<>();
        for(String b: bank) bankSet.add(b);

        reached.add(start);
        bankSet.remove(start);
        int step = 0;
        while(!reached.isEmpty())
        {
            Set<String> newReachedSet = new HashSet<>();
            for (String geneStr : reached)
            {

                for (int i = 0; i < geneStr.length(); i++)
                {
                    for (char c : new char[]{'A', 'C', 'G', 'T'})
                    {
                        char[] geneChars = geneStr.toCharArray();
                        geneChars[i] = c;
                        String newGeneStr = new String(geneChars);
                        if(bankSet.remove(newGeneStr))
                        {
                            newReachedSet.add(newGeneStr);
                            if (newGeneStr.equals(end)) return step+1;
                        }
                    }
                }


            }
            reached = newReachedSet;
            step++;
        }

        return -1;
    }

}
