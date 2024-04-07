package med.voll.apimedvoll.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.apimedvoll.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(

        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco


) {
}
