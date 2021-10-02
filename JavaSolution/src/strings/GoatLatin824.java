package strings;

/**
 * A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.

 We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)

 The rules of Goat Latin are as follows:

 If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
 For example, the word 'apple' becomes 'applema'.

 If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
 For example, the word "goat" becomes "oatgma".

 Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
 For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
 Return the final sentence representing the conversion from S to Goat Latin.



 Example 1:

 Input: "I speak Goat Latin"
 Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
 Example 2:

 Input: "The quick brown fox jumped over the lazy dog"
 Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 */

// 仔细拼接string即可，注意元音包括大小写，
// 用vowels.indexOf(word.charAt(0)) >= 0判断是否从元音字母开始比用array方便


public class GoatLatin824 {
    public String toGoatLatin(String S) {
        StringBuilder allA = new StringBuilder();

        String vowels = "aeiouAEIOU";
        StringBuilder res = new StringBuilder();
        String[] words = S.split(" ");
        for (int i = 0; i < words.length; i++)
        {
            String goatWord = "";
            String word = words[i];
            allA.append('a');
            if(vowels.indexOf(word.charAt(0)) >= 0) {
                goatWord+= (word + "ma" + allA.toString() + " ");
                res.append(goatWord);
            }
            else {
                goatWord += (word.substring(1) + word.charAt(0) + "ma" + allA.toString() + " ");
                res.append(goatWord);
            }
        }

        res.deleteCharAt(res.length()-1);
        return res.toString();
    }
}
