/**
 * ClassName: TreinoService
 * @autor [Daniel Douglas Melo Gomes Neto]
 * @email [danieldouglas26@outlook.com]
 * @data 4 de out. de 2025
 * @versão 1.0
 */
package com.fatesg.consoftware.academiaapi.controller;

import com.fatesg.consoftware.academiaapi.dto.TreinoDTO;
import com.fatesg.consoftware.academiaapi.service.TreinoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/treinos")
@Tag(name = "Treinos", description = "Gerenciamento de Treinos da Academia")
public class TreinoController {

	@Autowired
	private TreinoService treinoService;

	@PostMapping
	public ResponseEntity<TreinoDTO> criarTreino(@RequestBody TreinoDTO treinoDTO) {
		return new ResponseEntity<>(treinoService.criar(treinoDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<TreinoDTO>> listarTreinos() {
		return ResponseEntity.ok(treinoService.listarTodos());
	}

	@PostMapping("/{treinoId}/associar-aluno")
	public ResponseEntity<Void> associarAluno(@PathVariable Integer treinoId,
			@RequestBody Map<String, Integer> payload) {
		Integer alunoId = payload.get("alunoId");
		treinoService.associarAluno(treinoId, alunoId);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarTreino(@PathVariable Integer id) {
		treinoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}