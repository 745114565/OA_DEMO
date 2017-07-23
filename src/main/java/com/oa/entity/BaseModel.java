package com.oa.entity;

import com.oa.excel.ExcelResources;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by HOZANDUNG on 17/5/24.
 */
@MappedSuperclass
public class BaseModel implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
