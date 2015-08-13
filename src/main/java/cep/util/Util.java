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
    public static final String MENSSAGEM_ERRO_CEP = "CEP inválido";
    
    public static final String MENSSAGEM_ENDERECO_ENCONTRADO = "Endereço foi Econtrado";
    
    public static final String MENSSAGEM_ERRO_IN_ALT = "Não efetuar a Inclusão/Alteração";

    public static final String MENSSAGEM_ENDERECO_NAO_ENCONTRADO = "Endereço não foi Econtrado";
    

    public static List<Endereco> getListaEnderecos() {
        List<Endereco> listaRetorno = new ArrayList<Endereco>();
        listaRetorno.add(new Endereco("02310100", "Rua Jamunda", "Vila Mazzei", "São Paulo", "SP"));
        listaRetorno.add(new Endereco("03357010", "Rua Camberra", "Vila Formosa", "São Paulo", "SP"));
        listaRetorno.add(new Endereco("01202900", "Alameda Barão de Limeira", "Campos Elíseos", "São Paulo", "SP"));
        listaRetorno.add(new Endereco("01451001", "Av. Brg. Faria Lima", "Pinheiros", "São Paulo", "SP"));
        listaRetorno.add(new Endereco("05005900", "Rua Palestra Itália", "Perdizes", "São Paulo", "SP"));
        listaRetorno.add(new Endereco("05653070", "Praça Roberto Gomes Pedrosa", "Morumbi", "São Paulo", "SP"));

        return listaRetorno;
    }

  
}
