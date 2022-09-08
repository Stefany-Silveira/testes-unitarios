package br.com.stefany.tu.servicos;

import static br.com.stefany.tu.utils.DataUtils.isMesmaData;
import static br.com.stefany.tu.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.com.stefany.tu.exception.FilmeSemEstoqueException;
import br.com.stefany.tu.exception.LocadoraException;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.com.stefany.tu.entidades.Filme;
import br.com.stefany.tu.entidades.Locacao;
import br.com.stefany.tu.entidades.Usuario;

public class LocacaoServiceTest {

    private LocacaoService service;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        service = new LocacaoService();
    }

    @Test
    public void testLocacao() throws Exception {

        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));

        //acao
        Locacao locacao = service.alugarFilme(usuario, filmes);

        //verificacao
        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
        error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
    }

    @Test(expected = FilmeSemEstoqueException.class)
    public void testLocacao_filmeSemEstoque() throws Exception {

        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 4.0));

        //acao
        service.alugarFilme(usuario, filmes);
    }

    @Test
    public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException {

        //cenario
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));

        //acao
        try {
            service.alugarFilme(null, filmes);
            Assert.fail();
        } catch (LocadoraException e) {
            assertThat(e.getMessage(), is("Usuário vazio"));
        }
    }

    @Test
    public void testLocacao_FilmeVzio() throws FilmeSemEstoqueException, LocadoraException {

        //cenario
        Usuario usuario = new Usuario("Usuário 1");

        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio");

        //acao
        service.alugarFilme(usuario, null);
    }
}
