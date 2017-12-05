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

        int maxRepeat;
        
        if(lenA >= lenB) // A=123121, B=11
        {
            maxRepeat = 1;
        }
        else
        {
            maxRepeat = lenB / lenA;
        }

        String repeatA = "";
        for (int i = 0; i <= maxRepeat; i++)
        {
            repeatA += A;
            if(repeatA.contains(B))
            {
                return i+1;
            }
        }

        return -1;

    }
}
