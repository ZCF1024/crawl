package com.zcf.crawl.dao.domain;

import javax.persistence.Entity;

/**
 * @Author zcf
 * @Create 2018/10/23 13:43
 * @Desc 县级
 */
@Entity(name = "county")
public class County extends Place{

    public County() {

    }

    public County(Place place) {
        super(place);
    }
}