package br.edu.ifsc.aluno.victor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@ToString

@NoArgsConstructor
@AllArgsConstructor
@Getter

@Entity
@Table(name = "usuario")
public class Usuario extends Pessoa implements Serializable{

    private String username;
    private String senha;

    public Usuario(Integer id, String nome, Date dataNascimento, String fone, String fone2, String email, String cpf, String rg, Endereco endereco, String username, String senha) {
        super(id, nome, dataNascimento, fone, fone2, email, cpf, rg, endereco);
        this.username = username;
        this.senha = senha;
    }

    public Usuario(Endereco endereco, Usuario usuario) {
        this.preencher(endereco, usuario);
    }

    private void preencher(Endereco endereco, Usuario usuario) {
        super.preencher(endereco, usuario);
        this.username = usuario.getUsername();
        this.senha = usuario.getSenha();
    }

    public Usuario editar(Endereco endereco, Usuario usuario) {
        preencher(endereco, usuario);
        return this;
    }
}
