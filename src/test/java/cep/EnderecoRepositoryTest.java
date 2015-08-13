package cep;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cep.model.Endereco;
import cep.model.Endereco2;
import cep.repository.Endereco2Repository;
import cep.repository.EnderecoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mvc-config.xml" })
@WebAppConfiguration
public class EnderecoRepositoryTest {

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private static final String MENSSAGEM_ERRO_CEP = "CEP inválido";

    private static final String CEP_INVALIDO = "1122252222";

    @Mock
    private EnderecoRepository enderecoRepo;
    
    @Mock
    private Endereco2Repository endereco2Repo;
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void inicio() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void buscarCepValidoTest() throws Exception {
        Endereco enderecoTest = new Endereco("02310100", "Rua Jamunda", "Vila Mazzei", "São Paulo", "SP");

        doReturn(enderecoTest).when(enderecoRepo).buscarCep(anyString());

        mockMvc.perform(get(String.format("/buscar/%s", enderecoTest.getCep())).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.cep", is(enderecoTest.getCep())));
    }

    @Test
    public void buscarCepInvalidoTest() throws Exception {

        doReturn(new Endereco()).when(enderecoRepo).buscarCep(anyString());

        mockMvc.perform(get(String.format("/buscar/%s", CEP_INVALIDO)).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.mensagem", is(MENSSAGEM_ERRO_CEP)));
    }
    
 

}
