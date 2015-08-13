package cep.repository;

import java.util.List;

import cep.model.Endereco;
import cep.model.Endereco2;


public interface EnderecoRepository {
   public List<Endereco> buscarTodos();
   
   public  Endereco buscarCep(String cep);
   
   

}
