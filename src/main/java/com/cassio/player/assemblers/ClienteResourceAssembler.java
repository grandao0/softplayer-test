package com.cassio.player.assemblers;

import com.cassio.player.controllers.ClientesApiController;
import com.cassio.player.models.db.Cliente;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ClienteResourceAssembler implements ResourceAssembler<Cliente, Resource<Cliente>> {

    @Override
    public Resource<Cliente> toResource(Cliente cliente) {

        return new Resource<>(cliente,
                linkTo(methodOn(ClientesApiController.class).listarCliente(cliente.getId())).withSelfRel(),
                linkTo(methodOn(ClientesApiController.class).listarClientes()).withRel("clientes"));
    }
}
