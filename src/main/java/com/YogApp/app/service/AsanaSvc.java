package com.YogApp.app.service;

import com.YogApp.app.Exception.NotFoundException;
import com.YogApp.app.model.entites.Asana;
import com.YogApp.app.model.entites.User;
import com.YogApp.app.model.enums.TypeAsana;
import com.YogApp.app.model.request.AsanaReq;
import com.YogApp.app.repository.AsanaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AsanaSvc {

    @Autowired
    private AsanaRepo asanaRepo;
    @Autowired
    private UserSvc userSvc;

    public Asana createAsana (AsanaReq asanaReq){
        Asana asana = new Asana();
        User user = userSvc.findUserByUsername("TomAdmin");
        asana.setId(asanaReq.getId());
        asana.setName(asanaReq.getName());
        asana.setDescription(asanaReq.getDescription());
        asana.setDrishti(asanaReq.getDrishti());
        asana.setTypeAsana(asanaReq.getTypeAsana());
        asana.setBenefit(asanaReq.getBenefit());
        asana.setUrlImg(asanaReq.getUrlImg());
        asana.getUser().add(user);
        return asanaRepo.save(asana);
    }

    public List<Asana> getAll (){
        return this.asanaRepo.findAll();
    }
    public Asana findById(int id){
        return this.asanaRepo.findById(id).orElseThrow(() -> new NotFoundException("Asana not found"));
    }
    public void deleteAsana(int idAsana){
        Asana asana = findById(idAsana);
        for (User user : asana.getUser()){
            user.getAsana().remove(asana);
            int idu = user.getId();
            userSvc.refreshUser(idu);
        }
        this.asanaRepo.delete(asana);
    }
    public List<Asana> findByTipe(TypeAsana typeAsana){
        List<Asana> asanas = new ArrayList<>();
        asanas = asanaRepo.findBytypeAsana(typeAsana);
        return asanas;
    }
}
