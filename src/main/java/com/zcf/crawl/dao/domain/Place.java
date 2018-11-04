package com.zcf.crawl.dao.domain;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

/**
 * @Author zcf
 * @Create 2018/10/22 16:52
 * @Desc
 */
@MappedSuperclass
public class Place {

    @Id
    protected Long code;

    protected String name;

    protected String url;

    private int isupdated;

    public Place() {

    }

    public Place(Place place) {
        code = place.code;
        name = place.name;
        url = place.url;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(code, place.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    public int getIsupdated() {
        return isupdated;
    }

    public void setIsupdated(int isupdated) {
        this.isupdated = isupdated;
    }
}