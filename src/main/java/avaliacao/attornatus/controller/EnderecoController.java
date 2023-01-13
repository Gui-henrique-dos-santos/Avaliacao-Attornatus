package avaliacao.attornatus.controller;

import avaliacao.attornatus.entity.Endereco;
import avaliacao.attornatus.entity.Pessoa;
import avaliacao.attornatus.repository.EnderecoRepository;
import avaliacao.attornatus.repository.PessoaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/pessoas/{pessoaId}/enderecos")
public class EnderecoController {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @PostMapping
    public Endereco createEndereco(@PathVariable Long pessoaId, @RequestBody Endereco endereco) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId).orElseThrow(() -> new IllegalArgumentException("Invalid pessoa id:" + pessoaId));
        endereco.setPessoa(pessoa);
        return enderecoRepository.save(endereco);
    }

    @PutMapping("/{id}")
    public Endereco updateEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
        Endereco currentEndereco = enderecoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid endereco id:" + id));
        currentEndereco.setLogradouro(endereco.getLogradouro());
        currentEndereco.setCep(endereco.getCep());
        currentEndereco.setNumero(endereco.getNumero());
        currentEndereco.setCidade(endereco.getCidade());
        currentEndereco.setPrincipal(endereco.isPrincipal());
        return enderecoRepository.save(currentEndereco);
    }
    @GetMapping
    public List<Endereco> getEnderecos(@PathVariable Long pessoaId) {
        return enderecoRepository.findByPessoaId(pessoaId);
    }
    @GetMapping("/principal")
    public Endereco getEnderecoPrincipal(@PathVariable Long pessoaId) {
        return enderecoRepository.findByPessoaIdAndPrincipal(pessoaId, true)
                .stream().findFirst().orElseThrow(() -> new IllegalArgumentException("Endereco principal não encontrado para pessoa id:" + pessoaId));
    }

}