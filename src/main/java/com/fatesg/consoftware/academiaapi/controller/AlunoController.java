/**
 * ClassName: AlunoController
 * @autor [Daniel Douglas Melo Gomes Neto]
 * @email [danieldouglas26@outlook.com]
 * @data 4 de out. de 2025
 * @versão 1.0
 */
package com.fatesg.consoftware.academiaapi.controller;

import com.fatesg.consoftware.academiaapi.dto.AlunoDTO;
import com.fatesg.consoftware.academiaapi.dto.PagamentoDTO;
import com.fatesg.consoftware.academiaapi.service.AlunoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/alunos")
@Tag(name = "Alunos", description = "Gerenciamento de Alunos da Academia")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@PostMapping
	public ResponseEntity<AlunoDTO> criarAluno(@RequestBody AlunoDTO alunoDTO) {
		return new ResponseEntity<>(alunoService.criarAluno(alunoDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<AlunoDTO>> listarAlunos() {
		return ResponseEntity.ok(alunoService.listarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlunoDTO> buscarAlunoPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(alunoService.buscarPorId(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AlunoDTO> atualizarAluno(@PathVariable Integer id, @RequestBody AlunoDTO alunoDTO) {
		return ResponseEntity.ok(alunoService.atualizar(id, alunoDTO));
	}

	@PatchMapping("/{id}/inativar")
	public ResponseEntity<Void> inativarAluno(@PathVariable Integer id) {
		alunoService.inativar(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{id}/pagamentos")
	public ResponseEntity<PagamentoDTO> registrarPagamento(@PathVariable Integer id,
			@RequestBody PagamentoDTO pagamentoDTO) {
		return new ResponseEntity<>(alunoService.registrarPagamento(id, pagamentoDTO), HttpStatus.CREATED);
	}
}