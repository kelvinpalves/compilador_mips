/*
 * Setor de pesquisa e desenvolvimento - Kopp Tecnologia.
 */
package br.com.kelvin.comm;

import br.com.kelvin.utils.Token;
import java.util.StringTokenizer;

/**
 *
 * @author Kelvin Pereira Alves <kelvinpalves@gmail.com>
 */
public class LexicalAnalysis {

    public String[] analyze(String input) {
        StringTokenizer stringTokenizer = new StringTokenizer(input.replace(" ", ""), "+-*/()<!F", true);
        String tokens[] = new String[stringTokenizer.countTokens()];

        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = stringTokenizer.nextToken();
        }
        
        for (int i = 0; i < tokens.length; i++) {
            for (Token token : Token.values()) {
                if (tokens[i].equals(token.getCharacter())) {
                    tokens[i] = token.name();
                } else {
                    try {
                        Integer.parseInt(tokens[i]);
                        break;
                    } catch (NumberFormatException ex) {
                    }
                }
            }
        }
        
        return tokens;
    }
}
