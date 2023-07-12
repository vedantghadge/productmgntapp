package com.csi.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private int productId;

    @Size(min = 2, message = "product name should be at least 2 character")
    private String productName;

    private String productCode;

    private double productPrice;


    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date productLaunchDate;


}
