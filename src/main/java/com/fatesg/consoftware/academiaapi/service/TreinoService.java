/**
 * ClassName: TreinoService
 * @autor [Daniel Douglas Melo Gomes Neto]
 * @email [danieldouglas26@outlook.com]
 * @data 4 de out. de 2025
 * @versão 1.0
 */
package com.fatesg.consoftware.academiaapi.service;

import com.fatesg.consoftware.academiaapi.dto.TreinoDTO;
import com.fatesg.consoftware.academiaapi.entity.Aluno;
import com.fatesg.consoftware.academiaapi.entity.Treino;
import com.fatesg.consoftware.academiaapi.repository.AlunoRepository;
import com.fatesg.consoftware.academiaapi.repository.TreinoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreinoService {

	@Autowired
	private TreinoRepository treinoRepository;
	@Autowired
	private AlunoRepository alunoRepository;

	public TreinoDTO criar(TreinoDTO dto) {
		Treino treino = convertToEntity(dto);
		return convertToDTO(treinoRepository.save(treino));
	}

	public List<TreinoDTO> listarTodos() {
		return treinoRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public void associarAluno(Integer treinoId, Integer alunoId) {
		Treino treino = treinoRepository.findById(treinoId)
				.orElseThrow(() -> new EntityNotFoundException("Treino não encontrado"));
		Aluno aluno = alunoRepository.findById(alunoId)
				.orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));

		if (!aluno.getTreinos().contains(treino)) {
			aluno.getTreinos().add(treino);
			alunoRepository.save(aluno);
		}
	}

	public void deletar(Integer id) {
		Treino treino = treinoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Treino não encontrado"));
		if (treino.getAlunos() != null && !treino.getAlunos().isEmpty()) {
			throw new IllegalStateException("Não é possível remover treinos que ainda estejam associados a alunos.");
		}
		treinoRepository.delete(treino);
	}

	private TreinoDTO convertToDTO(Treino treino) {
		TreinoDTO dto = new TreinoDTO();
		dto.setId(treino.getId());
		dto.setNome(treino.getNome());
		dto.setDescricao(treino.getDescricao());
		dto.setNivel(treino.getNivel());
		return dto;
	}

	private Treino convertToEntity(TreinoDTO dto) {
		Treino treino = new Treino();
		treino.setNome(dto.getNome());
		treino.setDescricao(dto.getDescricao());
		treino.setNivel(dto.getNivel());
		return treino;
	}
}