/*
 * Setor de pesquisa e desenvolvimento - Kopp Tecnologia.
 */
package br.com.kelvin.comm;

import br.com.kelvin.utils.Token;
import java.util.Stack;

/**
 *
 * @author Kelvin Pereira Alves <kelvinpalves@gmail.com>
 */
public class NotationPostfixReverse {
    public String infixToPostfix (String[] tokens) {
        Stack<Character> stack = new Stack<>();
        StringBuilder out = new StringBuilder();
        
        for (String token : tokens) {
            if (token.equals(Token.OPER_PLUS_3.name())) {
                while (!stack.empty() && (stack.peek() == '*' || stack.peek() == '/' || stack.peek() == '-' || stack.peek() == '<' || stack.peek() == '!' || stack.peek() == 'F')) {
                    out.append(' ');
                    out.append(stack.pop());
                }
                out.append(' ');
                stack.push('+');
                continue;
            }
            
            if (token.equals(Token.OPER_SUBS_4.name())) {
                while (!stack.empty() && (stack.peek() == '*' || stack.peek() == '/' || stack.peek() == '-' || stack.peek() == '<' || stack.peek() == '!' || stack.peek() == 'F')) {
                    out.append(' ');
                    out.append(stack.pop());
                }
                out.append(' ');
                stack.push('-');
                continue;
            }
            
            if (token.equals(Token.OPER_DIVI_2.name())) {
                while (!stack.empty() && (stack.peek() == '*' || stack.peek() == '/' || stack.peek() == '-' || stack.peek() == '<' || stack.peek() == '!' || stack.peek() == 'F')) {
                    out.append(' ');
                    out.append(stack.pop());
                }
                out.append(' ');
                stack.push('/');
                continue;
            }
            
            if (token.equals(Token.OPER_MULT_1.name())) {
                out.append(' ');
                stack.push(token.equals(Token.OPER_MULT_1.name()) ? '*' : '/');
                continue;
            }
            
            if (token.equals(Token.SQRT.name()) || token.equals(Token.FACT.name()) ||  token.equals(Token.FIB.name())) {
                out.append(' ');
                if (token.equals(Token.FIB.name())) {
                    stack.push(token.equals(Token.SQRT.name()) ? '<' : 'F');
                } else {
                    stack.push(token.equals(Token.SQRT.name()) ? '<' : '!');
                }
                
                continue;
            }
            
            if (token.equals(Token.BEGIN_PAREN.name())) {
                stack.push('(');
                continue;
            }
            
            if (token.equals(" ")) {
                continue;
            }
            
            if (token.equals(Token.END_PAREN.name())) {
                while (!stack.empty() && stack.peek() != '(') {
                    out.append(' ');
                    out.append(stack.pop());
                }
                stack.pop();
                continue;
            }
            
            out.append(token);
        }
        
        while (!stack.empty()) {
            out.append(' ').append(stack.pop());
        }
        
        return out.toString();
    }
}
