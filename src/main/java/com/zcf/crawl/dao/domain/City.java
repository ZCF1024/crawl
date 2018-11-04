package com.zcf.crawl.dao.domain;

import javax.persistence.Entity;

/**
 * @Author zcf
 * @Create 2018/10/22 17:45
 * @Desc 市级
 */
@Entity(name = "city")
public class City extends Place {

    public City() {
    }

    public City(Place place) {
        super(place);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}