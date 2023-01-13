package avaliacao.attornatus.controller;

import avaliacao.attornatus.entity.Pessoa;
import avaliacao.attornatus.repository.PessoaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa createPessoa(@RequestBody Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @PutMapping("/{id}")
    public Pessoa updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Pessoa currentPessoa = pessoaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pessoa id:" + id));
        currentPessoa.setNome(pessoa.getNome());
        currentPessoa.setDataNascimento(pessoa.getDataNascimento());
        currentPessoa.setEnderecos(pessoa.getEnderecos());
        return pessoaRepository.save(currentPessoa);
    }

    @GetMapping("/{id}")
    public Pessoa getPessoa(@PathVariable Long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pessoa id:" + id));
    }

    @GetMapping
    public List<Pessoa> getPessoas() {
        return pessoaRepository.findAll();
    }
}

