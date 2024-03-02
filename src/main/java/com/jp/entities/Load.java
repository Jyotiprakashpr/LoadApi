package com.jp.entities;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@Table(name = "Load")
public class Load {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "loading_point")
    private String loadingPoint;

    @Column(name = "unloading_point")
    private String unloadingPoint;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "truck_type")
    private String truckType;

    @Column(name = "no_of_trucks")
    private int noOfTrucks;
     
    @Column(name = "weight")
    private int weight;
    
    @Column(name = "comment")
    private String comment;

    @Column(name = "shipper_id")
    private String shipperId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "load_date")
    private Date date;

    public Load() {
        this.shipperId = "shipper:" + UUID.randomUUID().toString();
    }
}
