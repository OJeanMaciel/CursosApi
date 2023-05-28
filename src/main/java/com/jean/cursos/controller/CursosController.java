package com.jean.cursos.controller;

import com.jean.cursos.domain.AlunoDto;
import com.jean.cursos.domain.CursoDto;
import com.jean.cursos.model.Aluno;
import com.jean.cursos.model.Cursos;
import com.jean.cursos.repository.CursosRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
@AllArgsConstructor
@CrossOrigin("*")
public class CursosController {

    private final CursosRepository cursosRepository;


    @GetMapping
    public List<Cursos> getCursos() {
        return cursosRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Cursos> getCursoById(@PathVariable Long id) {
        return cursosRepository.findById(id);
    }

    @PostMapping
    public ResponseEntity<Cursos> salvarCurso(@RequestBody Cursos curso) {
        Cursos cursoSalvo = cursosRepository.save(curso);
        return ResponseEntity.ok(cursoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCursoById(@PathVariable Long id) {
        Optional<Cursos> cursosOptional = cursosRepository.findById(id);
        if (cursosOptional.isPresent()) {
            cursosRepository.deleteById(id);
            return ResponseEntity.ok("Cursos deletado com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cursos> editarCurso(@PathVariable Long id, @RequestBody CursoDto cursoDto) {
        Optional<Cursos> cursoOptional = cursosRepository.findById(id);
        if (cursoOptional.isPresent()) {
            Cursos cursos = cursoOptional.get();
            cursos.setCurso(cursoDto.getCurso());
            cursos.setCategoria(cursoDto.getCategoria());

            Cursos cursoEditado = cursosRepository.save(cursos);
            return ResponseEntity.ok(cursoEditado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
