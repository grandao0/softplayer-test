package com.cassio.player.services;

import com.cassio.player.models.ClienteRequest;
import com.cassio.player.models.ClienteResponse;
import com.cassio.player.models.db.Cliente;
import com.cassio.player.repositories.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;

@Service
public class ClientesService {

    @Inject
    private ClienteRepository clienteRepository;

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    public ResponseEntity<List<ClienteResponse>> findAll() {
        List<ClienteResponse> listaResponse = new ArrayList<>();
        List<Cliente> listaClientes = clienteRepository.findAll();
        if (listaClientes.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            BeanUtils.copyProperties(listaResponse, listaClientes);
        }
        return new ResponseEntity<>(listaResponse, HttpStatus.CREATED);
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).get();
    }

    public ResponseEntity<ClienteResponse> save(ClienteRequest clienteRequest) {
        try {
            Cliente cliente = this.clienteRequestParaCliente(clienteRequest);
            clienteRepository.save(cliente);

            ClienteResponse clienteResponse = new ClienteResponse();
            BeanUtils.copyProperties(clienteResponse, cliente);
            return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Cliente clienteRequestParaCliente(ClienteRequest clienteRequest) {
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(cliente, clienteRequest);
        return cliente;
    }

    public ResponseEntity<Map<String, Object>> update(Cliente product, Long id) {
        try {
            Map<String, Object> result = new HashMap<>();

            Cliente cliente = clienteRepository.findById(id).get();
            cliente.setNome(product.getNome());
            clienteRepository.saveAndFlush(cliente);

            result.put(SUCCESS, true);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> result = new HashMap<>();
            result.put(ERROR, e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> deleteById(Long id) {
        try {
            Map<String, Object> result = new HashMap<>();

            clienteRepository.deleteById(id);
            result.put(SUCCESS, true);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> result = new HashMap<>();
            result.put(ERROR, e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
