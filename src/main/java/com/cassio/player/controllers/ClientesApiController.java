package com.cassio.player.controllers;

import com.cassio.player.models.ClienteRequest;
import com.cassio.player.models.ClienteResponse;
import com.cassio.player.services.ClientesService;
import com.cassio.player.validators.ClienteRequestValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@Validated
public class ClientesApiController implements com.cassio.player.controllers.ClientesApi {

    @Inject
    private ClientesService clientesService;

    @Inject
    private ClienteRequestValidator clienteRequestValidator;

    @InitBinder("clienteRequest")
    public void initMerchantOnlyBinder(WebDataBinder binder) {
        binder.addValidators(clienteRequestValidator);
    }

    @Override
    public ResponseEntity<List<ClienteResponse>> listarClientes() {
        return clientesService.findAll();
    }

    @Override
    public ResponseEntity<ClienteResponse> listarCliente(Integer idCliente) {
        return clientesService.findById(idCliente);
    }

    @Override
    public ResponseEntity<ClienteResponse> criarCliente(ClienteRequest cliente) {
        return clientesService.save(cliente);
    }

    @Override
    public ResponseEntity<ClienteResponse> editarCliente(Integer idCliente, ClienteRequest cliente) {
        return clientesService.update(idCliente, cliente);
    }

    @Override
    public ResponseEntity<Void> deletarCliente(Integer idCliente) {
        return clientesService.deleteById(idCliente);
    }
}
