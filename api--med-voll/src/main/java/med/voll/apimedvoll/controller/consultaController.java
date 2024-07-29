package med.voll.apimedvoll.controller;

import jakarta.validation.Valid;
import med.voll.apimedvoll.domain.consultas.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class consultaController {
    @Autowired
    private AgendaDeConsultas agendaDeConsultas;

    @Autowired
    private CancelamentoConsulta cancelamentoConsulta;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        agendaDeConsultas.agendar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(null,null,null,null));
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarConsulta(@PathVariable Long id, @RequestParam MotivoDoCancelamento motivo) {
        cancelamentoConsulta.cancelamentoDeConsulta(id, motivo);
        return ResponseEntity.noContent().build();
    }


}
