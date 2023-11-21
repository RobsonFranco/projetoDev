package com.fourcatsdev.projetoDev.controller;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fourcatsdev.projetoDev.entity.Permissoes;
import com.fourcatsdev.projetoDev.entity.Usuario;
import com.fourcatsdev.projetoDev.repository.PermissoesRepository;
import com.fourcatsdev.projetoDev.repository.UsuarioRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PermissoesRepository repPermissoes;

	@GetMapping("/novo")
	public String adicionarUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "/publica-criar-usuario";
	}

	@PostMapping("/salvar")
	public String salvarUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes, Model model) {
		if (result.hasErrors()) {
			return "/publica-criar-usuario";
		}
		
		Usuario verificaLogin = usuarioRepository.findByLogin(usuario.getLogin()); 
		if(verificaLogin!=null){
			model.addAttribute("loginExiste", "Login já cadastrado!");
			return "/publica-criar-usuario";
		}
		
		Permissoes permissoes = repPermissoes.findByPermissoes("USER");
		List<Permissoes> listapermissoes = new ArrayList<Permissoes>();
		listapermissoes.add(permissoes);
		usuario.setPermissoes(listapermissoes);
		
		
		usuarioRepository.save(usuario);
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
		return "redirect:/usuario/novo";
	}

	@RequestMapping("/listar")
	public String listarUsuario(Model model) {
		model.addAttribute("usuarios", usuarioRepository.findAll());
		return "/auth/admin/admin-listar-usuario";
	}

	@GetMapping("/admin/apagar/{id}")
	public String deletarUsuario(@PathVariable("id") long id,Model model, RedirectAttributes attributes) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id invalido:" + id));
		usuarioRepository.delete(usuario);
		attributes.addFlashAttribute("mensagem", "Usuário deletado com sucesso!");
		return "redirect:/usuario/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String updateUsuario(@PathVariable("id") long id,Model model) {
		Optional<Usuario> usuarioVelho = usuarioRepository.findById(id);
		if(!usuarioVelho.isPresent()) {
			throw new IllegalArgumentException("Usuário Inválido:" + id);
		}
		Usuario usuario = usuarioVelho.get();
		model.addAttribute("usuario", usuario);
		return "/auth/user/user-alterar-usuario";
	}

	@PostMapping("/editar/{id}")
	public String updateUsuario(@PathVariable("id") long id, @Valid Usuario usuario, BindingResult result) {
		if (result.hasErrors()) {
			usuario.setId(id);
			return "/auth/user/user-alterar-usuario";
		}
		usuarioRepository.save(usuario);
		return "redirect:/usuario/listar";
		
	}
	
}
