/*
 * Setor de pesquisa e desenvolvimento - Kopp Tecnologia.
 */
package br.com.kelvin.comm;

/**
 *
 * @author Kelvin Pereira Alves <kelvinpalves@gmail.com>
 */
public class ReadInput {

    private String[] tokens;
    private String npr;
    private Integer vlr;

    public void parse(String input) {
        System.out.println("#\tExpressão de Entrada:");
        System.out.println("#\t\t" + input);
        LexicalAnalysis lexicalAnalysis = new LexicalAnalysis();
        tokens = lexicalAnalysis.analyze(input);
        NotationPostfixReverse notationPostfixReverse = new NotationPostfixReverse();
        npr = notationPostfixReverse.infixToPostfix(tokens);
        System.out.println("#\tExpressão em Notação Polonesa Reversa: ");
        System.out.println("#\t\t" + npr);
        Calculator calculator = new Calculator();
        vlr = calculator.solve(npr);
        System.out.println("#\tResultado da Expressão: " + vlr);
        Parsing parsing = new Parsing();
        parsing.analyse(npr);
    }
}
