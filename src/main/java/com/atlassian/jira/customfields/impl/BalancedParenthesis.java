package com.atlassian.jira.customfields.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dima on 02-Aug-18.
 */
public class BalancedParenthesis {

    public static boolean isBalancedParenthesis(String str){
        if (str == null) return false; // ??? should it be "true" or "false" ???
        // if a string doesn't have parenthesis - balanced
        if (!str.contains("(") && !str.contains(")")) return true;
        // if a string has open but doesn't have closed parentheses - unbalanced
        if (str.contains("(") && !str.contains(")")) return false;
        // if a string has closed but doesn't have open parentheses - unbalanced
        if (!str.contains("(") && str.contains(")")) return false;

        List<Boolean> listParenthesis = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(')
                listParenthesis.add(true);
            else
                if(str.charAt(i) == ')') {
                    try {
                        listParenthesis.remove(listParenthesis.size() - 1);
                    } catch (Exception e) {
                        return false;
                    }
                }
        }

        return listParenthesis.size() == 0;
    }
}
