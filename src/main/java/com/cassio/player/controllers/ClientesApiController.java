package com.cassio.player.controllers;

import com.cassio.player.models.ClienteRequest;
import com.cassio.player.models.db.Cliente;
import com.cassio.player.services.ClientesService;
import com.cassio.player.validators.ClienteRequestValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

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

    @ApiOperation(value = "Retorna os clientes existentes na base de dados.", nickname = "listarClientes", notes = "Retorna os clientes existentes na base de dados - H2.", authorizations = {
            @Authorization(value = "basicAuth")})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Consulta realizada com sucesso."),
            @ApiResponse(code = 204, message = "Não existem clientes cadastrados na base de dados."),
            @ApiResponse(code = 500, message = "Erro ao buscar clientes na base de dados.")})
    @GetMapping("/clientes")
    public Resources<Resource<Cliente>> listarClientes() {
        return clientesService.findAll();
    }

    @ApiOperation(value = "Retorna o cliente existente pelo ID na base de dados.", nickname = "listarCliente", notes = "Retorna o cliente existente pelo ID na base de dados - H2.", authorizations = {
            @Authorization(value = "basicAuth")})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Cliente encontrado e exibido com sucesso."),
            @ApiResponse(code = 404, message = "Cliente não encontrado."),
            @ApiResponse(code = 500, message = "Erro ao buscar o cliente pelo ID na base de dados.")})
    @GetMapping("/clientes/{idCliente}")
    public ResponseEntity<Resource<Cliente>> listarCliente(@PathVariable Integer idCliente) {
        Resource<Cliente> cliente = clientesService.findById(idCliente);

        return ResponseEntity.ok(cliente);
    }

    @ApiOperation(value = "Cria um novo cliente na base de dados.", nickname = "criarCliente", notes = "Cria um novo cliente na base de dados - H2.", authorizations = {
            @Authorization(value = "basicAuth")})
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente criado com sucesso na base de dados."),
            @ApiResponse(code = 500, message = "Erro ao criar cliente na base de dados.")})
    @PostMapping("/clientes")
    public ResponseEntity<?> criarCliente(@Valid ClienteRequest cliente) throws URISyntaxException {
        Resource<Cliente> resource = clientesService.save(cliente);

        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @ApiOperation(value = "Edita o cliente passado como parâmetro (body) na base de dados.", nickname = "editarCliente", notes = "Edita o cliente passado como parâmetro (body) na base de dados - H2.", authorizations = {
            @Authorization(value = "basicAuth")})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Cliente editado com sucesso na base de dados."),
            @ApiResponse(code = 404, message = "Cliente não encontrado na base de dados."),
            @ApiResponse(code = 500, message = "Erro ao editar cliente na base de dados.")})
    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> editarCliente(Integer idCliente, @Valid ClienteRequest cliente) throws URISyntaxException {
        Resource<Cliente> resource = clientesService.update(idCliente, cliente);

        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @ApiOperation(value = "Deleta o cliente existente pelo ID na base de dados.", nickname = "deletarCliente", notes = "Deleta o cliente existente pelo ID na base de dados - H2.", authorizations = {
            @Authorization(value = "basicAuth")})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Cliente deletado com sucesso na base de dados."),
            @ApiResponse(code = 404, message = "Cliente não encontrado na base de dados."),
            @ApiResponse(code = 500, message = "Erro ao deletar o cliente pelo ID na base de dados.")})
    @DeleteMapping("/clientes/{idCliente}")
    public ResponseEntity<?> deletarCliente(@PathVariable Integer idCliente) {
        clientesService.deleteById(idCliente);

        return ResponseEntity.noContent().build();
    }
}
