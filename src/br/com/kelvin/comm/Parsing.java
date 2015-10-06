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
public class Parsing {

    boolean mult = false;
    Stack<Integer> tRegistors;
    String lastReg;
    Integer label;
    Integer labelMult;
    Integer labelFact;
    Integer labelFib;

    public void analyse(String npr) {
        npr = npr.replace("  ", " ");
        String elements[] = npr.split("[\\s]");
        initTRegistors();
        Stack stack = new Stack();
        label = 0;
        labelMult = 0;
        labelFact = 0;
        labelFib = 0;
        
        System.out.println(".text");
        
        for (String element : elements) {
            if (element.equals("F")) {
                String vlr01 = stack.pop().toString();
                String v1 = vlr01.startsWith("$t") ? vlr01 : String.format("0x%05X", Integer.parseInt(vlr01));
                
                Integer result = tRegistors.pop();
                checkStack(vlr01, null);
                lastReg = "$t" + result;
                
                if (labelFib == 0) {
                    System.out.println("iniciar_fib_" + labelFib + ":");
                }
                
                System.out.println("\tli\t" + lastReg + ", 0x0");
                System.out.println("\tli\t$s7, 0x0");
                System.out.println("\tli\t$s6, 0x1");
                
                if (v1.startsWith("$")) {
                    System.out.println("\tmove\t$s5, " + v1);
                } else {
                    System.out.println("\tli\t$s5, " + v1);
                }
                
                System.out.println("\tli\t$t4, 0x0");
                System.out.println("loop_fib_" + labelFib + ":");
                System.out.println("\tbge\t$s6, $s5, exit_fib_" + labelFib);
                System.out.println("\tbeq\t$s6, 0x01, first_fib" + labelFib);
                System.out.println("\tj\tcontinue_fib" + labelFib);
                System.out.println("first_fib" + labelFib +":");
                System.out.println("\taddi\t" + lastReg + ", " + lastReg +", 1");
                System.out.println("\taddi\t$s7, $s7, 1");
                System.out.println("\taddi\t$s6, $s6, 1");
                System.out.println("\tj\tloop_fib_" + labelFib);
                System.out.println("continue_fib" + labelFib + ":");
                System.out.println("\tadd\t" + lastReg + ", " + lastReg + ", $s7");
                System.out.println("\tsub\t$s7, " + lastReg +", $s7");
                System.out.println("\taddi\t$s6, $s6, 1");
                System.out.println("\tj\tloop_fib_" + labelFib);
                System.out.println("exit_fib_" + labelFib + ":");
                
                labelFib++;
                stack.push(lastReg);
                continue;
            }
            
            if (element.equals("!")) {
                String vlr01 = stack.pop().toString();
                String v1 = vlr01.startsWith("$t") ? vlr01 : String.format("0x%05X", Integer.parseInt(vlr01));
                
                Integer result = tRegistors.pop();
                checkStack(vlr01, null);
                lastReg = "$t" + result;
                
                if (labelFact == 0) {
                    System.out.println("init_fact" + labelFact + ":");
                }
                
                if (v1.startsWith("$")) {
                    System.out.println("\tmove\t$k0, " + v1);
                    System.out.println("\tmove\t$k1, " + v1);
                } else {
                    System.out.println("\tli\t$k0, " + v1);
                    System.out.println("\tli\t$k1, " + v1);
                }
                
                System.out.println("\tli\t" + lastReg + ", 0x0");
                System.out.println("\tli\t$t9, 0x0");
                System.out.println("loop_fact" + labelFact + ":");
                System.out.println("\tble\t$k1, 0x01, set_vlr_fact" + labelFact);
                System.out.println("\tsubi\t$k1, $k1, 1");
                System.out.println("\tmove\t$t9, $k1");
                System.out.println("loop_mult_fact" + labelFact + ":");
                System.out.println("\tble\t$t9, 0x0, move_vlr_fact" + labelFact);
                System.out.println("\tadd\t" + lastReg + ", " + lastReg + ", $k0");
                System.out.println("\tsubi\t$t9, $t9, 1");
                System.out.println("\tj\tloop_mult_fact" + labelFact);
                System.out.println("move_vlr_fact" + labelFact + ":");
                System.out.println("\tmove\t$k0, " + lastReg);
                System.out.println("\tli\t" + lastReg + ", 0x0");
                System.out.println("\tj\tloop_fact" + labelFact);
                System.out.println("set_vlr_fact" + labelFact + ":");
                System.out.println("\tmove " + lastReg + ", $k0");
                System.out.println("\tj\texit_fact" + labelFact);
                System.out.println("exit_fact" + labelFact + ":");
                labelFact++;
                stack.push(lastReg);
                
                continue;
            }
            
            if (element.equals("<")) {
                String vlr01 = stack.pop().toString();
                
                String v1 = vlr01.startsWith("$t") ? vlr01 : String.format("0x%05X", Integer.parseInt(vlr01));
                
                Integer result = tRegistors.pop();
                checkStack(vlr01, null);
                lastReg = "$t" + result;
                
                if (label == 0) {
                    System.out.println("iniciar_sqrt" + label + ":");
                }
                
                if (v1.startsWith("$t")) {
                    System.out.println("\tmove\t$s0, " + v1);
                } else {
                    System.out.println("\tli\t$s0, " + v1);
                }
                
                System.out.println("\tli\t" + lastReg + ", 0x0");
                System.out.println("\tsrl\t$s0, $s0, 0x01");
                System.out.println("loop_sqrt" + label + ":");
                System.out.println("\tblt\t$s0, " + lastReg + ", iniciar_sqrt" + (label + 1));
                System.out.println("\tsub\t$s0, $s0, " + lastReg);
                System.out.println("\taddi\t" + lastReg + ", " + lastReg + ", 0x01");
                System.out.println("\tj\tloop_sqrt" + label);               
                System.out.println("iniciar_sqrt" + (label + 1) + ":");
                label++;
                stack.push(lastReg);
                continue;
            } 
            
            if (element.equals("+") || element.equals("-")) {
                String vlr02 = stack.pop().toString();
                String vlr01 = stack.pop().toString();

                String cmd01 = vlr01.startsWith("$t") ? "move" : "li";
                String cmd02 = vlr02.startsWith("$t") ? "move" : "li";
                String v0 = vlr01.startsWith("$t") ? vlr01 : String.format("0x%05X", Integer.parseInt(vlr01));
                String v1 = vlr02.startsWith("$t") ? vlr02 : String.format("0x%05X", Integer.parseInt(vlr02));

                System.out.println("\t" + cmd01 + "\t$v0, " + v0);
                System.out.println("\t" + cmd02 + "\t$v1, " + v1);

                checkStack(vlr01, vlr02);
                Integer result = tRegistors.pop();
                lastReg = "$t" + result;
                String oper = element.equals("+") ? "add" : "sub";
                System.out.println("\t" + oper +"\t$t" + result + ", $v0, $v1");
                stack.push("$t" + result);
                continue;
            }
            
            if (element.equals("/")) {
                String vlr02 = stack.pop().toString();
                String vlr01 = stack.pop().toString();
                String cmd01 = vlr01.startsWith("$t") ? "move" : "li";
                String cmd02 = vlr02.startsWith("$t") ? "move" : "li";
                String v0 = vlr01.startsWith("$t") ? vlr01 : String.format("0x%05X", Integer.parseInt(vlr01));
                String v1 = vlr02.startsWith("$t") ? vlr02 : String.format("0x%05X", Integer.parseInt(vlr02));
                
                Integer result = tRegistors.pop();
                checkStack(vlr01, vlr02);
                lastReg = "$t" + result;
                
                if (label == 0) {
                    System.out.println("carregar" + label + ":");
                }
                
                System.out.println("\tli\t" + lastReg + ", 0x0");
                System.out.println("\t" + cmd01 + "\t$v0, " + v0);
                System.out.println("\t" + cmd02 + "\t$v1, " + v1);
                System.out.println("loop_div_" + label + ":");
                System.out.println("\tsub\t$v0,$v0, $v1");
                System.out.println("\tblt\t$v0, 0x0, carregar" + (label + 1));
                System.out.println("\taddi\t" + lastReg +", " + lastReg +", 1");
                System.out.println("\tj\tloop_div_" + label);
                System.out.println("carregar" + (label+1) + ":");
                
                
                label++;
                stack.push(lastReg);
                continue;
                
            }
            
            if (element.equals("*")) {
                String vlr02 = stack.pop().toString();
                String vlr01 = stack.pop().toString();
                
                String v2 = vlr01.startsWith("$t") ? vlr01 : String.format("0x%05X", Integer.parseInt(vlr01));
                String v3 = vlr02.startsWith("$t") ? vlr02 : String.format("0x%05X", Integer.parseInt(vlr02));
                
                if (labelMult == 0) {
                    System.out.println("loop" + labelMult + ":");
                }
                
                System.out.println("\tbge\t$s0, " + v2 + ", zerar" + (labelMult+1));
                System.out.println("\taddi\t$s0, $s0, 0x01");
                Integer result = tRegistors.pop();
                checkStack(vlr01, vlr02);
                lastReg = "$t" + result;

                System.out.println("\tadd\t" + lastReg + ", " + lastReg + ", " + v3);
                                
                System.out.println("\tj\tloop" + labelMult);
                System.out.println("zerar" + (labelMult+1) + ":");                
                
                System.out.println("\tli\t$s0, 0x0");
                if (v3.startsWith("$t")) {
                    System.out.println("\tli\t" + v3 + ", 0x0");
                }
                
                System.out.println("\tj\tloop" + (labelMult+1));
                System.out.println("loop" + (labelMult+1) + ":");
                labelMult++;
                stack.push(lastReg);
                continue;
            }

            stack.push(element);
        }

        System.out.println("\tli\t$v0, 1");
        System.out.println("\tmove\t$a0, " + lastReg);
        System.out.println("\tsyscall");
    }

    public void checkStack(String vlr01, String vlr02) {
        if (vlr01 != null) {
            if (vlr01.startsWith("$t")) {
                Integer t = Integer.parseInt(vlr01.replace("$t", ""));
                tRegistors.push(t);
            }
        }

        if (vlr02 != null) {
            if (vlr02.startsWith("$t")) {
                Integer t = Integer.parseInt(vlr02.replace("$t", ""));
                tRegistors.push(t);
            }
        }
    }

    public void initTRegistors() {
        tRegistors = new Stack<>();
        tRegistors.push(1);
        tRegistors.push(2);
        tRegistors.push(3);
        tRegistors.push(4);
        tRegistors.push(5);
        tRegistors.push(6);
        tRegistors.push(7);
    }

    public void checkReg(Stack stack) {
        int i = stack.size() - 1;
        System.out.print("Pilha Atual: ");
        for (int j = i; j >= 0; j--) {
            System.out.print(stack.get(j) + ", ");
        }
        System.out.println("");
    }

    public void printActualStack(Stack stack) {
        int i = stack.size() - 1;
        System.out.print("Pilha Atual: ");
        for (int j = i; j >= 0; j--) {
            System.out.print(stack.get(j) + ", ");
        }
        System.out.println("");
    }

    public String[] invertArray(String[] array) {
        String inverted[] = new String[array.length];
        int j = 0;
        for (int i = (array.length - 1); i >= 0; i--) {
            inverted[j++] = array[i];
        }
        return inverted;
    }

    public void analyse2(String npr) {
        String elements[] = npr.split("[\\s]");
        Stack stack = new Stack();
        System.out.println("\n");
        System.out.println(".text");

        int loopCount = 0;

        for (String element : elements) {
            if (element.equals(Token.OPER_PLUS_3.getCharacter())) {
                if (stack.isEmpty()) {
                    continue;
                }

                String t1 = stack.pop().toString();

                if (stack.isEmpty()) {
                    continue;
                }

                String t2 = stack.pop().toString();

                if (t1.startsWith("$t") || t2.startsWith("$t")) {
                    continue;
                }

                sum(t1, t2);
                stack.push("$t3");
                continue;
            }

            if (element.equals(Token.OPER_SUBS_4.getCharacter())) {
                String t2 = stack.pop().toString();
                String t1 = stack.pop().toString();
                sub(t1, t2);
                stack.push("$t0");
                continue;
            }

            if (element.equals(Token.OPER_MULT_1.getCharacter())) {
                String t1 = stack.pop().toString();
                String t2 = stack.pop().toString();

                System.out.println("\tli\t$t4, 0x0");
                System.out.println("\tli\t$t3, 0x0");
                System.out.println("loop" + loopCount + ": ");
                System.out.println("\tbge\t$t4, " + String.format("0x%05X", Integer.parseInt(t2)) + ", exit" + loopCount);
                System.out.println("\taddi\t$t4, $t4, 1");
                System.out.println("\tadd\t$t3, $t3, " + (t1.startsWith("$t") ? t1 : String.format("0x%05X", Integer.parseInt(t1))));
                System.out.println("\tj\tloop" + loopCount);
                System.out.println("exit" + loopCount + ":");
                System.out.println("\tmove\t$t5, $t3");

                stack.push("$t5");

                loopCount++;
                mult = true;
                continue;
            }

            if (element.equals(Token.OPER_DIVI_2.getCharacter())) {
                Integer vlr2 = Integer.parseInt(stack.pop().toString());
                Integer vlr1 = Integer.parseInt(stack.pop().toString());
                stack.push(vlr1 / vlr2);
                continue;
            }

            stack.push(element);
        }
    }

    public void sum(String t1, String t2) {
        if (t1.startsWith("$t")) {
            System.out.println("\tmove\t$t1, " + t1);
        } else {
            System.out.println("\tli\t$t1, " + String.format("0x%05X", Integer.parseInt(t1)));
        }

        if (t2.startsWith("$t")) {
            System.out.println("\tmove\t$t2, " + t2);
        } else {
            System.out.println("\tli\t$t2, " + String.format("0x%05X", Integer.parseInt(t2)));
        }

        System.out.println("\tmove\t$t4, $t3");
        System.out.println("\tadd\t$t3, $t1, $t2");
        System.out.println("\tadd\t$t3, $t3, $t4");
    }

    public void sumOld(String t1, String t2) {
        if (t1.startsWith("$t")) {
            System.out.println("\tmove\t$t1, " + t1);
        } else {
            System.out.println("\tli\t$t1, " + String.format("0x%05X", Integer.parseInt(t1)));
        }

        if (t2.startsWith("$t")) {
            System.out.println("\tmove\t$t2, " + t2);
        } else {
            System.out.println("\tli\t$t2, " + String.format("0x%05X", Integer.parseInt(t2)));
        }

        System.out.println("\tadd\t$t0, $t1, $t2");
    }

    public void sub(String t1, String t2) {
        if (t1.startsWith("$t")) {
            System.out.println("\tmove\t$t1, " + t1);
        } else {
            System.out.println("\tli\t$t1, " + String.format("0x%05X", Integer.parseInt(t1)));
        }

        if (t2.startsWith("$t")) {
            System.out.println("\tmove\t$t2, " + t2);
        } else {
            System.out.println("\tli\t$t2, " + String.format("0x%05X", Integer.parseInt(t2)));
        }

        System.out.println("\tsub\t$t0, $t1, $t2");
    }
}
