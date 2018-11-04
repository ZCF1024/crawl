package com.zcf.crawl.dao.repository;

import com.zcf.crawl.dao.domain.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/10/23 17:17
 * @Desc
 */
@Transactional
public interface CountyRepository extends JpaRepository<County, Long> {

    @Query(value = "SELECT url FROM county WHERE isupdated=0", nativeQuery = true)
    List<String> getTownUrls();

    @Modifying
    @Query(value = "UPDATE county SET isupdated=?2 WHERE code=?1", nativeQuery = true)
    void update(Long code, Integer isupdate);

}