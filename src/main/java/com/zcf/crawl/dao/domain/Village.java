package com.zcf.crawl.dao.domain;

import javax.persistence.Entity;

/**
 * @Author zcf
 * @Create 2018/10/23 14:29
 * @Desc 村级
 */
@Entity(name = "village")
public class Village extends Place {

    private Integer  classCode;

    public Integer getClassCode() {
        return classCode;
    }

    public void setClassCode(Integer classCode) {
        this.classCode = classCode;
    }

}