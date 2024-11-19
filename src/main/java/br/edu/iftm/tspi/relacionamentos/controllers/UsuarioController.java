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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.tspi.relacionamentos.models.Perfil;
import br.edu.iftm.tspi.relacionamentos.models.Usuario;
import br.edu.iftm.tspi.relacionamentos.repositories.PerfilRepository;
import br.edu.iftm.tspi.relacionamentos.repositories.UsuarioRepository;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping
    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> getUsuarioById(@RequestParam String id) {
        return usuarioRepository.findById(id);
    }

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario){
        if(usuario.getPerfil() != null && usuario.getPerfil().getId() == null){
            Perfil perfilSalvo = perfilRepository.save(usuario.getPerfil());
            usuario.setPerfil(perfilSalvo);
        }
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
        return usuarioRepository.findById(id).map(usuarioExistente -> {
            usuarioExistente.setNome(usuario.getNome());

            if(usuario.getPerfil() != null){
                Perfil perfilSalvo = perfilRepository.save(usuario.getPerfil());
                usuarioExistente.setPerfil(perfilSalvo);
            }else{
                usuarioExistente.setPerfil(usuario.getPerfil());
            }
            return usuarioRepository.save(usuarioExistente);
        }).orElseThrow(() -> new RuntimeException("Id de usuário não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable String id){
        usuarioRepository.deleteById(id);
    }
}
