package com.kanghe.payment.platform.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageParam {

    private String orderByClause;

    private boolean distinct;

    private Integer limit;

    private Integer offset;

}
