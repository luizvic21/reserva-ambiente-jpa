package br.edu.ifsc.aluno.victor.model;

import br.edu.ifsc.aluno.victor.model.Bloco;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-12T15:54:26")
@StaticMetamodel(Ambiente.class)
public class Ambiente_ { 

    public static volatile SingularAttribute<Ambiente, String> sigla;
    public static volatile SingularAttribute<Ambiente, String> foto;
    public static volatile SingularAttribute<Ambiente, String> chaveAcesso;
    public static volatile SingularAttribute<Ambiente, Bloco> bloco;
    public static volatile SingularAttribute<Ambiente, Integer> id;
    public static volatile SingularAttribute<Ambiente, String> descricao;

}