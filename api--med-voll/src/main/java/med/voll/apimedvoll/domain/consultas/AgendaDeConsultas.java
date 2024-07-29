package med.voll.apimedvoll.domain.consultas;


import med.voll.apimedvoll.domain.ValidaçãoException;
import med.voll.apimedvoll.domain.medico.Medico;
import med.voll.apimedvoll.domain.medico.MedicoRepository;
import med.voll.apimedvoll.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;


    public void agendar(DadosAgendamentoConsulta dados){

        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidaçãoException("id do paciente nao existe");
        }
        if (dados .idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidaçãoException("Id do medico não existe");
        }

            var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
            var medico = escolherMedico(dados);
            var consulta = new Consulta(null, medico, paciente, dados.data(), null, false);
            consultaRepository.save(consulta);

    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());

        }
        if (dados.especialidade() ==null){
            throw  new ValidaçãoException("Especialidade obrigatoria");
        }
        return medicoRepository.escolherMedicoAleatorioLivreData(dados.especialidade(), dados.data());
    }

}
