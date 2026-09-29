package io.github.joaogabriel0908.icompras.pedidos.client.representation;

public record ClienteRepresentation(
    Long codigo,
    String nome,
    String email,
    String cpf,
    String telefone,
    String logradouro,
    String numero,
    String bairro
) {
}
