package br.com.fiap.hackathon.spring.dto.cliente;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseClienteDTO(
   @JsonProperty("id_cliente")  String codigo
) {}
