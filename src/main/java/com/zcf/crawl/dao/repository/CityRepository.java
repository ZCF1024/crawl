package com.zcf.crawl.dao.repository;

import com.zcf.crawl.dao.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/10/23 17:14
 * @Desc
 */
@Transactional
public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "SELECT url FROM city  WHERE isupdated=0", nativeQuery = true)
    List<String> getCountyUrls();

    @Modifying
    @Query(value = "UPDATE city SET isupdated=?2 WHERE code=?1", nativeQuery = true)
    void update(Long code, Integer isupdate);
}