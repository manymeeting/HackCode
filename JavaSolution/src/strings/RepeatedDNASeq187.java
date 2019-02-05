package strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

 Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

 Example:

 Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

 Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 */


// 用一个长度为10的window滑动，同时用一个set判断是否出现过，注意for loop边界的index判断
public class RepeatedDNASeq187 {
    public List<String> findRepeatedDnaSequences(String s) {

        Set<String> set = new HashSet<>();
        Set<String> res = new HashSet<>();
        for (int i = 0; i + 9 < s.length(); i++) { // 注意越界判断，i+9 < length
            String dna = s.substring(i, i + 10);
            if(!set.add(dna)) {
                res.add(dna);
            }
        }

        return new ArrayList<>(res);
    }
}
