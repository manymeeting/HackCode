package others;

import java.util.*;

/**
 * This problem is an interactive problem new to the LeetCode platform.

 We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.

 You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.

 This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.

 For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.

 Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.

 Example 1:
 Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]

 Explanation:

 master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
 master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
 master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
 master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
 master.guess("abcczz") returns 4, because "abcczz" has 4 matches.

 We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
 Note:  Any solutions that attempt to circumvent the judge will result in disqualification.
 */

// 解法1：每次guess以后，留下与guess结果具有相同的match数的string作为下一次初始的list
// 解法2：因为两个word 0 match的概率很大，大约为80%（(25/26)^6 = 80%），所以每次选一个和其它word最小0match的word去guess，这样能更快的排除0 match的words
public class GuessTheWord843 {
    interface Master {
        public int guess(String word);
    }


    // 解法1
    public void findSecretWord(String[] wordlist, Master master) {
        for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
            String guess = wordlist[new Random().nextInt(wordlist.length)];
            x = master.guess(guess);
            List<String> wordlist2 = new ArrayList<>();
            for (String w : wordlist)
                if (matchCount(guess, w) == x)
                    wordlist2.add(w);
            wordlist = wordlist2.toArray(new String[wordlist2.size()]);
        }
    }

    // 解法2（更快排除0 match）
    /**
    public void findSecretWord(String[] wordlist, Master master) {


        int loop = 10;
        while(loop > 0) {
            Map<String, Integer> zeroMatchCntMap = new HashMap<>();
            for (String w1 : wordlist) {
                for (String w2 : wordlist) {
                    if(matchCount(w1, w2) == 0) {
                        zeroMatchCntMap.put(w1, zeroMatchCntMap.getOrDefault(w1, 0) + 1);
                    }
                }
            }

            // 选一个0match数量最小的string来guess
            String minMatchStr = "";
            int minMatchCnt = Integer.MAX_VALUE;
            for (String w : wordlist) {
                if(zeroMatchCntMap.getOrDefault(w, 0) < minMatchCnt) {
                    minMatchStr = w;
                }
            }

            int guessRes = master.guess(minMatchStr);
            List<String> newWordList = new ArrayList<>();
            for (String w: wordlist) {
                if(matchCount(minMatchStr, w) == guessRes) {
                    newWordList.add(w);
                }
            }
            wordlist = newWordList.toArray(new String[0]);



            loop--;
        }



    }
     */
    private int matchCount(String a, String b) {
        int count = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) count++;
        }
        return count;
    }
}
