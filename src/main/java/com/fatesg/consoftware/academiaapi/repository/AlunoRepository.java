/**
 * ClassName: AlunoRepository
 * @autor [Daniel Douglas Melo Gomes Neto]
 * @email [danieldouglas26@outlook.com]
 * @data 4 de out. de 2025
 * @versão 1.0
 */
package com.fatesg.consoftware.academiaapi.repository;

import com.fatesg.consoftware.academiaapi.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
	Optional<Aluno> findByCpf(String cpf);
}