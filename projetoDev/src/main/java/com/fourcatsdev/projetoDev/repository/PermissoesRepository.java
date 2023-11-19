package com.fourcatsdev.projetoDev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourcatsdev.projetoDev.entity.Permissoes;

public interface PermissoesRepository extends JpaRepository<Permissoes, Long> {

	Permissoes findByPermissoes(String permissoes);
}
