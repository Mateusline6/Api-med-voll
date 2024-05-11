package med.voll.apimedvoll.domain.paciente;

import med.voll.apimedvoll.domain.endereco.Endereco;

public record DadosDetalhamentoPaciente(

        Long id,
        String nome,
        String email,
        String telfone,
        String cpf,
        Endereco endereco

        ) {

    public DadosDetalhamentoPaciente(Paciente paciente){

        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());

    }

}
