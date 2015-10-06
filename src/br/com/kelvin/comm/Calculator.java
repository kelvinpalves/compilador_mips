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
public class Calculator {

    public Integer solve(String npr) {
        npr = npr.replace("  ", " ");
        String elements[] = npr.split("[\\s]");
        Stack stack = new Stack();

        for (String element : elements) {
            if (element.equals(Token.FIB.getCharacter())) {
                Integer vlr1 = Integer.parseInt(stack.pop().toString());
                vlr1 = fibo(vlr1);
                stack.push(vlr1);
                continue;
            }
            
            if (element.equals(Token.FACT.getCharacter())) {
                Integer vlr1 = Integer.parseInt(stack.pop().toString());
                Integer aux = vlr1;
                for (int i = 1; i < vlr1; i++) {
                    aux *= i;
                }
                stack.push(aux);
                continue;
            }
            
            if (element.equals(Token.SQRT.getCharacter())) {
                Integer vlr1 = Integer.parseInt(stack.pop().toString());
                Double vlr1D = Math.sqrt(vlr1);
                stack.push(vlr1D.intValue());
                continue;
            }
            
            if (element.equals(Token.OPER_PLUS_3.getCharacter())) {
                Integer vlr2 = Integer.parseInt(stack.pop().toString());
                Integer vlr1 = Integer.parseInt(stack.pop().toString());
                stack.push(vlr1 + vlr2);
                continue;
            }

            if (element.equals(Token.OPER_SUBS_4.getCharacter())) {
                Integer vlr2 = Integer.parseInt(stack.pop().toString());
                Integer vlr1 = Integer.parseInt(stack.pop().toString());
                stack.push(vlr1 - vlr2);
                continue;
            }

            if (element.equals(Token.OPER_MULT_1.getCharacter())) {
                Integer vlr2 = Integer.parseInt(stack.pop().toString());
                Integer vlr1 = Integer.parseInt(stack.pop().toString());
                stack.push(vlr1 * vlr2);
                continue;
            }

            if (element.equals(Token.OPER_DIVI_2.getCharacter())) {
                Integer vlr2 = Integer.parseInt(stack.pop().toString());
                Integer vlr1 = Integer.parseInt(stack.pop().toString());
                stack.push(vlr1 / vlr2);
                continue;
            }
            
            stack.push (element);
        }

        return Integer.parseInt(stack.pop().toString());
    }
    
    public Integer fibo(Integer n) { 
        int F = 0;
        int ant = 0; 
        for (int i = 1; i <= n; i++) { 
            if (i == 1) { 
                F = 1; 
                ant = 0; 
            } else { 
                F += ant; 
                ant = F - ant; 
            } 
        } 
        return F; 
    }
}
