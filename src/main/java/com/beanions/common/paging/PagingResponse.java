package com.beanions.common.paging;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PagingResponse<T> {

    public List<T> list = new ArrayList<>();
    public Pagination pagination;

    public PagingResponse(List<T> list, Pagination pagination){
        this.list.addAll(list);
        this.pagination = pagination;
    }
}
