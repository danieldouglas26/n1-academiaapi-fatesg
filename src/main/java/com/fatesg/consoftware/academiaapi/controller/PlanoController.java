/**
 * ClassName: PlanoController
 * @autor [Daniel Douglas Melo Gomes Neto]
 * @email [danieldouglas26@outlook.com]
 * @data 4 de out. de 2025
 * @versão 1.0
 */
package com.fatesg.consoftware.academiaapi.controller;

import com.fatesg.consoftware.academiaapi.dto.PlanoDTO;
import com.fatesg.consoftware.academiaapi.service.PlanoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/planos")
@Tag(name = "Planos", description = "Gerenciamento de Planos da Academia")
public class PlanoController {

	@Autowired
	private PlanoService planoService;

	@PostMapping
	public ResponseEntity<PlanoDTO> criarPlano(@RequestBody PlanoDTO planoDTO) {
		return new ResponseEntity<>(planoService.criar(planoDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<PlanoDTO>> listarPlanos() {
		return ResponseEntity.ok(planoService.listarTodos());
	}
}