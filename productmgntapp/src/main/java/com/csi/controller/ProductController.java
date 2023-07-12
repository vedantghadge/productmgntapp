package com.csi.controller;


import com.csi.exception.RecordNotFoundException;
import com.csi.model.Product;
import com.csi.service.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProductController {

    @Autowired
    ProductServiceImpl productServiceImpl;

    @PostMapping("/savedata")
    public ResponseEntity<Product> saveData(@Valid @RequestBody Product product) {
        log.info("------Trying to save data for Product " + product.getProductName());

        return ResponseEntity.ok(productServiceImpl.saveData(product));
    }

    @GetMapping("/getdatabyid/{productId}")
    public ResponseEntity<Optional<Product>> getAllDataById(@PathVariable int productId) {

        return ResponseEntity.ok(productServiceImpl.getDataById(productId));
    }

    @GetMapping("/getdatabyname/{productName}")
    public ResponseEntity<List<Product>> getDataByName(@PathVariable String productName) {

        return ResponseEntity.ok(productServiceImpl.getDataByName(productName));
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<Product>> getAllData() {

        return ResponseEntity.ok(productServiceImpl.getAllData());
    }

    @PutMapping("/updatedata/{productId}")
    public ResponseEntity<Product> updataData(@PathVariable int productId, @Valid @RequestBody Product product) {

        Product product1 = productServiceImpl.getDataById(productId).orElseThrow(() -> new RecordNotFoundException("Product ID Does Not Exist"));

        product1.setProductCode(product.getProductCode());
        product1.setProductName(product.getProductName());
        product1.setProductPrice(product.getProductPrice());
        product1.setProductLaunchDate(product.getProductLaunchDate());


        return new ResponseEntity<>(productServiceImpl.updateData(product1), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletedatabyid/{productId}")
    public ResponseEntity<String> deletDataById(@PathVariable int productId) {

        productServiceImpl.deleteDataById(productId);

        return ResponseEntity.ok("Data Deleteted Succesfully ");
    }


    @DeleteMapping("/deletealldata")
    public ResponseEntity<String> deleteAllData() {

        productServiceImpl.deleteAllData();

        return ResponseEntity.ok("All Data Deleted Successfully");
    }
    @GetMapping("/sayhello")
    public ResponseEntity<String> sayHello (){
        return ResponseEntity.ok("welcome");
    }


}
