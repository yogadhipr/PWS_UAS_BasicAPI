/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PWS.Project2.PWS;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TUF GAMING
 */

@RestController
public class ProductServiceController {
    private static Map<String, Product> productRepo = new HashMap<>();//menggunakan HashMap untuk menyimpan Product
    static{
        Product honey = new Product(); //membuat product baru dan memanggil file Product.java
        honey.setId("1"); //membuat id product
        honey.setName("Honey"); //membuat nama product
        honey.setHarga("Rp 135.000");
        honey.setStock("24");
        productRepo.put(honey.getId(), honey); //memasukkan product baru ke HashMap
        
        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        almond.setHarga("Rp 95.000");
        almond.setStock("153");
        productRepo.put(almond.getId(), almond);    
    }
    
    //DELETE API
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        if(!productRepo.containsKey(id)){
        
        return new ResponseEntity<>("product could not be found", HttpStatus.NOT_FOUND);
        }
      productRepo.remove(id); //menghapus product menggunakan id yg tersimpan di HashMap
      return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK); //popup berhasil product didelete
    }  
    //PUT API 
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
        if(!productRepo.containsKey(product.getId())){
        
        return new ResponseEntity<>("product is null", HttpStatus.NOT_FOUND);
        }
      productRepo.remove(id); 
      product.setId(id); 
      productRepo.put(id, product); //memanggil id dan nama product yang akan di update 
      
      return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
   }
   
    //POST API
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        if(!productRepo.containsKey(product.getId())){
        
        return new ResponseEntity<>("product si already exist with ID : " +product.getId(), HttpStatus.NOT_FOUND);
        }
      productRepo.put(product.getId(), product);
      
      return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
   }
    
   //GET API
    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct(){
        if (productRepo.isEmpty()){
       return new ResponseEntity<>("product is null", HttpStatus.NOT_FOUND);
       }
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
}
