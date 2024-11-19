package br.edu.iftm.tspi.relacionamentos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.iftm.tspi.relacionamentos.models.Postagem;

public interface PostagemRepository extends MongoRepository<Postagem, String> {
    
}
