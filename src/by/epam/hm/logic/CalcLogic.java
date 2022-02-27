package by.epam.hm.logic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

public class CalcLogic {

    public static double calculation(String str) {
        ArrayList<String> token = new ArrayList<>(getPNotation(str));
        Stack<Double> stack = new Stack<>();
        double value;

        for (int i = 0; i < token.size(); i++) {
            switch (token.get(i)) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    value = stack.pop();
                    stack.push(stack.pop() - value);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    value = stack.pop();
                    stack.push(stack.pop() / value);
                    break;
                default:
                    stack.push(Double.parseDouble(token.get(i)));
            }
        }
        return stack.pop();
    }

    public static ArrayDeque<String> getPNotation(String text) {
        ArrayDeque<String> box1 = new ArrayDeque<>();
        Stack<String> box2 = new Stack<>();
        char symbol;
        String temp = new String();

        for (int i = 0; i < text.length(); i++) {
            symbol = text.charAt(i);
            if (isDigit(symbol)) {
                temp += String.valueOf(symbol);
                if (i == text.length() - 1 || !isDigit(text.charAt(i + 1))) {
                    box1.add(temp);
                    temp = "";
                }
            } else if (isMathSymbol(symbol)) {
                if (getPriority(symbol) == 1) {
                    box2.push(String.valueOf(symbol));
                } else if (getPriority(symbol) > 1) {
                    while (box2.size() != 0) {
                        if (getPriority(box2.peek().charAt(0)) >= getPriority(symbol)) {
                            box1.add(box2.pop());
                        } else {
                            break;
                        }
                    }
                    box2.push((String.valueOf(symbol)));
                } else if (getPriority(symbol) == -1) {
                    while ((getPriority(box2.peek().charAt(0)) != 1)) {
                        box1.add(box2.pop());
                    }
                    box2.pop();
                }
            }
        }
        while (!box2.isEmpty()) {
            box1.add(box2.pop());
        }
        return box1;
    }

    public static boolean isDigit(char c) {
        if (c >= '0' && c <= '9' || c == '.') {
            return true;
        }
        return false;
    }

    public static boolean isMathSymbol(char c) {
        if (c == '(' || c == ')' || c == '+' || c == '-' || c == '*' || c == '/') {
            return true;
        }
        return false;
    }

    public static int getPriority(char c) {
        if (c == '/' || c == '*') {
            return 3;
        } else if (c == '-' || c == '+') {
            return 2;
        } else if (c == '(') {
            return 1;
        } else if (c == ')') {
            return -1;
        }
        return 0;
    }
}

