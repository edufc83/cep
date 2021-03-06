package cep.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cep.model.Endereco2;
import cep.repository.Endereco2Repository;
import cep.util.Util;

@Controller
@RequestMapping(value = "/endereco")
public class Exercicio2Controller {

    @Autowired
    private Endereco2Repository endereco2Repository;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Endereco2 inserirEndereco(@RequestBody Endereco2 endereco) {
        List<Endereco2> lista = endereco2Repository.consultarCepBD(endereco.getCep());
        Endereco2 result = null;
        try {
            if (lista != null && lista.size() > 0 ) {
                result = new Endereco2(Util.MENSSAGEM_ERRO_IN_ALT + " pois o " + Util.MENSSAGEM_ENDERECO_ENCONTRADO);

            } else {
                result = endereco2Repository.salvar(endereco);
            }
        } catch (Exception e) {
            result = new Endereco2(Util.MENSSAGEM_ERRO_IN_ALT + " pois o " + Util.MENSSAGEM_ENDERECO_ENCONTRADO);
        }

        return result;

    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Endereco2 alterarEndereco(@RequestBody Endereco2 endereco) {
        List<Endereco2> lista = endereco2Repository.consultarCepBD(endereco.getCep());
        Endereco2 result = null;
        try {

            if (lista != null || lista.size() > 0) {
                endereco.setId(lista.get(0).getId());
                result = endereco2Repository.salvar(endereco);

            } else {
                result = new Endereco2(Util.MENSSAGEM_ERRO_IN_ALT + " pois o " + Util.MENSSAGEM_ENDERECO_NAO_ENCONTRADO);
            }
        } catch (Exception e) {
            result = new Endereco2(Util.MENSSAGEM_ERRO_IN_ALT + " pois o " + Util.MENSSAGEM_ENDERECO_NAO_ENCONTRADO);
        }

        return result;

    }

    @RequestMapping(method = RequestMethod.DELETE)
    public  @ResponseBody Endereco2 deletarEndereco(@RequestParam(required = false) Integer id) {
        endereco2Repository.excluir(id);          
        return new Endereco2(Util.MENSSAGEM_EXCLUSAO_SUCESSO) ;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Endereco2> consultaEndereco(@RequestParam(required = false) String cep) {
        List<Endereco2> result = endereco2Repository.consultarCepBD(cep);
       
        return result;

    }

}
