package com.YogApp.app.repository;

import com.YogApp.app.model.entites.Asana;
import com.YogApp.app.model.enums.TypeAsana;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsanaRepo extends JpaRepository<Asana, Integer> {
    public List<Asana> findBytypeAsana(TypeAsana typeAsana);
}
