/*
 * Setor de pesquisa e desenvolvimento - Kopp Tecnologia.
 */
package br.com.kelvin.comm;

/**
 *
 * @author Kelvin Pereira Alves <kelvinpalves@gmail.com>
 */
public class Expression {
    private Integer id;
    private String operator;
    private String value01;
    private String value02;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue01() {
        return value01;
    }

    public void setValue01(String value01) {
        this.value01 = value01;
    }

    public String getValue02() {
        return value02;
    }

    public void setValue02(String value02) {
        this.value02 = value02;
    }
    
}
