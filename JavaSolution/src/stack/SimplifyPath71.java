package stack;

import java.util.*;

/**
Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.

In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level. For more information, see: Absolute path vs relative path in Linux/Unix

Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.

 

Example 1:

Input: "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.
Example 2:

Input: "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
Example 3:

Input: "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
Example 4:

Input: "/a/./b/../../c/"
Output: "/c"
Example 5:

Input: "/a/../../b/../c//.//"
Output: "/c"
Example 6:

Input: "/a//b////c/d//././/.."
Output: "/a/b/c"
*/


// 先split（注意会包含空字符串），然后用一个Deque来存有效的目录名，最后从另一端poll出来，拼接成最终结果

class SimplifyPath71 {
    public String simplifyPath(String path) {
    	if(path == null || path.length() == 0) return "";

        String[] splitPath = path.split("/");
        ArrayDeque<String> deque = new ArrayDeque<>();
        for (int i = 0; i < splitPath.length; i++) {
        	String curr = splitPath[i];
        	
        	if(curr.length() == 0) continue; 
        	if("..".equals(curr)) {
        		if(!deque.isEmpty()) {
        			deque.pollLast();
        		}
        	}
        	else if(".".equals(curr)) {
        		continue;
        	}
        	else {
        		deque.offerLast(curr);	
        	}
        	
        }

        StringBuilder res = new StringBuilder();
        
        if(deque.isEmpty()) return "/"; // 根据测试需要，回到根目录时返回一个"/"

        while(!deque.isEmpty()) {
        	res.append("/");
        	res.append(deque.pollFirst());
        }

        return res.toString();
    }
}
