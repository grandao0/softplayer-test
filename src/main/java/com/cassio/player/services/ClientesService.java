package com.cassio.player.services;

import com.cassio.player.assemblers.ClienteResourceAssembler;
import com.cassio.player.constants.MessageConstants;
import com.cassio.player.constants.ServiceConstants;
import com.cassio.player.controllers.ClientesApiController;
import com.cassio.player.exceptions.ClienteNaoEncontradoException;
import com.cassio.player.exceptions.ClientesNaoEncontradosException;
import com.cassio.player.exceptions.ErroInesperadoException;
import com.cassio.player.models.ClienteRequest;
import com.cassio.player.models.enums.Sexo;
import com.cassio.player.models.db.Cliente;
import com.cassio.player.repositories.ClienteRepository;
import com.cassio.player.utils.Messages;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class ClientesService {

    @Inject
    private ClienteRepository clienteRepository;

    @Inject
    private ClienteResourceAssembler assembler;

    @Inject
    private Messages messages;

    public Resources<Resource<Cliente>> findAll() {
        List<Resource<Cliente>> listaClientes = clienteRepository.findAll()
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        if (listaClientes.isEmpty()) {
            throw new ClientesNaoEncontradosException(messages.get(MessageConstants.ERRO_BUSCAR_CLIENTES_LISTA_VAZIA));
        }

        try {
            return new Resources<>(listaClientes, linkTo(methodOn(ClientesApiController.class).listarClientes()).withSelfRel());
        } catch (Exception e) {
            throw new ErroInesperadoException(messages.get(MessageConstants.ERRO_BUSCAR_CLIENTES));
        }
    }

    public Resource<Cliente> findById(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));
        try {
            return assembler.toResource(cliente);
        } catch (Exception e) {
            throw new ErroInesperadoException(messages.get(MessageConstants.ERRO_BUSCAR_CLIENTE_POR_ID));
        }
    }

    @Transactional
    public Resource<Cliente> save(ClienteRequest clienteRequest) {
        try {
            Cliente cliente = new Cliente();
            BeanUtils.copyProperties(clienteRequest, cliente);
            cliente.setDataNascimento(LocalDate.parse(clienteRequest.getDataNascimento(), ServiceConstants.formatter));
            if (clienteRequest.getSexo() != null) {
                cliente.setSexo(Sexo.valueOf(clienteRequest.getSexo()));
            }

            clienteRepository.save(cliente);

            return assembler.toResource(cliente);
        } catch (Exception e) {
            throw new ErroInesperadoException(messages.get(MessageConstants.ERRO_SALVAR_CLIENTE));
        }
    }

    @Transactional
    public Resource<Cliente> update(Integer id, ClienteRequest clienteRequest) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (!cliente.isPresent()) {
            throw new ClienteNaoEncontradoException(id);
        }

        try {
            Cliente clienteUpdate = cliente.get();
            BeanUtils.copyProperties(clienteRequest, clienteUpdate);
            clienteUpdate.setDataNascimento(LocalDate.parse(clienteRequest.getDataNascimento(), ServiceConstants.formatter));
            if (clienteRequest.getSexo() != null) {
                clienteUpdate.setSexo(Sexo.valueOf(clienteRequest.getSexo()));
            }

            clienteRepository.saveAndFlush(clienteUpdate);

            return assembler.toResource(clienteUpdate);
        } catch (Exception e) {
            throw new ErroInesperadoException(messages.get(MessageConstants.ERRO_SALVAR_CLIENTE));
        }
    }

    @Transactional
    public void deleteById(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (!cliente.isPresent()) {
            throw new ClienteNaoEncontradoException(id);
        }

        try {
            clienteRepository.deleteById(id);
        } catch (Exception e) {
            throw new ErroInesperadoException(messages.get(MessageConstants.ERRO_DELETAR_CLIENTE));
        }
    }
}
