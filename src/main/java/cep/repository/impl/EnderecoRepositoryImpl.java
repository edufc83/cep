package cep.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import cep.model.Endereco;
import cep.model.Endereco2;
import cep.repository.EnderecoRepository;
import cep.util.Util;

@Repository
public class EnderecoRepositoryImpl implements EnderecoRepository {

    public List<Endereco> buscarTodos() {

        return Util.getListaEnderecos();
    }

    public Endereco buscarCep(String cep) {
        List<Endereco> lista = Util.getListaEnderecos();
        for (Endereco endereco : lista) {
            if (endereco.getCep().equals(cep)) {
                return endereco;
            }
        }

        return new Endereco(Util.MENSSAGEM_ERRO_CEP);
    }

   

}
