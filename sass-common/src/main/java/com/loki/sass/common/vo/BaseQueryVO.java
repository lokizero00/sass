package com.loki.sass.common.vo;

import java.io.Serializable;

/**
 * created by lokizero00 on 2019-03-04
 */
public class BaseQueryVO implements Serializable {
    private Integer page = 1;

    private Integer rows = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
