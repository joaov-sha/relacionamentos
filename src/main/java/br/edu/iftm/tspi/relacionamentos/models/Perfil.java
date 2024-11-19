package br.edu.iftm.tspi.relacionamentos.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "perfis")
public class Perfil {
    
    @Id
    private String id;
    private String bio;
    private String avatarUrl;
}