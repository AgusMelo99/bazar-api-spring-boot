package com.jmelo.bazar.service;

import com.jmelo.bazar.model.Cliente;
import com.jmelo.bazar.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository cliRepo;

    @Override
    public List<Cliente> getCliente() {
        return cliRepo.findAll();
    }

    @Override
    public void saveCliente(List<Cliente> cli) {
        cliRepo.saveAll(cli);
    }

    @Override
    public void deleteCliente(Long id) {
        cliRepo.deleteById(id);

    }

    @Override
    public Cliente findCliente(Long id) {
        return cliRepo.findById(id).orElseThrow(() -> new RuntimeException("Cliente inexistente"));
    }

    @Override
    @Transactional
    public Cliente editCliente(Cliente cli) {
        return cliRepo.findById(cli.getId_cliente())
                .map(cliente -> { return cliRepo.save(cli);
                })
                .orElseThrow(()->new RuntimeException("No se puede editar: Cliente inexistente"));
    }
}
