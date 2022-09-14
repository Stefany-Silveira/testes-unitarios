package br.com.stefany.tu.servicos;

import br.com.stefany.tu.exception.NaoPodeDividirPorZeroException;
import br.com.stefany.tu.runners.ParallelRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

public class CalculadoraTest {

    public static StringBuffer ordem = new StringBuffer();

    private Calculadora calc;

    @Before
    public void setup() {
        calc = new Calculadora();
        System.out.println("iniciando...");
    }

    @After
    public void tearDown() {
        System.out.println("finalizando...");
    }

    @Test
    public void devesomarDoisValores() {
        //cenario
        int a = 5;
        int b= 3;

        //acao
        int resultado = calc.somar(a, b);

        //verificacao
        Assert.assertEquals(8, resultado);
    }

    @Test
    public void deveSubtrairDoisValores() {
        //cenario
        int a = 8;
        int b = 5;

        //acao
        int resultado = calc.subtrair(a, b);

        //verificacao
        Assert.assertEquals(3, resultado);
    }

    @Test
    public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException {
        //cenario
        int a = 10;
        int b = 2;

        //acao
        int resultado = calc.divisao(a,b);

        //verificacao
        Assert.assertEquals(5, resultado);
    }

    @Test(expected = NaoPodeDividirPorZeroException.class)
    public void deveLancarExcecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException {
        int a = 10;
        int b = 0;

        calc.divisao(a, b);
    }

    @Test
    public void deveDividir() {
        String a ="6";
        String b = "3";

        int resultado = calc.divide(a, b);

        Assert.assertEquals(2, resultado);
    }
}
