package com.zcf.crawl;

import com.zcf.crawl.dao.domain.*;
import com.zcf.crawl.dao.repository.*;
import com.zcf.crawl.util.main.ChinaPlaceUtil;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zcf
 * @Create 2018/10/23 17:42
 * @Desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MainTest {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private VillageRepository villageRepository;

    @Autowired
    private TownRepository townRepository;

    @Test
    public void addProvinces(){
        List<Province> provinces = ChinaPlaceUtil.getProvinces("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/index.html");
        this.provinceRepository.saveAll(provinces);
    }

    @Test
    public void addCity(){
        List<String> cityUrls = provinceRepository.getCityUrls();
        for(String url : cityUrls){
            List<City> cities = ChinaPlaceUtil.getCities(url);
            this.cityRepository.saveAll(cities);
        }
    }

    @Test
    public void addCounty(){
        List<String> countyUrls = cityRepository.getCountyUrls();
        for(String url : countyUrls){
            List<County> counties = null;
            try{
                counties = ChinaPlaceUtil.getCounties(url);
            } catch (Exception e1){
                System.out.println(e1.getCause());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                counties = ChinaPlaceUtil.getCounties(url);
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            if(counties != null && counties.size() > 0){
                this.countyRepository.saveAll(counties);
                this.cityRepository.update(ChinaPlaceUtil.getCityCodeByUrl(url), 1);
            }
        }
    }

    @Test
    @Transactional
    public void addTown(){
        List<String> townUrls = countyRepository.getTownUrls();
        for(String url : townUrls){
            List<Town> towns = null;
            try{
                towns = ChinaPlaceUtil.getTowns(url);
            } catch (Exception e1){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                towns = ChinaPlaceUtil.getTowns(url);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            if(towns != null && towns.size() > 0){
                this.townRepository.saveAll(towns);
                this.countyRepository.update(ChinaPlaceUtil.getCountyCodeByUrl(url), 1);
            }
        }
    }

    @Test
    @Rollback(value = false)
    @Transactional
    public void addVillage(){
        List<String> villageUrls = townRepository.getVillageUrls();
        for(String url : villageUrls){
            List<Village> villages = null;
            try{
                villages = ChinaPlaceUtil.getVillages(url);
            } catch (Exception e1){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                villages = ChinaPlaceUtil.getVillages(url);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            if(villages != null && villages.size() > 0){
                this.villageRepository.saveAll(villages);
                this.townRepository.update(ChinaPlaceUtil.getTownCodeByUrl(url), 1);
            }
        }
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void update(){
       this.townRepository.update(110101005000L, 0);
    }

}