/*
 * Setor de pesquisa e desenvolvimento - Kopp Tecnologia.
 */
package br.com.kelvin.utils;

/**
 *
 * @author Kelvin Pereira Alves <kelvinpalves@gmail.com>
 */
public enum Token {
    BEGIN_PAREN ("(", 3),
    END_PAREN (")", 4),
    OPER_MULT_1 ("*", 2),
    OPER_DIVI_2 ("/", 2),
    OPER_PLUS_3 ("+", 1),
    OPER_SUBS_4 ("-", 1),
    FACT ("!", 7),
    FIB ("F", 8),
    SQRT ("<", 9),
    NUM ("d+", 10);
    
    private String character;
    private int order;
    
    Token(String character, int order) {
        this.character = character;
        this.order = order;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
