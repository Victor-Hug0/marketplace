package br.com.victor.Marketplace.dto.address;

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
