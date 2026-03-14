package com.nih.incomapi.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PagedResponse<T> {
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

    public static <T> PagedResponse<T> of(Page<T> p) {
        PagedResponse<T> r = new PagedResponse<>();
        r.content = p.getContent();
        r.page = p.getNumber();
        r.size = p.getSize();
        r.totalElements = p.getTotalElements();
        r.totalPages = p.getTotalPages();
        return r;
    }
}
