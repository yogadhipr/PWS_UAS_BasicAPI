/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PWS.Project2.PWS;

/**
 *
 * @author TUF GAMING
 */
class Product {
   private String id;
   private String name;
   private String harga;
   private String stock;

   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getHarga() {
      return harga;
   }
   public void setHarga(String harga) {
      this.harga = harga;
   }
   public String getStock() {
      return stock;
   }
   public void setStock(String stock) {
      this.stock = stock;
   }
   
   
   /*public String getHarga(){
       return harga;
   }
   public void setHarga(String harga){
       this.harga = harga;
   }
   public String getStock(){
       return stock;
   }
   public void setStock(String stock){
       this.stock = stock;
   }*/
}
