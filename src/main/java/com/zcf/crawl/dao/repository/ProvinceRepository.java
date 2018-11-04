package com.zcf.crawl.dao.repository;

import com.zcf.crawl.dao.domain.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/10/23 17:21
 * @Desc
 */
@Transactional
public interface ProvinceRepository extends JpaRepository<Province, Long> {

    @Query(value = "SELECT url FROM province WHERE isupdated=0", nativeQuery = true)
    List<String> getCityUrls();

    @Modifying
    @Query(value = "UPDATE province SET isupdated=?2 WHERE code=?1", nativeQuery = true)
    void update(Long code, Integer isupdate);
}