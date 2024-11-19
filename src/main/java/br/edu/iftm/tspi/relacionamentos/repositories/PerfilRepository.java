package br.edu.iftm.tspi.relacionamentos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.iftm.tspi.relacionamentos.models.Perfil;

public interface PerfilRepository extends MongoRepository<Perfil, String>{
    
}
