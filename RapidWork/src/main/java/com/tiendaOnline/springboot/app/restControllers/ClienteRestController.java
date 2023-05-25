package com.tiendaOnline.springboot.app.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tiendaOnline.springboot.app.models.entity.Cliente;
import com.tiendaOnline.springboot.app.services.ClienteServices;



@RestController
@RequestMapping("/rest/cliente")
public class ClienteRestController {

	@Autowired
	private ClienteServices servicio;

	@GetMapping
	public ResponseEntity<List<Cliente>> buscarTodo() {
		List<Cliente> listClientes = servicio.buscarTodo();
		System.out.println("LISTA DE CLIENTES: " + listClientes);
		return ResponseEntity.ok(listClientes);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> buscarPorId(@PathVariable("id") int id) {
		Cliente cliente = servicio.buscarPorId(id);
		if (cliente == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado, el ID proporcionado no es correcto");
		return ResponseEntity.ok(cliente);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> crear(@RequestBody Cliente cliente) {
		servicio.crear(cliente);
		return ResponseEntity.ok("Cliente creado correctamente");
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> actualizar(@PathVariable("id") int id, @RequestBody Cliente cliente) {
		cliente.setIdCliente(id);
		servicio.actualizar(cliente);
		return ResponseEntity.ok("Cliente actualizado correctamente");
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> eliminar(@PathVariable("id") int id) {
		servicio.eliminarCliente(id);
		return ResponseEntity.ok("Cliente eliminado correctamente");
	}
}



