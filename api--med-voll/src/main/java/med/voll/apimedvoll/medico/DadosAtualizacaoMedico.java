package med.voll.apimedvoll.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.apimedvoll.endereco.DadosEndereco;
import med.voll.apimedvoll.endereco.Endereco;

public record DadosAtualizacaoMedico(

        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {



}
