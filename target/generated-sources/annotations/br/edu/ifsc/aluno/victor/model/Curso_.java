package br.edu.ifsc.aluno.victor.model;

import br.edu.ifsc.aluno.victor.enuns.EnumModalidade;
import br.edu.ifsc.aluno.victor.enuns.EnumPeriodo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-12T15:54:26")
@StaticMetamodel(Curso.class)
public class Curso_ { 

    public static volatile SingularAttribute<Curso, EnumPeriodo> periodo;
    public static volatile SingularAttribute<Curso, Integer> id;
    public static volatile SingularAttribute<Curso, String> email;
    public static volatile SingularAttribute<Curso, String> descricao;
    public static volatile SingularAttribute<Curso, EnumModalidade> modalidade;

}