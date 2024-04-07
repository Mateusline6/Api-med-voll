package med.voll.apimedvoll.controller;

import jakarta.validation.Valid;
import med.voll.apimedvoll.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
//commit

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriComponentsBuilder ){
        var paciente = new Paciente(dados);
        repository.save(paciente);
        var uri = uriComponentsBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listarPaciente(@PageableDefault(size = 10, sort = {"nome"})Pageable pagina){
        var page = repository.findAllByAtivoTrue(pagina).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizaInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));

    }@DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarMedico(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharMedico(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

}
