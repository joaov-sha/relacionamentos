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

import br.edu.iftm.tspi.relacionamentos.models.Estudante;
import br.edu.iftm.tspi.relacionamentos.repositories.EstudanteRepository;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {
    @Autowired
    private EstudanteRepository estudanteRepository;

    @GetMapping
    public List<Estudante> getAll() {
        return estudanteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Estudante> getEstudanteById(@PathVariable String id) {
        return estudanteRepository.findById(id);
    }

    @PostMapping
    public Estudante create(@RequestBody Estudante estudante) {
        return estudanteRepository.save(estudante);
    }

    @PutMapping("/{id}")
    public Estudante updateEstudante(@PathVariable String id, @RequestBody Estudante estudante) {
        return estudanteRepository.findById(id).map(estudanteExistente -> {
            estudanteExistente.setNome(estudante.getNome());
            return estudanteRepository.save(estudanteExistente);
        }).orElseThrow(() -> new RuntimeException("Id de estudante não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deleteEstudante(@PathVariable String id) {
        estudanteRepository.deleteById(id);
    }
}