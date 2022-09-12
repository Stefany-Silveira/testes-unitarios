package br.com.stefany.tu.dao;

import br.com.stefany.tu.entidades.Locacao;

import java.util.List;

public interface LocacaoDAO {

    public void salvar(Locacao locacao);

    public List<Locacao> obterLocacoesPendentes();
}
