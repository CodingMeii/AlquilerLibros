package com.alquilerLibros.alquilerLibros.services;

import com.alquilerLibros.alquilerLibros.entyties.cliente;
import com.alquilerLibros.alquilerLibros.entyties.libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alquilerLibros.alquilerLibros.repositories.clienteRepository;
import com.alquilerLibros.alquilerLibros.repositories.libroRepository;

import java.util.*;

@Service
public class services {
    @Autowired
    //Instaciación el metodo o la interfaz de forma inmediata
    clienteRepository clienteRepo;

    @Autowired
    libroRepository libroRepo;

    public boolean login(int id, String psw){
        List<cliente> clientes = clienteRepo.findAllById(id);

        if (clientes.isEmpty()){
            return false;
        }else if (clientes.get(0).getClave().equals(psw)){
            clientes.get(0).setEstado(true);
            clienteRepo.save(clientes.get(0));
            return true;
        }else{
            return false;
        }
    }

    public boolean logout(int id){
        List<cliente> clientes = clienteRepo.findAllById(id);
        clientes.get(0).setEstado(false);
        clienteRepo.save(clientes.get(0));
        return true;
    }

    public List<cliente> getAllClientes(){
        return clienteRepo.findAll();
    }

    public boolean saveCliente (cliente cli){
        List<cliente> clientes = clienteRepo.findAllById(cli.getId());

        if (clientes.size() == 0){
            clienteRepo.save(cli);
            return true;
        }else if (clientes.get(0).getId() == cli.getId()){
            return false;
        }else {
            return false;
        }
    }

    /*CRUD Libro*/

    //Get all
    public List<libro> getAllLibros(){
        return libroRepo.findAll();
    }

    //Get By id
    public List<libro> getLibro(int libroId){
        return libroRepo.findAllById(libroId);
    }

    //Get all avaliable books
    public List<libro> getAllAvaliable(){
        List<libro> libros = libroRepo.findAll();
        List<libro> salidaLibros = new ArrayList<libro>();

        for (int i=0; i<libros.size(); i++){
            if (libros.get(i).getNDisponible() > 0){
                salidaLibros.add(libros.get(i));
            }
        }

        return salidaLibros;
    }

    //Reserve book
    public float reserveBook(int idLibro, int cant){
        List<libro> libros = libroRepo.findAllById(idLibro);

        if (libros.size() == 0){
            return 0;
        }else{
            if (libros.get(0).getNDisponible() < cant){
                return 0;
            }else{
                int setDispo = libros.get(0).getNDisponible();
                int setReser = libros.get(0).getNReservada();

                libros.get(0).setNDisponible(setDispo - cant);
                libros.get(0).setNReservada(setReser + cant);

                float valor = libros.get(0).getVAlquiler() * cant;

                libroRepo.save(libros.get(0));
                return valor;
            }
        }
    }

    //Post
    public boolean saveLibro (libro lib){
        List<libro> libros = libroRepo.findAllById(lib.getId());

        if (libros.size() == 0){
            libroRepo.save(lib);
            return true;
        }else if (libros.get(0).getId() == lib.getId()){
            return false;
        }else {
            return false;
        }
    }

    //put
    public boolean putLibro(libro lib){
        List<libro> libros = libroRepo.findAllById(lib.getId());

        if (libros.get(0).getId().equals(lib.getId())){
            libroRepo.save(lib);
            return true;
        }else {
            return false;
        }
    }

    //Delete
    public boolean deleteLibro (int libroId){
        List<libro> libros = libroRepo.findAllById(libroId);

        if (libros.get(0).getId().equals(libroId)){
            libroRepo.delete(libros.get(0));
            return true;
        }else {
            return false;
        }
    }
}
