package com.oa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by HOZANDUNG on 17/5/24.
 */
@MappedSuperclass
public class BaseModel implements Serializable {

    private static final long serialVersionUID = -5504883436669440135L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date update_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

}
