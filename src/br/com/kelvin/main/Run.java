/*
 * Setor de pesquisa e desenvolvimento - Kopp Tecnologia.
 */
package br.com.kelvin.main;

import br.com.kelvin.comm.ReadInput;

/**
 *
 * @author Kelvin Pereira Alves <kelvinpalves@gmail.com>
 */
public class Run {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        ReadInput readInput = new ReadInput();
        for (String arg : args) {
            readInput.parse(arg);
        }
    }
}