package strings;


import java.util.LinkedList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 For example, given n = 3, a solution set is:

 [
 "((()))",
 "(()())",
 "(())()",
 "()(())",
 "()()()"
 ]
 */

// 通过递归中调用多个相同递归来构造树状结果集
// if(open < numberOfParentheses)：递归，if(close < open)：再次递归

public class GenerateParentheses22 {

    public List<String> generateParenthesis(int n) {

        List<String> res = new LinkedList<>();
        generator(res, "", 0,0, n);
        return res;
    }

    public void generator(List<String> solutionSet, String solution, int open, int close, int numberOfParentheses){

        if(open == numberOfParentheses && close == numberOfParentheses)
        {
            solutionSet.add(solution);
            return;
        }

        if(open < numberOfParentheses)
        {
            generator(solutionSet, solution + "(", open+1, close, numberOfParentheses);
        }
        if(close < open)
        {
            generator(solutionSet, solution + ")", open, close+1, numberOfParentheses);
        }

    }

}
