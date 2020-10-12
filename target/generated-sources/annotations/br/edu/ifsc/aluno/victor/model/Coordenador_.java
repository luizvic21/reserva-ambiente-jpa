package br.edu.ifsc.aluno.victor.model;

import br.edu.ifsc.aluno.victor.model.Curso;
import br.edu.ifsc.aluno.victor.model.Servidor;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-12T15:54:26")
@StaticMetamodel(Coordenador.class)
public class Coordenador_ { 

    public static volatile SingularAttribute<Coordenador, Servidor> servidor;
    public static volatile SingularAttribute<Coordenador, Boolean> ativo;
    public static volatile SingularAttribute<Coordenador, LocalDate> dataFim;
    public static volatile SingularAttribute<Coordenador, Curso> curso;
    public static volatile SingularAttribute<Coordenador, Integer> id;
    public static volatile SingularAttribute<Coordenador, LocalDate> dataInicio;

}