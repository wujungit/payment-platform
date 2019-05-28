package com.kanghe.payment.platform.base;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: W_jun1
 * @Date: 2019/5/27 15:16
 * @Description: PageModel
 */
@Getter
@Setter
public class PageModel<T> {

    public List<T> list;
    public Integer count = 0;
    public String msg;
    public Boolean rel;

}
