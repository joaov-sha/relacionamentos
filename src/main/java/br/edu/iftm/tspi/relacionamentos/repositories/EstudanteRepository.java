package br.edu.iftm.tspi.relacionamentos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.iftm.tspi.relacionamentos.models.Estudante;

public interface EstudanteRepository extends MongoRepository<Estudante, String>{
    
}
