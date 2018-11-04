package com.zcf.crawl.dao.repository;

import com.zcf.crawl.dao.domain.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/10/23 20:03
 * @Desc
 */
@Transactional
public interface TownRepository extends JpaRepository<Town, Long> {

    @Query(value = "SELECT url FROM town  WHERE isupdated=0", nativeQuery = true)
    List<String> getVillageUrls();

    @Modifying
    @Query(value = "UPDATE town SET isupdated=?2 WHERE code=?1", nativeQuery = true)
    Integer update(Long code, Integer isupdate);

}