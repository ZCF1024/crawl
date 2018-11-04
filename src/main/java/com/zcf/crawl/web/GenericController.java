package com.zcf.crawl.web;

import com.zcf.crawl.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenericController {

    @Autowired
    private GenericService genericService;

    @GetMapping("province")
    public String addProvinces(){
        this.genericService.addProvinces();
        return "OK";
    }

    @GetMapping("city")
    public String addCity(){
        this.genericService.addCity();
        return "OK";
    }

    @GetMapping("county")
    public String addCounty(){
        this.genericService.addCounty();
        return "Ok";
    }

    @GetMapping("town")
    public String addTown(){
        this.genericService.addTown();
        return "Ok";
    }

    @GetMapping("village")
    public String addVillage(){
        this.genericService.addVillage();
        return "Ok";
    }

}
