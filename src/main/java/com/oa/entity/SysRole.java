package com.oa.entity;

import javax.persistence.Entity;

/**
 * Created by HOZANDUNG on 17/5/10.
 */
@Entity
public class SysRole extends BaseModel {


    private String name;

    private String description;

    public SysRole() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
