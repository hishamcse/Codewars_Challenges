package com.Hisham.KuTwo.MathEvaluator;

/*
 * Challenge: https://www.codewars.com/kata/52a78825cdfc2cfc87000005
 * ******
 * Resource:
 *  1. https://stackoverflow.com/questions/3681242/java-how-to-parse-double-from-regex
 *  2. https://stackoverflow.com/questions/27808112/java-splitting-with-math-expression
 *  3. https://tutorialspoint.dev/data-structure/stack-data-structure
 *  4. https://www.codewars.com/kata/52a78825cdfc2cfc87000005/discuss/java
 */

/* another version.
 * Excellent implementation through regex. really awesome solution.
 * [Collected]
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathEvaluator2 {

    private final Pattern pattern_parenthesis = Pattern.compile("(-?)\\(([^()]+)\\)");
    private final Pattern pattern_mult_div = Pattern.compile("(-?[0-9.]+)\\s*(/|\\*)\\s*(-?[0-9.]+)");
    private final Pattern pattern_add_subtract = Pattern.compile("(-?[0-9.]+)\\s*(\\+|-)\\s*(-?[0-9.]+)");

    private double getValue_By_Operation(String op, double val1, double val2) {
        if (op.equals("+")) return val1 + val2;
        if (op.equals("-")) return val1 - val2;
        if (op.equals("*")) return val1 * val2;
        if (op.equals("/")) return val1 / val2;
        return 0;
    }

    public double calculate(String expression) {
        Matcher matcher = pattern_parenthesis.matcher(expression);
        while (matcher.find()) {
            String evaluatedValue = evaluate(matcher.group(2));
            if (!matcher.group(1).isEmpty()) {
                evaluatedValue = evaluatedValue.startsWith("-") ? evaluatedValue.substring(1) : "-" + evaluatedValue;
            }
            expression = expression.substring(0, matcher.start()) + evaluatedValue + expression.substring(matcher.end());
            matcher = pattern_parenthesis.matcher(expression);
        }

        return Double.parseDouble(evaluate(expression));
    }

    private String evaluate(String expression) {
        Matcher matcher = pattern_mult_div.matcher(expression);
        while (matcher.find()) {
            double val1 = Double.parseDouble(matcher.group(1));
            double val2 = Double.parseDouble(matcher.group(3));
            String op = matcher.group(2);
            double value = getValue_By_Operation(op, val1, val2);
            expression = expression.substring(0, matcher.start()) + value + expression.substring(matcher.end());
            matcher = pattern_mult_div.matcher(expression);
        }

        matcher = pattern_add_subtract.matcher(expression);
        while (matcher.find()) {
            double val1 = Double.parseDouble(matcher.group(1));
            double val2 = Double.parseDouble(matcher.group(3));
            String op = matcher.group(2);
            double value = getValue_By_Operation(op, val1, val2);
            expression = expression.substring(0, matcher.start()) + value + expression.substring(matcher.end());
            matcher = pattern_add_subtract.matcher(expression);
        }

        return expression;
    }

    public static void main(String[] args) {
        MathEvaluator2 evaluator2 = new MathEvaluator2();
        System.out.println(evaluator2.calculate("(123.45*(678.90 / (-2.5+ 11.5)-(((80 -(19))) *33.25)) / 20) - (123.45*(678.90 / (-2.5+ 11.5)-(((80 -(19))) *33.25)) / 20) + (13 - 2)/ -(-11)"));
    }
}