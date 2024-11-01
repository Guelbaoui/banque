package com.example.demo1.services;

import com.example.demo1.dao.CompteRepository;
import com.example.demo1.entities.*;
import jakarta.ws.rs.*;


import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
@Path("/banque")
public class CompteRestJaxRSAPI {
    @Autowired
    private CompteRepository compteRepository;

    //Recuperer tous les comptes
    @Path("/comptes")
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Compte> getComptes(){
        return compteRepository.findAll();
    }
    //recuperer un compte par son identifiant
    @Path("/comptes/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Compte getCompte(@PathParam("id") Long id){
        return compteRepository.findById(id).orElse(null);
    }
    //Ajouter un nouveau compte
    @Path("/comptes")
    @POST
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Compte addCompte(Compte compte){
        return compteRepository.save(compte);
    }
    //Mettre a jour un compte existant
    @Path("/comptes/{id}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Compte updateCompte(@PathParam("id") Long id,Compte compte){
        Compte existingCompte = compteRepository.findById(id).orElse(null);
        if (existingCompte != null){
            existingCompte.setSolde(compte.getSolde());
            existingCompte.setDateCreation(compte.getDateCreation());
            existingCompte.setType(compte.getType());
            return compteRepository.save(existingCompte);
        }
        return null;
    }
    //Supprimer un compte
    @Path("comptes/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public void deleteCompte(@PathParam("id") Long id){
        compteRepository.deleteById(id);
    }
}

