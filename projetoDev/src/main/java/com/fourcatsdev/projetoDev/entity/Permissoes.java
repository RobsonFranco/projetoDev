package com.fourcatsdev.projetoDev.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Permissoes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String permissoes;
	
	//Muitas Permissões para muitos usuarios
	@ManyToMany(mappedBy = "permissoes", fetch = FetchType.EAGER)
	private List<Usuario> usuario;
	
	
	public Permissoes() {
	}

	public Permissoes(String permissoes) {
		super();
		this.permissoes = permissoes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(String permissoes) {
		this.permissoes = permissoes;
	}

	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}

	
}
