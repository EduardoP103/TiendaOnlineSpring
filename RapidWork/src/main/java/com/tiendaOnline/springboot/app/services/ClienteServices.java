package com.tiendaOnline.springboot.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendaOnline.springboot.app.models.entity.Cliente;
import com.tiendaOnline.springboot.app.repository.ClienteRepository;
import javax.transaction.Transactional;


@Service
@Transactional
public class ClienteServices {
	
	@Autowired
	private ClienteRepository repositorio;
	
	public Cliente crear(Cliente cliente) {
		return repositorio.save(cliente);
	}
	
	public List<Cliente> buscarTodo(){
		return (List<Cliente>) repositorio.findAll();
	}
	
	public Cliente buscarPorId(Integer id) {
		return repositorio.findById(id).get();
	}
	
	public Cliente actualizar(Cliente clienteActualizar) {
		Cliente clienteActual = repositorio.findById(clienteActualizar.getId_cliente()).get();
		
		clienteActual.setId_cliente(clienteActualizar.getId_cliente());		
		clienteActual.setApellido(clienteActualizar.getApellido());
		clienteActual.setDireccion(clienteActualizar.getDireccion());
		clienteActual.setDni(clienteActualizar.getDni());
		clienteActual.setEmail(clienteActualizar.getEmail());
		clienteActual.setNombre(clienteActualizar.getNombre());
		
		Cliente clienteActualizado = repositorio.save(clienteActual); 
		return clienteActualizado;
	}
	
	public void eliminarCliente(Integer id) {
		repositorio.deleteById(id);
	}
	
}