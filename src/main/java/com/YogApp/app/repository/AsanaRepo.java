package com.YogApp.app.repository;

import com.YogApp.app.model.entites.Asana;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsanaRepo extends JpaRepository<Asana, Integer> {
}
