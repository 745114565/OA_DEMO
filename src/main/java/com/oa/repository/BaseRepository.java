package com.oa.repository;

import com.oa.entity.BaseModel;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by HOZANDUNG on 17/5/25.
 */
public interface BaseRepository<M extends BaseModel> extends PagingAndSortingRepository<M,Long> {
}
