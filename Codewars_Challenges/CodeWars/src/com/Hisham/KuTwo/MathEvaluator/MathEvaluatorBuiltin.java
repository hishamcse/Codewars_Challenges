package com.Hisham.KuTwo.MathEvaluator;

/*
 * Challenge: https://www.codewars.com/kata/52a78825cdfc2cfc87000005
 * ********
 * using java's built in class. but it is not allowed in this challenge
 * also deprecated solution. because Nashorn engine will no longer be available in future
 */

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.List;

public class MathEvaluatorBuiltin {

    private static ScriptEngine calc = new ScriptEngineManager().getEngineByName("graal.js");

    public double calculate(String expression) {
        try {
            return Double.parseDouble(calc.eval(expression) + "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1.0;
    }

    public static void main(String[] args) {
        MathEvaluatorBuiltin evaluatorBuiltin = new MathEvaluatorBuiltin();
        System.out.println(evaluatorBuiltin.calculate("2 /2+3 * 4.75- -6"));
        List<String> list = new ArrayList<>(20);
        System.out.println(list.size());
    }
}