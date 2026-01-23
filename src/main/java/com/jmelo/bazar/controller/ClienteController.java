package com.jmelo.bazar.controller;

import com.jmelo.bazar.model.Cliente;
import com.jmelo.bazar.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {
    @Autowired
    private IClienteService cliService;

    @GetMapping("/bazar/clientes")
    public List<Cliente> getCliente() {
        return cliService.getCliente();
    }

    @PostMapping("/bazar/clientes/crear")
    public String crearcliente(@RequestBody List<Cliente> cli) {
        cliService.saveCliente(cli);
        return "Cliente agregado correctamente";
    }

    @DeleteMapping("/bazar/clientes/delete/{id_cliente}")
    public String deleteCliente(@PathVariable Long id_cliente) {
        cliService.deleteCliente(id_cliente);
        return "Cliente borrado exitosamente";
    }

    @GetMapping("/bazar/clientes/{id_cliente}")
    public Cliente traerCliente(@PathVariable Long id_cliente) {
        return cliService.findCliente(id_cliente);
    }

    @PutMapping("bazar/clientes/edit/{id_cliente}")
    public  Cliente editCliente(@PathVariable Long id_cliente,@RequestBody Cliente cli) {
        cli.setId_cliente(id_cliente);
        return cliService.editCliente(cli);
    }

}
