package com.zcf.crawl.dao.domain;

import javax.persistence.Entity;

/**
 * @Author zcf
 * @Create 2018/10/23 20:02
 * @Desc
 */
@Entity(name = "town")
public class Town extends Place{

    public Town(){

    }

    public Town(Place place){
        super(place);
    }

}