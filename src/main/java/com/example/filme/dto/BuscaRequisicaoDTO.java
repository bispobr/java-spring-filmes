package com.example.filme.dto;

import jakarta.validation.constraints.NotBlank;

public record BuscaRequisicaoDTO(@NotBlank String busca) {
}
