package com.YogApp.app.repository;

import com.YogApp.app.model.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

//    metodo per aggiungere una posizione ad un utente

//    metodo per avere tutte le posizioni di un utente
}
