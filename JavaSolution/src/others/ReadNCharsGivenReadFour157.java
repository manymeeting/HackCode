package others;

/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.

 The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

 By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

 Example 1:

 Input: buf = "abc", n = 4
 Output: "abc"
 Explanation: The actual number of characters read is 3, which is "abc".
 Example 2:

 Input: buf = "abcde", n = 5
 Output: "abcde"
 Note:
 The read function will only be called once for each test case.
 */

// 注意终止条件，reaedcount<4（已经读完了），或者total==n（读取长度达到了n上限）

public class ReadNCharsGivenReadFour157 {

    // Dummy function
    int read4(char[] buf){
        return -1;
    }

    public int read(char[] buf, int n) {
        int total = 0;

        while(true) {


            char[] temp = new char[4];
            int readCount = read4(temp);
            for (int i = 0; i < readCount; i++) {

                if(total == n) return total;

                buf[total] = temp[i];
                total++;
            }

            if(readCount< 4) return total;
        }
    }
}
