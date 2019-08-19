package com.cassio.player.controllers;

import com.cassio.player.interfaces.RestApiController;
import com.cassio.player.models.ClienteRequest;
import com.cassio.player.models.ClienteResponse;
import com.cassio.player.services.ClientesService;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientesApiController implements RestApiController, com.cassio.player.controllers.ClientesApi {

    @Inject
    private ClientesService clientesService;

    @Override
    public ResponseEntity<ClienteResponse> criarCliente(ClienteRequest cliente) {
        return clientesService.save(cliente);
    }

    @Override
    public ResponseEntity<List<ClienteResponse>> listarClientes() {
        return clientesService.findAll();
    }
}
