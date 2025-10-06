/**
 * ClassName: Pagamento
 * @autor [Daniel Douglas Melo Gomes Neto]
 * @email [danieldouglas26@outlook.com]
 * @data 4 de out. de 2025
 * @versão 1.0
 */
package com.fatesg.consoftware.academiaapi.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Pagamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDate dataPagamento;
	private Double valor;
	private String status; // PAGO, PENDENTE, ATRASADO

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aluno_id", nullable = false)
	private Aluno aluno;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
}