/**
 * ClassName: PlanoService
 * @autor [Daniel Douglas Melo Gomes Neto]
 * @email [danieldouglas26@outlook.com]
 * @data 4 de out. de 2025
 * @versão 1.0
 */
package com.fatesg.consoftware.academiaapi.service;

import com.fatesg.consoftware.academiaapi.dto.PlanoDTO;
import com.fatesg.consoftware.academiaapi.entity.Plano;
import com.fatesg.consoftware.academiaapi.repository.PlanoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanoService {
    @Autowired
    private PlanoRepository planoRepository;

    public PlanoDTO criar(PlanoDTO planoDTO) {
        Plano plano = convertToEntity(planoDTO);
        return convertToDTO(planoRepository.save(plano));
    }

    public List<PlanoDTO> listarTodos() {
        return planoRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private PlanoDTO convertToDTO(Plano plano) {
        PlanoDTO dto = new PlanoDTO();
        dto.setId(plano.getId());
        dto.setNome(plano.getNome());
        dto.setValor(plano.getValor());
        return dto;
    }

    private Plano convertToEntity(PlanoDTO dto) {
        Plano plano = new Plano();
        plano.setNome(dto.getNome());
        plano.setValor(dto.getValor());
        return plano;
    }
}