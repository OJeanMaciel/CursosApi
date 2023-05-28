package com.jean.cursos.repository;

import com.jean.cursos.domain.AlunoDto;
import com.jean.cursos.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query("SELECT a as aluno, c as curso " +
            "FROM Aluno a " +
            "JOIN Cursos c ON a.curso.id = c.id")
    List<Aluno> findAlunosWithCurso();

    @Query("SELECT a as aluno, c as curso " +
            "FROM Aluno a " +
            "JOIN Cursos c ON a.curso.id = c.id WHERE a.id = :id")
    Optional<Aluno> findAlunosById(@Param("id") Long id);
}