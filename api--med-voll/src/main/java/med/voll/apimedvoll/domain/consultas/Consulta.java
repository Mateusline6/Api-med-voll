package med.voll.apimedvoll.domain.consultas;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.apimedvoll.domain.medico.Medico;
import med.voll.apimedvoll.domain.paciente.Paciente;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private MotivoDoCancelamento motivoCancelamento;

    private boolean cancelada;

    public boolean setCancelada(boolean cancelada) {
        return cancelada;
    }

    public MotivoDoCancelamento setMotivoCancelamento(MotivoDoCancelamento motivo) {
        return motivo;
    }
}
