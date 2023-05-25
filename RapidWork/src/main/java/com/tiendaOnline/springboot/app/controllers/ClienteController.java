package com.tiendaOnline.springboot.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.tiendaOnline.springboot.app.models.dao.IClienteDao;
import com.tiendaOnline.springboot.app.models.entity.Cliente;
import com.tiendaOnline.springboot.app.services.ClienteServices;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
	@Autowired 
	private ClienteServices servicio;
	@Autowired
	private IClienteDao clienteDao;
	
	
	@RequestMapping( "/listar")
	public String listar (Model model) {
		
		List <Cliente> listaCliente= servicio.buscarTodo();
		System.out.println("LISTACLIENTE: " + listaCliente);
		model.addAttribute("listaCliente", listaCliente);
		return "/listar";
	}
	@RequestMapping(value = "/index")
	public String index(Cliente cliente) {

		return "index";
	}
	
	@RequestMapping( "/form")
	public String crear(Model model) {
		Cliente cliente = new Cliente ();
		model.addAttribute("cliente",cliente);
		return "/form"
				+ "";	
	}
	
	@RequestMapping(value ="/guardar", method= RequestMethod.POST)
	public String crear(@ModelAttribute("cliente") Cliente cliente) {
		    servicio.crear(cliente);
		    return "redirect:/rest/cliente/listar";
		
	}
	
	 @RequestMapping(value ="/actualizar/{id}")
	public ModelAndView editar(@PathVariable(name="id") int id) {
		ModelAndView mav = new ModelAndView("/templates/form");
		Cliente cliente = servicio.buscarPorId(id);
		mav.addObject("cliente",cliente);
		return mav;
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(name="id") int id) {
		servicio.eliminarCliente(id);
		return "redirect:/cliente/listar";
	}

}
