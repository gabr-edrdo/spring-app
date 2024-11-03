package com.gabr.edrdo.spring_app.service;

import com.gabr.edrdo.spring_app.dto.PessoaDTO;
import com.gabr.edrdo.spring_app.model.Pessoa;
import com.gabr.edrdo.spring_app.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional(readOnly = true)
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Pessoa findById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + id + "!"));
    }

    @Transactional(readOnly = true)
    public List<Pessoa> findByNome(String nome) {
        return pessoaRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional(readOnly = true)
    public List<Pessoa> findByFilters(String nome, String cidade, String estado) {
        return pessoaRepository.findByFilters(nome, cidade, estado);
    }

    @Transactional
    public Pessoa save(PessoaDTO dto) {
        if (dto.getId() == null && pessoaRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado!");
        }

        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(dto, pessoa);
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public Pessoa update(Long id, PessoaDTO dto) {
        Pessoa pessoa = findById(id);

        // Verifica se o CPF foi alterado e se já existe
        if (!pessoa.getCpf().equals(dto.getCpf()) && pessoaRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado!");
        }

        BeanUtils.copyProperties(dto, pessoa, "id");
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void delete(Long id) {
        Pessoa pessoa = findById(id);
        pessoaRepository.delete(pessoa);
    }
}
