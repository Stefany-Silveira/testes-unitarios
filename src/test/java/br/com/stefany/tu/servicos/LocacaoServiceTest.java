package br.com.stefany.tu.servicos;

import org.junit.Rule;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static br.com.stefany.tu.utils.DataUtils.isMesmaData;
import static br.com.stefany.tu.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import br.com.stefany.tu.entidades.Filme;
import br.com.stefany.tu.entidades.Locacao;
import br.com.stefany.tu.entidades.Usuario;
import org.junit.rules.ErrorCollector;

public class LocacaoServiceTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Test
    public void testeLocacao() {

        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        //ação
        Locacao locacao = service.alugarFilme(usuario, filme);

        //verificacao
        error.checkThat(locacao.getValor(), is(equalTo(6.0)));
        error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)),
                is(false));
    }
}
