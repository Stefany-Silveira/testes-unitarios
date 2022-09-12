package br.com.stefany.tu.servicos;

import br.com.stefany.tu.entidades.Usuario;

public interface EmailService {

    public void notificarAtraso(Usuario usuario);
}
