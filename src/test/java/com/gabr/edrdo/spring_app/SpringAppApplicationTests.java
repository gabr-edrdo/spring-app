package com.gabr.edrdo.spring_app;

import com.gabr.edrdo.spring_app.dto.PessoaDTO;
import com.gabr.edrdo.spring_app.model.Pessoa;
import com.gabr.edrdo.spring_app.service.PessoaService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class SpringAppApplicationTests {

	@Autowired
	private PessoaService pessoaService;

	private Pessoa pessoa;

	private PessoaDTO pessoaDTO;

	@BeforeEach
	void setUp() {
		// Configurar a pessoa para o teste
		pessoaDTO = new PessoaDTO();
		pessoaDTO.setNome("Teste Teste");
		pessoaDTO.setDataNascimento(LocalDate.of(1990, 1, 1));
		pessoaDTO.setCpf("794.861.290-45");
		pessoaDTO.setCep("12345-678");
		pessoaDTO.setLogradouro("Rua Teste");
		pessoaDTO.setNumero("123");
		pessoaDTO.setBairro("Centro");
		pessoaDTO.setCidade("São Paulo");
		pessoaDTO.setEstado("SP");

		pessoa = pessoaService.save(pessoaDTO);
	}

	@AfterEach
	void tearDown() {
		// Deleta a pessoa cada teste para não dar duplicidade de CPF
		pessoaService.delete(pessoa.getId());
	}

	@Test
	void contextLoads() {
	}

	@Test
	void testCreatePessoa() {
		// Create
		Assertions.assertNotNull(pessoa.getId(), "O Id deve ser gerado após salvar!");
		Assertions.assertEquals(pessoaDTO.getNome(), pessoa.getNome(), "O nome deve ser igual ao DTO!");
	}

	@Test
	void testFindAllPessoas() {
		// Listar todas as pessoas
		List<Pessoa> pessoas = pessoaService.findAll();
		Assertions.assertFalse(pessoas.isEmpty(), "A lista de pessoas não deve estar vazia!");
	}

	@Test
	void testFindPessoaById() {
		// Listar pelo Id
		Pessoa foundPessoa = pessoaService.findById(pessoa.getId());
		Assertions.assertEquals(pessoa.getId(), foundPessoa.getId(), "Os Ids devem ser iguais");
		Assertions.assertEquals(pessoaDTO.getNome(), foundPessoa.getNome(), "O nome deve ser igual ao DTO!");
	}

	@Test
	void testUpdatePessoa() {
		// Update
		PessoaDTO updatedDTO = new PessoaDTO();
		updatedDTO.setNome("Teste 2");
		updatedDTO.setDataNascimento(LocalDate.of(1985, 5, 5));
		updatedDTO.setCpf(pessoaDTO.getCpf()); // Mesmo CPF para não gerar duplicidade
		updatedDTO.setCep("54321-987");
		updatedDTO.setLogradouro("Avenida Nova");

		Pessoa updatedPessoa = pessoaService.update(pessoa.getId(), updatedDTO);
		Assertions.assertEquals("Teste 2", updatedPessoa.getNome(), "O nome deve ser atualizado para Teste 2!");
		Assertions.assertEquals("54321-987", updatedPessoa.getCep(), "O CEP deve ser atualizado para 54321-987!");
	}

	// Teste de duplicidade de CPF
	@Test
	void testDuplicateCpf() {
		PessoaDTO pessoaComCpfDuplicado = new PessoaDTO();
		pessoaComCpfDuplicado.setNome("Outro Teste");
		pessoaComCpfDuplicado.setDataNascimento(LocalDate.of(1980, 1, 1));
		pessoaComCpfDuplicado.setCpf(pessoaDTO.getCpf());  // CPF duplicado
		pessoaComCpfDuplicado.setCep("54321-987");

		Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			pessoaService.save(pessoaComCpfDuplicado);
		});

		String expectedMessage = "CPF já cadastrado!";
		String actualMessage = exception.getMessage();

		Assertions.assertEquals(expectedMessage, actualMessage, "Exceção CPF duplicado.");
	}

	// Teste para pessoa sem nome
	@Test
	void testCreatePessoaWithoutName() {
		PessoaDTO pessoaSemNome = new PessoaDTO();
		pessoaSemNome.setDataNascimento(LocalDate.of(1990, 1, 1));
		pessoaSemNome.setCpf("123.456.789-10");
		pessoaSemNome.setCep("12345-678");

		Assertions.assertThrows(org.springframework.orm.jpa.JpaSystemException.class, () -> {
			pessoaService.save(pessoaSemNome);
		}, "Exceção pessoa sem nome!");
	}
}
