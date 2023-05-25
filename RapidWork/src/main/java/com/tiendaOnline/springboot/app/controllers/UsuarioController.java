package com.tiendaOnline.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.tiendaOnline.springboot.app.models.dao.IUsuarioDao;
import com.tiendaOnline.springboot.app.models.entity.Usuario;

@Controller
@SessionAttributes("usuario")
public class UsuarioController {

	@Autowired
	private IUsuarioDao usuarioDao;

	@RequestMapping(value = "/listarUsuario", method = RequestMethod.GET)
	public String listarUsuario(Model model) {
		model.addAttribute("titulo", "Listado de Usuarios");
		model.addAttribute("usuario", usuarioDao.findAll());
		return "listarUsuario";
	}
	
	@RequestMapping(value = "/formUsuario")
	public String crearUsuario(Map<String, Object> model) {

		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("titulo", "Registrar cliente");
		model.put("subTitulo", "Registar");
		
		return "formUsuario";
	}

	@RequestMapping(value = "/formUsuario/{id}")
	public String editarUsuario(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Usuario usuario = null;
		if (id > 0) {
			usuario = usuarioDao.findOne(id);
		} else {
			return "redirect:/listarUsuario";
		}
		model.put("usuario", usuario);
		model.put("titulo", "Editar Usuario");
		model.put("subTitulo", "Editar");
		
		return "formUsuario";
	}

	@RequestMapping(value = "/formUsuario", method = RequestMethod.POST)
	public String guardarUsuario(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("tituloUsuario", "Formulario de Usuario");
			return "formUsuario";
		}

		usuarioDao.save(usuario);
		status.setComplete();
		return "redirect:listarUsuario";
	}

	@RequestMapping(value = "/eliminarUsuario/{id}")
	public String eliminarUsuario(@PathVariable(value = "id") Long id) {

		if (id > 0) {
			usuarioDao.delete(id);
		}
		return "redirect:/listarUsuario";
	}
}