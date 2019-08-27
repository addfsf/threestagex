package com.qf.pojo;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
@Data
public class Role_Permission implements Serializable {
    private Integer roleId;
    private Integer pid;
}
