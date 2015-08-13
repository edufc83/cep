package cep;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mvc-config.xml" })
@WebAppConfiguration
public class Endereco2RepositoryTest {

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Mock
    private Endereco2Repository endereco2Repo;

    @Autowired
    private WebApplicationContext webApplicationContext2;

    private MockMvc mockMvc2;

    @Before
    public void inicio() {
        
        MockitoAnnotations.initMocks(this);

        mockMvc2 = MockMvcBuilders.webAppContextSetup(webApplicationContext2).build();
    }

    @Test
    public void consultaEnderecoTest() throws Exception {
        Endereco enderecoTest = new Endereco("02310100", "Rua Jamunda", "Vila Mazzei", "São Paulo", "SP");
        List<Endereco2> lista = new ArrayList<Endereco2>();
        doReturn(lista).when(endereco2Repo).consultarCepBD(anyString());

        mockMvc2.perform(get(String.format("/endereco/?cep=%s", enderecoTest.getCep())).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8));

    }
}
