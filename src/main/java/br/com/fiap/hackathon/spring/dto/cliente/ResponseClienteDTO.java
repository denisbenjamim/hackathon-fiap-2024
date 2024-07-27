package br.com.fiap.hackathon.spring.dto.cliente;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseClienteDTO(
   @JsonProperty("id_cliente")  UUID codigo
) {}
