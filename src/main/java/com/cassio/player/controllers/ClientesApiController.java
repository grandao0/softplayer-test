package com.cassio.player.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cassio.player.models.ClienteRequest;
import com.cassio.player.models.db.Cliente;
import com.cassio.player.services.ClientesService;
import com.cassio.player.validators.ClienteRequestValidator;

@RestController
@Validated
public class ClientesApiController {

	@Inject
	private ClientesService clientesService;

	@Inject
	private ClienteRequestValidator clienteRequestValidator;

	@InitBinder("clienteRequest")
	public void initMerchantOnlyBinder(WebDataBinder binder) {
		binder.addValidators(clienteRequestValidator);
	}

	@GetMapping("/clientes")
	public Resources<Resource<Cliente>> listarClientes() {
		return clientesService.findAll();
	}

	@GetMapping("/clientes/{idCliente}")
	public ResponseEntity<Resource<Cliente>> listarCliente(@PathVariable Integer idCliente) {
		Resource<Cliente> cliente = clientesService.findById(idCliente);
		return ResponseEntity.ok(cliente);
	}

	@PostMapping("/clientes")
	public ResponseEntity<?> criarCliente(@Valid ClienteRequest cliente) throws URISyntaxException {
		Resource<Cliente> resource = clientesService.save(cliente);

		return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
	}

	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> editarCliente(Integer idCliente, @Valid ClienteRequest cliente) throws URISyntaxException {
		Resource<Cliente> resource = clientesService.update(idCliente, cliente);

		return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
	}

	@DeleteMapping("/clientes/{idCliente}")
	public ResponseEntity<?> deletarCliente(@PathVariable Integer idCliente) {
		clientesService.deleteById(idCliente);
		return ResponseEntity.noContent().build();
	}
}
