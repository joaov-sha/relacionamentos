package br.edu.iftm.tspi.relacionamentos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.iftm.tspi.relacionamentos.models.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    
}
