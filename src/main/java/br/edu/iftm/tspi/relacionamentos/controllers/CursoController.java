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

import br.edu.iftm.tspi.relacionamentos.models.Curso;
import br.edu.iftm.tspi.relacionamentos.repositories.CursoRepository;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<Curso> getAll() {
        return cursoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Curso> getCursoById(@PathVariable String id) {
        return cursoRepository.findById(id);
    }

    @PostMapping
    public Curso create(@RequestBody Curso curso) {
        return cursoRepository.save(curso);
    }

    @PutMapping("/{id}")
    public Curso updateCurso(@PathVariable String id, @RequestBody Curso curso) {
        return cursoRepository.findById(id).map(cursoExistente -> {
            cursoExistente.setNome(curso.getNome());
            return cursoRepository.save(cursoExistente);
        }).orElseThrow(() -> new RuntimeException("Id de curso não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deleteCurso(@PathVariable String id) {
        cursoRepository.deleteById(id);
    }
}