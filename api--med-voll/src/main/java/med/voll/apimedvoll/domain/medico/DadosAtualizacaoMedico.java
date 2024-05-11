package med.voll.apimedvoll.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.apimedvoll.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(

        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {



}
