package avaliacao.attornatus.utils;
import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import avaliacao.attornatus.entity.Endereco;
import avaliacao.attornatus.entity.Pessoa;
import org.junit.jupiter.api.Test;

class PessoaTest {

    @Test
    void testPessoa() {
        Pessoa pessoa = new Pessoa("John Doe",LocalDate.of(1980, 1, 1));
        assertEquals("John Doe", pessoa.getNome());
        assertEquals(LocalDate.of(1980, 1, 1), pessoa.getDataNascimento());

        Endereco endereco1 = new Endereco("Main St.", "12345", 12, "Springfield",true, pessoa);
        Endereco endereco2 = new Endereco("Maple St.", "67890", 456, "Shelbyville", false, pessoa);
        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(endereco1);
        enderecos.add(endereco2);
        pessoa.setEnderecos(enderecos);
        assertEquals(enderecos, pessoa.getEnderecos());
    }

}

