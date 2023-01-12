/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UAS_PWS.UAS_PWS;

import UAS_PWS.UAS_PWS.exceptions.NonexistentEntityException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TUF GAMING
 */


@RestController
@CrossOrigin


public class BaranggController {

    Barangg barang = new Barangg();//declare entity
    BaranggJpaController ctrl = new BaranggJpaController();//declare jpa con
    
    List<Barangg> arr = new ArrayList<>();//declare new list var
    
    @ResponseBody
    @RequestMapping("/get")
    public List<Barangg> getTableAll(){
        try {
            return ctrl.findBaranggEntities();// return all data if available
            
        }catch(Exception e){
            return List.of(); //data isnt available
        }
        //return arr;
    }
    
    @ResponseBody
    @RequestMapping("/get/{id}")
    public List<Barangg> getDataById(@PathVariable("id") int id){
        
        try{
            barang = ctrl.findBarangg(id);//get data from entity
            arr.clear();//clear data in list
            arr.add(barang);//fill list
            return arr;//show data
        }catch(Exception e){
            return List.of();
        }
    }
    
    @ResponseBody
    @RequestMapping("/del/{id}")
    public String deleteData(@PathVariable("id") int id){
        
        try {
            ctrl.destroy(id);//delete data
            return id + "telah dihapus!";
        }catch (NonexistentEntityException e){
            return "gagal menghapus!";
        }
    }
    
    @ResponseBody
    @RequestMapping("/post")
    public String saveData(HttpEntity<String> data) throws Exception {//get data barang from list
        
        String message = "no action!";
        //Barangg bbarang = new Barangg();
        
        ObjectMapper map = new ObjectMapper();
        
        barang = map.readValue(data.getBody(), Barangg.class);
        
        try{
            ctrl.create(barang);
            message = "input tersimpan!";
        }catch(Exception e){
            message = "error!";
        }
        
        return message;
    }
    
    @ResponseBody
    @RequestMapping(value="/put", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public String putData(@PathVariable ("id") int id, @RequestBody Barangg data)  {
        
        String message = "no action!";
        //Barangg bbarang = new Barangg();
        
        //ObjectMapper map = new ObjectMapper();
        
        //bbarang = map.readValue(data.getBody(), Barangg.class);
        
        try{
            data.setId(id);//insert data
            ctrl.create(data);//
            message = "update tersimpan!";
        }catch(Exception e){
            message = e.toString() + "error!";
        }
        
        return message;
    }
}
