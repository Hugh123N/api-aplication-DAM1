package com.api.api.service.impl;

import com.api.api.dto.UsuarioDTO;
import com.api.api.model.Usuario;
import com.api.api.repository.IUsuarioRepository;
import com.api.api.service.IUsuarioService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

//////LOGICA DE NEGOCIO (VALIDACIONES, COMBERTIR OBJETOS, PROCESAR INFORMACION)
@AllArgsConstructor
@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private IUsuarioRepository usuarioRepository;
    private ModelMapper mapper;


    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Integer id) {
         return usuarioRepository.findById(id).orElseThrow(ResolutionException::new);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(UsuarioDTO u, Integer id) {

        Usuario usuario=findById(id);
        mapper.map(u,usuario);
        return usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
