package cep.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cep.model.Endereco;
import cep.model.Endereco2;
import cep.repository.EnderecoRepository;

@Controller
public class Exercicio1Controller {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @RequestMapping(value = "/buscar/{cep}", method = RequestMethod.GET)
    public @ResponseBody Endereco getEnderecoCep(@PathVariable String cep) {

        return enderecoRepository.buscarCep(cep);

    }

    @RequestMapping(value = "/buscarTodos", method = RequestMethod.GET)
    public @ResponseBody List<Endereco> getEnderecosCep() {

        List<Endereco> endereco = enderecoRepository.buscarTodos();

        return endereco;

    }

 
  
}

