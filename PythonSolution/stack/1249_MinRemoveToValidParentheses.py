"""
Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.


Example:

Input: s = "a)b(c)d"
Output: "ab(c)d"

"""


from ctypes import sizeof


class Solution(object):
    def minRemoveToMakeValid(self, s):
        """
        :type s: str
        :rtype: str
        """
        if sizeof(s) == 0:
            return ''

        indexes_to_remove = set()
        stk = []
        
        # Find indexes of invalid parentheses to remove
        for i, ch in enumerate(s):
            if ch not in "()":
                continue
            elif ch == '(':
                stk.append(i)
            elif ch == ')': 
                if not stk:
                    # We found an invalid ')'
                    indexes_to_remove.add(i)
                else:
                    stk.pop()

        # Add remaining indexes of redundunt '('
        indexes_to_remove = indexes_to_remove.union(set(stk))

        valid_str = []
        for i, ch in enumerate(s):
            if i not in indexes_to_remove:
                valid_str.append(ch)

        return "".join(valid_str)