package com.alquilerLibros.alquilerLibros.controllers;

import com.alquilerLibros.alquilerLibros.entyties.cliente;
import com.alquilerLibros.alquilerLibros.entyties.libro;
import com.alquilerLibros.alquilerLibros.services.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/mibiblioteca")
public class controller {
    @Autowired
    services servicios;

    @GetMapping("/login")
    public boolean login(@RequestParam("user") int idCliente, @RequestParam("psw") String psw){
        return servicios.login(idCliente, psw);
    }

    @GetMapping("/logout")
    public boolean logout(@RequestParam("user") int idCliente){
        return servicios.logout(idCliente);
    }

    @GetMapping("/allclients")
    public List<cliente> getAllClientes(){
        return servicios.getAllClientes();
    }

    @PostMapping("/saveclient")
    public boolean saveCliente (@RequestBody cliente cli){
        return servicios.saveCliente(cli);
    }

    // CRUD libro

    @GetMapping("/allbooks")
    public List<libro> getAllLibros(){
        return servicios.getAllLibros();
    }

    @GetMapping("/listbook")
    public List<libro> getLibro(@RequestParam("id") int libroId){
        return  servicios.getLibro(libroId);
    }

    @GetMapping("/avaliablebook")
    public List<libro> getAllAvaliable(){
        return servicios.getAllAvaliable();
    }

    @GetMapping("/reservebook")
    public float reserveBook(@RequestParam("id") int idLibro, @RequestParam("cant") int cantidad){
        return servicios.reserveBook(idLibro, cantidad);
    }

    @PostMapping("/savebook")
    public boolean saveLibro (@RequestBody libro lib){
        return servicios.saveLibro(lib);
    }

    @PutMapping("/updatebook")
    public boolean putLibro(@RequestBody libro lib){
        return servicios.putLibro(lib);
    }

    @DeleteMapping("/deletebook")
    public boolean deleteLibro (@RequestParam("id") int libroId){
        return servicios.deleteLibro(libroId);
    }
}
