package med.voll.apimedvoll.domain.consultas;

import med.voll.apimedvoll.domain.ValidaçãoException;
import med.voll.apimedvoll.domain.medico.MedicoRepository;
import med.voll.apimedvoll.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelamentoConsulta {


    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public void cancelamentoDeConsulta(Long consultaId, MotivoDoCancelamento motivo){

        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new ValidaçãoException("Consulta não encontrada"));

        consulta.setCancelada(true);
        consulta.setMotivoCancelamento(motivo);

        consultaRepository.save(consulta);
    }

}
