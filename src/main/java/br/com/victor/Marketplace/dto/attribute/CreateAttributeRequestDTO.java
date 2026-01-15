package br.com.victor.Marketplace.dto.attribute;

import br.com.victor.Marketplace.entity.enums.AttributeType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateAttributeRequestDTO(
        @NotEmpty(message = "Field companyName can't be empty or null!")
        String name,
        @NotNull(message = "Field type can't be null!")
        AttributeType type,
        @NotEmpty(message = "Field value can't be empty or null!")
        String value
) {
}
