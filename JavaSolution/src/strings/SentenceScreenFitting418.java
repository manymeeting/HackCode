package strings;

/**
 * Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.

 Note:

 A word cannot be split into two lines.
 The order of words in the sentence must remain unchanged.
 Two consecutive words in a line must be separated by a single space.
 Total words in the sentence won't exceed 100.
 Length of each word is greater than 0 and won't exceed 10.
 1 ≤ rows, cols ≤ 20,000.
 Example 1:

 Input:
 rows = 2, cols = 8, sentence = ["hello", "world"]

 Output:
 1

 Explanation:
 hello---
 world---

 The character '-' signifies an empty space on the screen.
 Example 2:

 Input:
 rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

 Output:
 2

 Explanation:
 a-bcd-
 e-a---
 bcd-e-

 The character '-' signifies an empty space on the screen.
 Example 3:

 Input:
 rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

 Output:
 1

 Explanation:
 I-had
 apple
 pie-I
 had--

 The character '-' signifies an empty space on the screen.
 */

// 把arr中的string用空格连接，并在最后加上一个空格作为重复模板，
// 记录一个当前已放下的字符的数量，每次加上col数量，然后判断chCnt % len（offset）位置是否为空格，
// 不是的话说明word放不下，需要退回一个字符重试，否则向前一个字符。循环直到区域结束

public class SentenceScreenFitting418 {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String newSentence = String.join(" ", sentence) + " ";

        int chCnt = 0;
        int len = newSentence.length();
        for (int i = 0; i < rows;i ++) {
            chCnt += cols;
            if(newSentence.charAt(chCnt % len) == ' ') {
                chCnt++;
            }
            else {
                // 由于不能将一个word分成两行，所以要退回char位置
                while(chCnt > 0 && !(newSentence.charAt((chCnt - 1) % len) == ' ') ) {
                    chCnt--;
                }
            }
        }
        return chCnt / newSentence.length();
    }
}
