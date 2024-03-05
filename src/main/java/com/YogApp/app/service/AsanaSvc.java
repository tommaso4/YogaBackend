package com.YogApp.app.service;

import com.YogApp.app.Exception.NotFoundException;
import com.YogApp.app.model.entites.Asana;
import com.YogApp.app.model.entites.User;
import com.YogApp.app.model.request.AsanaReq;
import com.YogApp.app.repository.AsanaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void deleteAsana(int id){
        Asana asana = findById(id);
        this.asanaRepo.delete(asana);
    }

}
