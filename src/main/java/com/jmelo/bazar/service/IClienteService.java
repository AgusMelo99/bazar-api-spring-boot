package com.jmelo.bazar.service;

import com.jmelo.bazar.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IClienteService {

    public List<Cliente> getCliente();

    public void saveCliente(List<Cliente> cli);

    public void deleteCliente(Long id);

    public Cliente findCliente(Long id);

    public Cliente editCliente(Cliente cli);
}

