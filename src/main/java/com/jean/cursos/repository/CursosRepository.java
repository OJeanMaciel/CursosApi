package com.jean.cursos.repository;

import com.jean.cursos.model.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursosRepository extends JpaRepository<Cursos, Long> {

    Optional<Cursos> findById(String curso);
}
