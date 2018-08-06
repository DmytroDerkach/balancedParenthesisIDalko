package ut.com.atlassian.jira.customfields;

import com.atlassian.jira.customfields.impl.BalancedParenthesis;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Dima on 02-Aug-18.
 */
public class BalancedParenthesisTest {
    @Test
    public void isBalancedTest(){
        String[] balancedStr = {" ", " () ", "(afs(asdf)asdf)asdf(asdf)", "(()()())", "((()))", "(()(()()))"};
        for (String aBalancedStr : balancedStr) {
            boolean isBalanced = BalancedParenthesis.isBalancedParenthesis(aBalancedStr);
            Assert.assertTrue("Validation has not been passed with balanced parenthesis",isBalanced);
        }

        String[] notBalancedStr = {"(asd(((())", "()s))(", " (f", "))", "((sdf("};
        for (String aNotBalancedStr : notBalancedStr) {
            boolean isBalanced = BalancedParenthesis.isBalancedParenthesis(aNotBalancedStr);
            Assert.assertFalse("Validation has not been passed with un-balanced parenthesis", isBalanced);
        }


    }
}
