package cep.repository;

import java.util.List;

import cep.model.Endereco2;


public interface Endereco2Repository {
    public Endereco2 salvar(Endereco2 endereco);
    
    public   List<Endereco2> consultarCepBD(String cep);
    
    
    public void excluir(Integer id);

}
