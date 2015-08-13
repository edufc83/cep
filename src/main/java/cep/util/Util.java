package cep.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import cep.model.Endereco;
import cep.model.Endereco2;

public class Util {
    public static final String MENSSAGEM_ERRO_CEP = "CEP inv�lido";
    
    public static final String MENSSAGEM_ENDERECO_ENCONTRADO = "Endere�o foi Econtrado";
    
    public static final String MENSSAGEM_ERRO_IN_ALT = "N�o efetuar a Inclus�o/Altera��o";

    public static final String MENSSAGEM_ENDERECO_NAO_ENCONTRADO = "Endere�o n�o foi Econtrado";
    

    public static List<Endereco> getListaEnderecos() {
        List<Endereco> listaRetorno = new ArrayList<Endereco>();
        listaRetorno.add(new Endereco("02310100", "Rua Jamunda", "Vila Mazzei", "S�o Paulo", "SP"));
        listaRetorno.add(new Endereco("03357010", "Rua Camberra", "Vila Formosa", "S�o Paulo", "SP"));
        listaRetorno.add(new Endereco("01202900", "Alameda Bar�o de Limeira", "Campos El�seos", "S�o Paulo", "SP"));
        listaRetorno.add(new Endereco("01451001", "Av. Brg. Faria Lima", "Pinheiros", "S�o Paulo", "SP"));
        listaRetorno.add(new Endereco("05005900", "Rua Palestra It�lia", "Perdizes", "S�o Paulo", "SP"));
        listaRetorno.add(new Endereco("05653070", "Pra�a Roberto Gomes Pedrosa", "Morumbi", "S�o Paulo", "SP"));

        return listaRetorno;
    }

  
}
