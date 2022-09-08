package br.com.stefany.tu.servicos;

import static br.com.stefany.tu.utils.DataUtils.isMesmaData;
import static br.com.stefany.tu.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.com.stefany.tu.entidades.Filme;
import br.com.stefany.tu.entidades.Locacao;
import br.com.stefany.tu.entidades.Usuario;

public class LocacaoServiceTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testeLocacao() throws Exception {

        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 1, 6.0);

        //ação
        Locacao locacao = service.alugarFilme(usuario, filme);

        //verificacao
        error.checkThat(locacao.getValor(), is(equalTo(6.0)));
        error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)),
                is(true));
    }

    @Test(expected=Exception.class)
    public void testLocacao_filmesSemEstoque() throws Exception {

        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0, 5.0);

        //acao
            service.alugarFilme(usuario, filme);
    }

    @Test
    public void testLocacao_filmeSemEstoque_2() {

        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0, 5.0);

        //acao
        try {
            service.alugarFilme(usuario, filme);
            Assert.fail("Deveria ser lançado uma exceção");
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Filme sem estoque"));
        }
    }

    @Test
    public void testeLocaccao_filmeSemEstoque_3() throws Exception {

        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0, 5.0);

        exception.expect(Exception.class);
        exception.expectMessage("Filme sem estoque");

        //acao
        service.alugarFilme(usuario, filme);
    }
}
