package strings;

/**
 * Compare two version numbers version1 and version2.
 If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.

 You may assume that the version strings are non-empty and contain only digits and the . character.
 The . character does not represent a decimal point and is used to separate number sequences.
 For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

 Example 1:

 Input: version1 = "0.1", version2 = "1.1"
 Output: -1
 Example 2:

 Input: version1 = "1.0.1", version2 = "1"
 Output: 1
 Example 3:

 Input: version1 = "7.5.2.4", version2 = "7.5.3"
 Output: -1
 */


// Split后从左往右比较，注意如果出现长度不相同的情况，则需要判断多出来的部分是否全为0
    
public class CompareVersionNum165 {
    public int compareVersion(String version1, String version2) {
        if(version1 == null || version2 == null) return 0;

        String[] ver1Digits = version1.split("\\.");
        String[] ver2Digits = version2.split("\\.");


        int i  = 0;
        while(i < ver1Digits.length && i < ver2Digits.length) {
            int ver1Num = Integer.parseInt(ver1Digits[i]);
            int ver2Num = Integer.parseInt(ver2Digits[i]);
            if(ver1Num < ver2Num) {
                return -1;
            }
            if(ver1Num > ver2Num) {
                return 1;
            }

            i++;
        }
        if(ver1Digits.length == ver2Digits.length) return 0;


        // 判断剩余部分是否全为0，比如1.2.0000等同于1.2
        int restSum = 0;
        if(ver1Digits.length > ver2Digits.length) {
            for (int j = i; j < ver1Digits.length; j++) {
                restSum += Integer.parseInt(ver1Digits[j]);
            }
        }
        else {
            for (int j = i; j < ver2Digits.length; j++) {
                restSum += Integer.parseInt(ver2Digits[j]);
            }
        }

        if(restSum == 0) {
            return 0;
        }

        // 若剩余部分不为0则越长越大
        return ver1Digits.length > ver2Digits.length ? 1 : -1;
    }
}
