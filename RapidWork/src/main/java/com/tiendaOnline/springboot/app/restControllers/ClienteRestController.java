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
import org.springframework.web.bind.annotation.ResponseBody;
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
	public ResponseEntity <Object> buscarTodo() {
		List<Cliente> listaClientes = servicio.buscarTodo();
		System.out.println("LISTA DE CLIENTES: " + listaClientes);
		return  new ResponseEntity<>(listaClientes, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<Object> buscarPorId(@PathVariable("id") int id) {
		Cliente cliente = servicio.buscarPorId(id);
		if (cliente == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado, el ID proporcionado no es correcto");
		return new ResponseEntity<Object>(cliente, HttpStatus.OK);
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
		)
	public ResponseEntity<Object> crear(@RequestBody Cliente cliente) {
		servicio.crear(cliente);
		return new ResponseEntity<Object>("Cliente creada correctamente", HttpStatus.OK);
	}

	@PutMapping (value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
			)
	public ResponseEntity<Object> actualizar(@PathVariable("id") int id, @RequestBody Cliente cliente) {

		servicio.actualizar(cliente);
		return new ResponseEntity<Object>("Cliente actualizado correctamente", HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") int id) {
		servicio.eliminarCliente(id);
		return new ResponseEntity<Object>("CLiente eliminado correctamente", HttpStatus.OK);
	}
}



