package com.gabr.edrdo.spring_app.repository;

import com.gabr.edrdo.spring_app.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findByNomeContainingIgnoreCase(String nome);

    boolean existsByCpf(String cpf);

    @Query("SELECT p FROM Pessoa p WHERE " +
            "(:nome IS NULL OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:cidade IS NULL OR LOWER(p.cidade) LIKE LOWER(CONCAT('%', :cidade, '%'))) AND " +
            "(:estado IS NULL OR LOWER(p.estado) = LOWER(:estado))")
    List<Pessoa> findByFilters(@Param("nome") String nome,
                               @Param("cidade") String cidade,
                               @Param("estado") String estado);
}
