package com.fourcatsdev.projetoDev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourcatsdev.projetoDev.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
