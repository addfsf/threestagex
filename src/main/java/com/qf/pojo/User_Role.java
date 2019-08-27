package com.qf.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class User_Role implements Serializable {
    private int userId;
    private int roleId;
}
