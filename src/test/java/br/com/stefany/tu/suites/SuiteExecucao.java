package br.com.stefany.tu.suites;

import org.junit.runners.Suite.SuiteClasses;

import br.com.stefany.tu.servicos.CalculadoraTest;
import br.com.stefany.tu.servicos.CalculoValorLocacaoTest;
import br.com.stefany.tu.servicos.LocacaoServiceTest;
import org.junit.runners.Suite;

//@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculadoraTest.class,
        CalculoValorLocacaoTest.class,
        LocacaoServiceTest.class
})
public class SuiteExecucao {
}
