package com.Hisham.KuThree;

/*
  Challenge: https://www.codewars.com/kata/5235c913397cbf2508000048
  ******
 */

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class Calculator {

    public static double getValue_By_Operation(String op, double val1, double val2) {
        if (op.equals("+")) return val1 + val2;
        if (op.equals("-")) return val1 - val2;
        if (op.equals("*")) return val1 * val2;
        if (op.equals("/")) return val1 / val2;
        return 0;
    }

    public static Double evaluate(String expression) {
        Map<String, Integer> operator_id = new TreeMap<>();
        operator_id.put("(", 0);
        operator_id.put(")", 0);
        operator_id.put("+", 1);
        operator_id.put("-", 1);
        operator_id.put("*", 2);
        operator_id.put("/", 2);

        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();

        String[] all = expression.split(" ");

        for (String s : all) {

            if (!operator_id.containsKey(s)) {   // value
                vals.push(Double.parseDouble(s));
                continue;
            }

            while (true) {                    // operator
                if (ops.isEmpty() || s.equals("(") || (operator_id.get(s) > operator_id.get(ops.peek()))) {
                    ops.push(s);
                    break;
                }

                String op = ops.pop();
                if (op.equals("(")) {
                    break;
                } else {
                    double val2 = vals.pop();
                    double val1 = vals.pop();
                    vals.push(getValue_By_Operation(op, val1, val2));
                }
            }
        }

        while (!ops.isEmpty()) {
            String op = ops.pop();
            double val2 = vals.pop();
            double val1 = vals.pop();
            vals.push(getValue_By_Operation(op, val1, val2));
        }

        return vals.pop();
    }

    public static void main(String[] args) {
        System.out.println(evaluate("7.7 - 3.3 - 4.4"));
    }
}
