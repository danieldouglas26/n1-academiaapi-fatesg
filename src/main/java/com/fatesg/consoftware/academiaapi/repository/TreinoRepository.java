/**
 * ClassName: TreinoRepository
 * @autor [Daniel Douglas Melo Gomes Neto]
 * @email [danieldouglas26@outlook.com]
 * @data 4 de out. de 2025
 * @versão 1.0
 */
package com.fatesg.consoftware.academiaapi.repository;

import com.fatesg.consoftware.academiaapi.entity.Treino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinoRepository extends JpaRepository<Treino, Integer> {
}