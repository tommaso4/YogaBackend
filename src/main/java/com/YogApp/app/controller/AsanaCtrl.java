package com.YogApp.app.controller;

import com.YogApp.app.model.entites.Asana;
import com.YogApp.app.model.enums.TypeAsana;
import com.YogApp.app.model.request.AsanaReq;
import com.YogApp.app.service.AsanaSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AsanaCtrl {

    @Autowired
    private AsanaSvc asanaSvc;

    @PostMapping("/asana/create")
    public ResponseEntity<CustomResponse> createAsana(@RequestBody @Validated AsanaReq asanaReq, BindingResult result){
        if (result.hasErrors()){
            String err = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).toList().toString();
            return CustomResponse.failure(err, HttpStatus.BAD_REQUEST);
        }
        Asana asana = asanaSvc.createAsana(asanaReq);
        return CustomResponse.success(HttpStatus.CREATED.toString(), asana,HttpStatus.CREATED);
    }

    @GetMapping("asana/getAll")
    public ResponseEntity<CustomResponse> getAll (){
        List<Asana> asanas = asanaSvc.getAll();
        return CustomResponse.success(HttpStatus.OK.toString(),asanas,HttpStatus.OK);
    }

    @DeleteMapping("/asana/delete/{id}")
    public ResponseEntity<CustomResponse> deleteAsana (@PathVariable int id){
        asanaSvc.deleteAsana(id);
        return CustomResponse.emptyResponse("asana with id:"+id+"deleted",HttpStatus.OK);
    }

    @GetMapping("/asana/getByType/{type}")
    public ResponseEntity<CustomResponse> findByType(@PathVariable TypeAsana type){
        List<Asana> asanas = asanaSvc.findByTipe(type);
        return CustomResponse.success(HttpStatus.OK.toString(),asanas,HttpStatus.OK);
    }
}
