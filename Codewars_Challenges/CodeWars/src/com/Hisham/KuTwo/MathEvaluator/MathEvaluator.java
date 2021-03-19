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

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathEvaluator {

    private static final Pattern PATTERN_NUMBER = Pattern.compile("[0-9]*\\.?[0-9]+");
    Stack<String> ops = new Stack<>();
    Stack<Double> vals = new Stack<>();

    private static final Map<String, Integer> OPERATOR_ID = new TreeMap<>() {{
        put("(", 0);
        put(")", 0);
        put("+", 1);
        put("-", 1);
        put("*", 2);
        put("/", 2);
        put("~", 3);
    }};

    public double getValue_By_Operation(String op, double val1, double val2) {
        if (op.equals("+")) return val1 + val2;
        if (op.equals("-")) return val1 - val2;
        if (op.equals("*")) return val1 * val2;
        if (op.equals("/")) return val1 / val2;
        return 0;
    }

    private void populate_Value_stack(String operator) {
        if (operator.equals("~")) {
            double val = vals.pop();
            vals.push(-val);
        } else {
            Double val2 = vals.pop();
            Double val1 = vals.pop();
            vals.push(getValue_By_Operation(operator, val1, val2));
        }
    }

    public double calculate(String expression) {

        expression = expression.replaceAll("\\s+", "");
        int pos = 0;
        boolean bool = true;
        Matcher m = PATTERN_NUMBER.matcher(expression);

        while (pos < expression.length()) {
            if (bool) {
                if (expression.charAt(pos) == '-') {
                    ops.push("~");
                    pos++;
                } else if (expression.charAt(pos) == '(') {
                    ops.push("(");
                    pos++;
                } else {
                    boolean ignored = m.find();
                    int end = m.end();
                    double d = Double.parseDouble(expression.substring(pos, end));
                    vals.push(d);
                    pos = end;
                    bool = false;
                }
            } else {
                String op = String.valueOf(expression.charAt(pos));
                if (op.equals(")")) {
                    op = ops.pop();
                    while (!op.equals("(")) {
                        populate_Value_stack(op);
                        op = ops.pop();
                    }
                } else {
                    while (!ops.isEmpty() && OPERATOR_ID.get(ops.peek()) >= OPERATOR_ID.get(op)) {
                        populate_Value_stack(ops.pop());
                    }
                    ops.push(op);
                    bool = true;
                }
                pos++;
            }
        }

        while (!ops.isEmpty()) {
            populate_Value_stack(ops.pop());
        }
        return vals.pop();
    }

    public static void main(String[] args) {
        MathEvaluator evaluator = new MathEvaluator();
        System.out.println(evaluator.calculate("(123.45*(678.90 / (-2.5+ 11.5)-(((80 -(19))) *33.25)) / 20) - (123.45*(678.90 / (-2.5+ 11.5)-(((80 -(19))) *33.25)) / 20) + (13 - 2)/ -(-11)"));
    }
}