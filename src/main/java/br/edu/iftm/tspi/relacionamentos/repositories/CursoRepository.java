package br.edu.iftm.tspi.relacionamentos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.iftm.tspi.relacionamentos.models.Curso;

public interface CursoRepository extends MongoRepository<Curso, String> {
    
}
