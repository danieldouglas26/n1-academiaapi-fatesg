/**
 * ClassName: AlunoService
 * @autor [Daniel Douglas Melo Gomes Neto]
 * @email [danieldouglas26@outlook.com]
 * @data 4 de out. de 2025
 * @versão 1.0
 */
package com.fatesg.consoftware.academiaapi.service;

import com.fatesg.consoftware.academiaapi.dto.AlunoDTO;
import com.fatesg.consoftware.academiaapi.dto.PagamentoDTO;
import com.fatesg.consoftware.academiaapi.entity.Aluno;
import com.fatesg.consoftware.academiaapi.entity.Pagamento;
import com.fatesg.consoftware.academiaapi.entity.Plano;
import com.fatesg.consoftware.academiaapi.repository.AlunoRepository;
import com.fatesg.consoftware.academiaapi.repository.PagamentoRepository;
import com.fatesg.consoftware.academiaapi.repository.PlanoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private PlanoRepository planoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	public AlunoDTO criarAluno(AlunoDTO alunoDTO) {
		if (alunoRepository.findByCpf(alunoDTO.getCpf()).isPresent()) {
			throw new IllegalArgumentException("Erro: CPF duplicado.");
		}
		Aluno aluno = convertToEntity(alunoDTO);
		aluno.setAtivo(true); // Começa como ativo
		Aluno alunoSalvo = alunoRepository.save(aluno);
		return convertToDTO(alunoSalvo);
	}

	public List<AlunoDTO> listarTodos() {
		return alunoRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public AlunoDTO buscarPorId(Integer id) {
		Aluno aluno = alunoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
		return convertToDTO(aluno);
	}

	public AlunoDTO atualizar(Integer id, AlunoDTO alunoDTO) {
		Aluno aluno = alunoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
		// Atualiza os campos, usando o DTO
		aluno.setNome(alunoDTO.getNome());
		aluno.setDataNascimento(alunoDTO.getDataNascimento());
		Plano plano = planoRepository.findById(alunoDTO.getPlanoId())
				.orElseThrow(() -> new EntityNotFoundException("Plano não encontrado"));
		aluno.setPlano(plano);
		Aluno atualizado = alunoRepository.save(aluno);
		return convertToDTO(atualizado);
	}

	public void inativar(Integer id) {
		Aluno aluno = alunoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
		aluno.setAtivo(false);
		alunoRepository.save(aluno);
	}

	public PagamentoDTO registrarPagamento(Integer alunoId, PagamentoDTO pagamentoDTO) {
		Aluno aluno = alunoRepository.findById(alunoId)
				.orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
		Pagamento pagamento = new Pagamento();
		pagamento.setAluno(aluno);
		pagamento.setDataPagamento(LocalDate.now()); // Regra de negócio: data preenchida automaticamente
		pagamento.setValor(aluno.getPlano().getValor()); // Regra de negócio: usar valor do plano
		pagamento.setStatus("PAGO"); // Ele assume que um registro é de um pagamento efetuado

		Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);
		return convertPagamentoToDTO(pagamentoSalvo);
	}

	// Métodos de conversão
	private AlunoDTO convertToDTO(Aluno aluno) {
		AlunoDTO dto = new AlunoDTO();
		dto.setId(aluno.getId());
		dto.setNome(aluno.getNome());
		dto.setCpf(aluno.getCpf());
		dto.setDataNascimento(aluno.getDataNascimento());
		dto.setAtivo(aluno.isAtivo());
		if (aluno.getPlano() != null) {
			dto.setPlanoId(aluno.getPlano().getId());
		}
		return dto;
	}

	private Aluno convertToEntity(AlunoDTO dto) {
		Aluno aluno = new Aluno();
		aluno.setNome(dto.getNome());
		aluno.setCpf(dto.getCpf());
		aluno.setDataNascimento(dto.getDataNascimento());
		if (dto.getPlanoId() != null) {
			Plano plano = planoRepository.findById(dto.getPlanoId())
					.orElseThrow(() -> new EntityNotFoundException("Plano não encontrado"));
			aluno.setPlano(plano);
		}
		return aluno;
	}

	private PagamentoDTO convertPagamentoToDTO(Pagamento pagamento) {
		PagamentoDTO dto = new PagamentoDTO();
		dto.setId(pagamento.getId());
		dto.setDataPagamento(pagamento.getDataPagamento());
		dto.setValor(pagamento.getValor());
		dto.setStatus(pagamento.getStatus());
		dto.setAlunoId(pagamento.getAluno().getId());
		return dto;
	}
}