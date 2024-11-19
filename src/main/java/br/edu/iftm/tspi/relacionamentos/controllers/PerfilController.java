package br.edu.iftm.tspi.relacionamentos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.tspi.relacionamentos.models.Perfil;
import br.edu.iftm.tspi.relacionamentos.repositories.PerfilRepository;

@RestController
@RequestMapping("/perfis")
public class PerfilController {
    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping
    public List<Perfil> getAll() {
        return perfilRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Perfil> getPerfilById(@PathVariable String id) {
        return perfilRepository.findById(id);
    }

    @PostMapping
    public Perfil create(@RequestBody Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    @PutMapping("/{id}")
    public Perfil updatePerfil(@PathVariable String id, @RequestBody Perfil perfil) {
        return perfilRepository.findById(id).map(perfilExistente -> {
            perfilExistente.setBio(perfil.getBio());
            perfilExistente.setAvatarUrl(perfil.getAvatarUrl());
            return perfilRepository.save(perfilExistente);
        }).orElseThrow(() -> new RuntimeException("Id de perfil não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deletePerfil(@PathVariable String id) {
        perfilRepository.deleteById(id);
    }
}
