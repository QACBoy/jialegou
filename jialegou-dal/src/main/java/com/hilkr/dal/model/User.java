package com.hilkr.dal.model;

import com.hilkr.dal.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class User extends BaseModel {

    private Long id;

    private String username;

    private String password;

    private String phone;

    private Date created;

    private String salt;

}