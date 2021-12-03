package com.edn.base.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductFilterDTO {

    private int page;
    private int pageSize;
    private String sku;
    private String name;
    private String category;
    private String description;
    private String initialDate;
    private String finalDate;

}
