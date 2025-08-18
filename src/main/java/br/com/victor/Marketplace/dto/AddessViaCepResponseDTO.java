package br.com.victor.Marketplace.dto;

public record AddessViaCepResponseDTO(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String estado,
        String regiao
) {
}
