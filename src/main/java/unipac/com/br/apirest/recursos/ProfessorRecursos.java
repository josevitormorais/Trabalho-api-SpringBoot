package unipac.com.br.apirest.recursos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import unipac.com.br.apirest.model.domain.Professor;
import unipac.com.br.apirest.model.repository.ProfessorRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
    @RequestMapping("/professores")
public class ProfessorRecursos {
    @Autowired
    private ProfessorRepository professorRepositorio;

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessor(@PathVariable("id") Long id){
        Optional<Professor> professor = professorRepositorio.findById(id);

        if(!professor.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(professor.get());

    }

    @GetMapping
    public ResponseEntity<List<Professor>> getProfessorList() {
        List<Professor> professors = professorRepositorio.findAll();

        if(professors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(professors);
    }

    @PostMapping
    public ResponseEntity<Professor> addProfessor(@RequestBody Professor professor){
        log.info("Inserting professors" + professor.toString());

        Professor saved = professorRepositorio.save(professor);

        URI url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(url).body(saved);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable("id") Long id, @RequestBody Professor novoProfessor ) {
        Optional<Professor> professor = professorRepositorio.findById(id);

        if(professor.isPresent()) {
            Professor professorParaUpdate = professor.get();
            professorParaUpdate.setNome(novoProfessor.getNome());

            professorRepositorio.save(professorParaUpdate);

            return ResponseEntity.ok(professorParaUpdate);

        }else  {
            log.info("Professor nao pode ser alterado: " + professor.toString());
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable("id") Long id) {
        try {
            professorRepositorio.deleteById(id);
            return ResponseEntity.ok("Dados deletados");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Dados nao pode ser deletados" + e.getMessage());
        }

    }

}
