package com.fourcatsdev.projetoDev.componentes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fourcatsdev.projetoDev.entity.Permissoes;
import com.fourcatsdev.projetoDev.repository.PermissoesRepository;

@Component
public class CarregadoraDados implements CommandLineRunner{

	@Autowired
	private PermissoesRepository repPermissoes;
	
	@Override
	public void run(String... args) throws Exception {
		
		String[] permissoes = {"ADMIN","USER","BIBLIOTECARIO"};
		
		for(String permissaoLista: permissoes) {
			Permissoes permissao = repPermissoes.findByPermissoes(permissaoLista);
			if(permissao == null) {
				permissao = new Permissoes(permissaoLista);
				repPermissoes.save(permissao);
			}
			
		}
		
		
	}

}
