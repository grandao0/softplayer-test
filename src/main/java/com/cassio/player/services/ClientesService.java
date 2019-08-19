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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {

    @Inject
    private ClienteRepository clienteRepository;

    public ResponseEntity<List<ClienteResponse>> findAll() {
        try {
            List<ClienteResponse> listaResponse = new ArrayList<>();

            List<Cliente> listaClientes = clienteRepository.findAll();

            if (listaClientes.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            listaClientes.forEach(cliente -> {
                ClienteResponse clienteResponse = new ClienteResponse();
                BeanUtils.copyProperties(cliente, clienteResponse);
                listaResponse.add(clienteResponse);
            });
            return new ResponseEntity<>(listaResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ClienteResponse> findById(Integer id) {
        try {
            Optional<Cliente> cliente = clienteRepository.findById(id);
            if (!cliente.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            ClienteResponse clienteResponse = new ClienteResponse();
            BeanUtils.copyProperties(cliente.get(), clienteResponse);
            return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ClienteResponse> save(ClienteRequest clienteRequest) {
        try {
            Cliente cliente = new Cliente();
            BeanUtils.copyProperties(clienteRequest, cliente);
            clienteRepository.save(cliente);

            ClienteResponse clienteResponse = new ClienteResponse();
            BeanUtils.copyProperties(cliente, clienteResponse);
            return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ClienteResponse> update(Integer id, ClienteRequest clienteRequest) {
        try {
            Optional<Cliente> cliente = clienteRepository.findById(id);
            if (!cliente.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            BeanUtils.copyProperties(clienteRequest, cliente);

            clienteRepository.saveAndFlush(cliente.get());

            ClienteResponse clienteResponse = new ClienteResponse();
            BeanUtils.copyProperties(cliente, clienteResponse);
            return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteById(Integer id) {
        try {
            Optional<Cliente> cliente = clienteRepository.findById(id);
            if (!cliente.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            clienteRepository.deleteById(id);

            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
