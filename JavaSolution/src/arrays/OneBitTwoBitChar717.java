package arrays;

/**
We have two special characters. The first character can be represented by one bit 0. The second character can be represented by two bits (10 or 11).

Now given a string represented by several bits. Return whether the last character must be a one-bit character or not. The given string will always end with a zero.

Example 1:
Input: 
bits = [1, 0, 0]
Output: True
Explanation: 
The only way to decode it is two-bit character and one-bit character. So the last character is one-bit character.
Example 2:
Input: 
bits = [1, 1, 1, 0]
Output: False
Explanation: 
The only way to decode it is two-bit character and two-bit character. So the last character is NOT one-bit character.
Note:

1 <= len(bits) <= 1000.
bits[i] is always 0 or 1.

*/

// 用一个pointer从0开始，遇到1就移动2位（因为1开头的肯定是两个bit一组），遇到0移动一位，最后看停在哪个位置

class OneBitTwoBitChar717 {
    public boolean isOneBitCharacter(int[] bits) {
     	int idx = 0;
     	while(idx < bits.length - 1) { // 停止条件：查看到倒数第二个
     		int bit = bits[idx];
     		if(bit == 1) {
     			idx += 2;
     		}
     		else {
     			idx += 1;
     		}
     	}

     	return idx == bits.length - 1;
    }
}