package br.com.stefany.tu.servicos;

import br.com.stefany.tu.entidades.Filme;
import br.com.stefany.tu.entidades.Locacao;
import br.com.stefany.tu.entidades.Usuario;
import br.com.stefany.tu.utils.DataUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static br.com.stefany.tu.utils.DataUtils.adicionarDias;

public class LocacaoService {

    public Locacao alugarFilme(Usuario usuario, Filme filme) {
        Locacao locacao = new Locacao();
        locacao.setFilme(filme);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(new Date());
        locacao.setValor(filme.getPrecoLocacao());

        //Entrega no dia seguinte
        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1);
        locacao.setDataRetorno(dataEntrega);

        //Salvando a locacao...
        //TODO adicionar método para salvar

        return locacao;
    }

    @Test
    public void teste() {

        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        //ação
        Locacao locacao = service.alugarFilme(usuario, filme);

        //verificacao
        Assertions.assertTrue(locacao.getValor() == 5.0);
        Assertions.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        Assertions.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(),
                DataUtils.obterDataComDiferencaDias(1)));
    }
}
