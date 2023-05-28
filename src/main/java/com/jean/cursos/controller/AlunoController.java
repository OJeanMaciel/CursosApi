package com.jean.cursos.controller;

import com.jean.cursos.domain.AlunoDto;
import com.jean.cursos.model.Aluno;
import com.jean.cursos.model.Cursos;
import com.jean.cursos.repository.AlunoRepository;
import com.jean.cursos.repository.CursosRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/aluno")
@AllArgsConstructor
@CrossOrigin("*")
public class AlunoController {
    private final AlunoRepository alunoRepository;
    private final CursosRepository cursosRepository;

    @GetMapping("/alunobycurso")
    public List<AlunoDto> listAlunoByCurso() {
        List<AlunoDto> alunosDTO = new ArrayList<>();
        List<Aluno> alunos = alunoRepository.findAlunosWithCurso();

        for (Aluno aluno : alunos) {
            Cursos curso = aluno.getCurso();

            AlunoDto alunoDTO = new AlunoDto();
            alunoDTO.setId(aluno.getId());
            alunoDTO.setNome(aluno.getNome());
            alunoDTO.setEmail(aluno.getEmail());
            alunoDTO.setTelefone(aluno.getTelefone());
            alunoDTO.setCep(aluno.getCep());
            alunoDTO.setCidade(aluno.getCidade());
            alunoDTO.setBairro(aluno.getBairro());
            alunoDTO.setRua(aluno.getRua());
            if (curso != null) {
                alunoDTO.setCategoria(curso.getCategoria());
                alunoDTO.setCurso(curso.getCurso());
            }

            alunosDTO.add(alunoDTO);
        }

        return alunosDTO;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id) {
        Optional<Aluno> alunoOptional = alunoRepository.findAlunosById(id);
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlunoById(@PathVariable Long id) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if (alunoOptional.isPresent()) {
            alunoRepository.deleteById(id);
            return ResponseEntity.ok("Aluno deletado com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody AlunoDto alunoDto) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoDto.getNome());
        aluno.setEmail(alunoDto.getEmail());
        aluno.setTelefone(alunoDto.getTelefone());
        aluno.setCep(alunoDto.getCep());
        aluno.setCidade(alunoDto.getCidade());
        aluno.setBairro(alunoDto.getBairro());
        aluno.setRua(alunoDto.getRua());

        // Verifica se o ID do curso foi fornecido
        if (alunoDto.getCurso() != null) {
            // Consulta o curso pelo ID no banco de dados
            Optional<Cursos> cursoOptional = cursosRepository.findById(alunoDto.getCurso());
            if (cursoOptional.isPresent()) {
                Cursos curso = cursoOptional.get();
                aluno.setCurso(curso);
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        Aluno alunoCadastrado = alunoRepository.save(aluno);
        return ResponseEntity.ok(alunoCadastrado);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> editarAluno(@PathVariable Long id, @RequestBody AlunoDto alunoDto) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            aluno.setNome(alunoDto.getNome());
            aluno.setEmail(alunoDto.getEmail());
            aluno.setTelefone(alunoDto.getTelefone());
            aluno.setCep(alunoDto.getCep());
            aluno.setCidade(alunoDto.getCidade());
            aluno.setBairro(alunoDto.getBairro());
            aluno.setRua(alunoDto.getRua());

            // Verifica se o ID do curso foi fornecido
            if (alunoDto.getCurso() != null) {
                // Consulta o curso pelo ID no banco de dados
                Optional<Cursos> cursoOptional = cursosRepository.findById(alunoDto.getCurso());
                if (cursoOptional.isPresent()) {
                    Cursos curso = cursoOptional.get();
                    aluno.setCurso(curso);
                } else {
                    return ResponseEntity.badRequest().build();
                }
            }

            Aluno alunoEditado = alunoRepository.save(aluno);
            return ResponseEntity.ok(alunoEditado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
