package br.edu.ifsc.aluno.victor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@ToString

@NoArgsConstructor
@AllArgsConstructor
@Getter

@Entity
@Table(name = "coordenador")
public class Coordenador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;
    private Boolean ativo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servidor_id", referencedColumnName = "id")
    private Servidor servidor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", referencedColumnName = "id")
    private Curso curso;

    public Coordenador (Servidor servidor, Curso curso, Coordenador coordenador) {
        this.preencher(servidor, curso, coordenador);
    }

    private void preencher(Servidor servidor, Curso curso, Coordenador coordenador) {
        this.dataInicio = coordenador.getDataInicio();
        this.dataFim = coordenador.getDataFim();
        this.ativo = coordenador.getAtivo();
        this.servidor = servidor;
        this.curso = curso;
    }

    public Coordenador editar(Servidor servidor, Curso curso, Coordenador coordenador) {
        this.preencher(servidor, curso, coordenador);
        return this;
    }

    public Coordenador desativar(){
        this.dataFim = LocalDate.now();
        this.ativo = false;
        return this;
    }
}
