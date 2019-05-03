package com.hilkr.dal.model;

import lombok.Data;

@Data
public class Address {
    private Long id;

    private Long userId;

    private String name;

    private String phone;

    private String zipCode;

    private String state;

    private String city;

    private String district;

    private String address;

    private Boolean defaultAddress;

    private String label;
}