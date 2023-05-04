package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.PessoaDAO;
import br.edu.ifsp.pep.modelo.Pessoa;
import br.edu.ifsp.pep.util.Util;
import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class PessoaController implements Serializable {

    @Inject
    private PessoaDAO pessoaDAO;

    private Pessoa pessoa;
    private Pessoa pessoaAutenticada;

    public PessoaController() {
        this.pessoa = new Pessoa();
    }

    public void autenticar() {
        pessoaAutenticada = pessoaDAO
                .buscarPorLoginSenha(pessoa.getLogin(), pessoa.getSenha());
        if (pessoaAutenticada != null) {
            Util.addMessageInformation("Usuário autenticado.");
        } else {
            Util.addMessageError("Login ou Senha inválidos.");
        }
        this.pessoa = new Pessoa();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Pessoa getPessoaAutenticada() {
        return pessoaAutenticada;
    }
    
    

}
