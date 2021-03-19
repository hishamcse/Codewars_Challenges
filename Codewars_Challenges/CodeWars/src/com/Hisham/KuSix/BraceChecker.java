package com.Hisham.KuSix;

/*
 * Challenge: https://www.codewars.com/kata/5277c8a221e209d3f6000b56/java
 */

import java.util.Stack;

public class BraceChecker {

    private static final char LEFT_PAREN = '(';
    private static final char RIGHT_PAREN = ')';
    private static final char LEFT_BRACE = '{';
    private static final char RIGHT_BRACE = '}';
    private static final char LEFT_BRACKET = '[';
    private static final char RIGHT_BRACKET = ']';

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == LEFT_PAREN) stack.push(LEFT_PAREN);
            if (s.charAt(i) == LEFT_BRACE) stack.push(LEFT_BRACE);
            if (s.charAt(i) == LEFT_BRACKET) stack.push(LEFT_BRACKET);

            if (s.charAt(i) == RIGHT_PAREN) {
                if (stack.isEmpty()) return false;
                if (stack.pop() != LEFT_PAREN) return false;
            } else if (s.charAt(i) == RIGHT_BRACE) {
                if (stack.isEmpty()) return false;
                if (stack.pop() != LEFT_BRACE) return false;
            } else if (s.charAt(i) == RIGHT_BRACKET) {
                if (stack.isEmpty()) return false;
                if (stack.pop() != LEFT_BRACKET) return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        BraceChecker checker = new BraceChecker();
        System.out.println(checker.isValid("[({})](]"));
    }
}
