package Barclay;

import java.util.Stack;

/**
 * Created by linyujie on 10/29/17.
 */
public class PrefixEvaluation {
    public int evaluate(String s){
        Stack<Integer> stack = new Stack<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (Character.isDigit(s.charAt(i))) {
                stack.push(s.charAt(i) - '0');
            } else {
                int a = stack.pop();
                int b = stack.pop();

                switch (s.charAt(i)) {
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(a - b);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                    case '/':
                        stack.push(a/b);
                        break;
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String s = "*+234";
        PrefixEvaluation pe = new PrefixEvaluation();
        System.out.print(pe.evaluate(s));
    }
}
