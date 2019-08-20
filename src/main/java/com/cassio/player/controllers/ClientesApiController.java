package com.cassio.player.controllers;

import com.cassio.player.models.ClienteRequest;
import com.cassio.player.models.ClienteResponse;
import com.cassio.player.services.ClientesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
public class ClientesApiController implements com.cassio.player.controllers.ClientesApi {

    @Inject
    private ClientesService clientesService;

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
