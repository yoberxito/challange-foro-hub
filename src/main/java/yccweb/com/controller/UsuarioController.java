package yccweb.com.controller;/*
 * Copyright (c) 2025 yober cieza coronel. Todos los derechos reservados.
 *
 * Este archivo es parte de foro-hub.
 *
 * foro-hub es software propietario: no puedes redistribuirlo y/o modificarlo sin el
 * permiso expreso del propietario. Está sujeto a los términos y condiciones
 * que acompañan el uso del software.
 *
 * Cualquier uso no autorizado puede ser sancionado según la ley vigente.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yccweb.com.domain.ususario.DatosAutenticacionUsuario;
import yccweb.com.domain.ususario.Usuario;
import yccweb.com.domain.ususario.UsuarioRepository;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public void crearUsuario(@RequestBody DatosAutenticacionUsuario autenticacionUsuario){
        Optional<Usuario> usuario=usuarioRepository.findByUserAndContrasena(autenticacionUsuario.user(), autenticacionUsuario.clave());
        System.out.println("usuario"+usuario.isPresent());
        if  (!usuario.isPresent() ){
            Usuario usuario1=new Usuario(autenticacionUsuario);
            usuarioRepository.save(usuario1);
        }else {
            throw new RuntimeException("Usuario ya existe");
        }


    }
}
