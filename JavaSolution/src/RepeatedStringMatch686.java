public class RepeatedStringMatch686 {
    /*
    * Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.

    For example, with A = "abcd" and B = "cdabcdab".

    Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").

    Note:
    The length of A and B will be between 1 and 10000.
    */

    public int repeatedStringMatch(String A, String B) {
        int lenA = A.length();
        int lenB = B.length();

        // when lenA > lenB, consider A=123121, B=11
        int maxRepeat = lenA >= lenB ? 1 : (lenB / lenA + 1);

        StringBuilder repeatA = new StringBuilder();
        for (int i = 0; i <= maxRepeat; i++)
        {
            repeatA.append(A);
            if(repeatA.toString().contains(B))
            {
                return i+1;
            }
        }

        return -1;

    }
}
