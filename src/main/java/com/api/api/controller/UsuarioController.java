package com.api.api.controller;

import com.api.api.dto.UsuarioDTO;
import com.api.api.model.Usuario;
import com.api.api.service.IUsuarioService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@RestController
public class UsuarioController {
    /** + enter
     *
     */
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/usuarios")
    public List<Usuario> findAll(){
        return usuarioService.findAll();
    }
    @GetMapping("/{id}")
    public Usuario findById(@PathVariable Integer id){
        return usuarioService.findById(id);
    }
    @PostMapping("/save")
    public Usuario save( @RequestBody Usuario usuario){
        return usuarioService.save(usuario);
    }
    @PutMapping("/update/{id}")
    public Usuario update(@RequestBody UsuarioDTO u, @PathVariable Integer id){
        return usuarioService.update(u,id);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        usuarioService.delete(id);
    }
    //INICIO SESION requestParam es para que los variables suban automaticamente a la sentencia
    //  -->  /login?email=dato&password=dato
    //@PostMapping("/login")
    //public Usuario findByEmail(@RequestParam String email,@RequestParam String password){
      //  return usuarioService.findByEmailAndPassword(email,password);
    //}
    @PostMapping("/login")
    public ResponseEntity<String> findByEmail(@RequestBody Usuario usuario){
        Optional<Usuario> userOptional=usuarioService.findByEmail(usuario.getEmail());
        if(userOptional.isPresent())
            return ResponseEntity.ok("Inicio de sesion exitoso");
        else
            return ResponseEntity.status(401).body("Credenciales inválidas");
    }

}
