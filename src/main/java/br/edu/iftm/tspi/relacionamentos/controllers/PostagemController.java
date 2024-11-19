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

import br.edu.iftm.tspi.relacionamentos.models.Postagem;
import br.edu.iftm.tspi.relacionamentos.repositories.PostagemRepository;

@RestController
@RequestMapping("/postagens")
public class PostagemController {
    @Autowired
    private PostagemRepository postagemRepository;

    @GetMapping
    public List<Postagem> getAll() {
        return postagemRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Postagem> getPostagemById(@PathVariable String id) {
        return postagemRepository.findById(id);
    }

    @PostMapping
    public Postagem create(@RequestBody Postagem postagem) {
        return postagemRepository.save(postagem);
    }

    @PutMapping("/{id}")
    public Postagem updatePostagem(@PathVariable String id, @RequestBody Postagem postagem) {
        return postagemRepository.findById(id).map(postagemExistente -> {
            postagemExistente.setTitulo(postagem.getTitulo());
            postagemExistente.setConteudo(postagem.getConteudo());
            return postagemRepository.save(postagemExistente);
        }).orElseThrow(() -> new RuntimeException("Id de postagem não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deletePostagem(@PathVariable String id) {
        postagemRepository.deleteById(id);
    }
}
