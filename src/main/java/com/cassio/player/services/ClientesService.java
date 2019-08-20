package com.cassio.player.services;

import com.cassio.player.constants.ServiceConstants;
import com.cassio.player.models.ClienteRequest;
import com.cassio.player.models.ClienteResponse;
import com.cassio.player.models.db.Cliente;
import com.cassio.player.repositories.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
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
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            listaClientes.forEach(cliente -> {
                ClienteResponse clienteResponse = new ClienteResponse();
                BeanUtils.copyProperties(cliente, clienteResponse);
                listaResponse.add(clienteResponse);
            });
            return new ResponseEntity<>(listaResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ClienteResponse> findById(Integer id) {
        try {
            Optional<Cliente> cliente = clienteRepository.findById(id);
            if (!cliente.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            ClienteResponse clienteResponse = new ClienteResponse();
            BeanUtils.copyProperties(cliente.get(), clienteResponse);
            return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<ClienteResponse> save(ClienteRequest clienteRequest) {
        try {
            Cliente cliente = new Cliente();
            BeanUtils.copyProperties(clienteRequest, cliente);
            cliente.setDataNascimento(LocalDate.parse(clienteRequest.getDataNascimento(), ServiceConstants.formatter));

            clienteRepository.save(cliente);

            ClienteResponse clienteResponse = new ClienteResponse();
            BeanUtils.copyProperties(cliente, clienteResponse);
            return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<ClienteResponse> update(Integer id, ClienteRequest clienteRequest) {
        try {
            Optional<Cliente> cliente = clienteRepository.findById(id);
            if (!cliente.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Cliente clienteUpdate = cliente.get();
            BeanUtils.copyProperties(clienteRequest, clienteUpdate);
            clienteUpdate.setDataNascimento(LocalDate.parse(clienteRequest.getDataNascimento(), ServiceConstants.formatter));

            clienteRepository.saveAndFlush(clienteUpdate);

            ClienteResponse clienteResponse = new ClienteResponse();
            BeanUtils.copyProperties(clienteUpdate, clienteResponse);
            return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<Void> deleteById(Integer id) {
        try {
            Optional<Cliente> cliente = clienteRepository.findById(id);
            if (!cliente.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            clienteRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
