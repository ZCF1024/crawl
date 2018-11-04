package com.zcf.crawl.util.main;

import com.zcf.crawl.dao.domain.*;
import com.zcf.crawl.util.page.RequestAndResponseTool;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author zcf
 * @Create 2018/10/22 16:25
 * @Desc
 */
public class ChinaPlaceUtil {

    public static void main(String[] args) {

        System.out.println(getTownCodeByUrl("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/36/07/03/360703100.html"));

//        List<County> counties = getCounties("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/44/4419.html");
//        for(County county : counties){
//            System.out.println(county.getUrl());
//        }
    }

    public static List<Province> getProvinces(String url){
        List<Province> provinces = new ArrayList<>();
        Elements trs = getHtmlTrByClassName(url, "provincetr");
        for(Element tr : trs){
            Elements as = tr.getElementsByTag("a");
            Province province = null;
            for(Element a : as){
                province = new Province();
                String href = a.attr("href");
                province.setUrl(getNextUrl(url, href));
                String codeStr = href.substring(0, href.indexOf(".html"));
                province.setCode(Long.parseLong(codeStr));
                province.setName(a.text());
                provinces.add(province);
            }
        }
        return provinces;
    }

    public static List<City> getCities(String url){
        List<City> cities = new ArrayList<>();
        List<Place> places = getPlaceInfos(url, "citytr");
        for(Place place : places){
            cities.add(new City(place));
        }
        return cities;
    }

    public static List<County> getCounties(String url){
        List<County> counties = new ArrayList<>();
        List<Place> places = getPlaceInfos(url, "countytr");
        for(Place place : places){
            counties.add(new County(place));
        }
        return counties;
    }

    public static List<Town> getTowns(String url){
        List<Town> towns = new ArrayList<>();
        List<Place> places = getPlaceInfos(url, "towntr");
        for(Place place : places){
            towns.add(new Town(place));
        }
        return towns;
    }

    private static List<Place> getPlaceInfos(String url, String className){
        List<Place> places = new ArrayList<>();
        Elements trs = getHtmlTrByClassName(url, className);
        Iterator ite = trs.iterator();
        Place place = null;
        while(ite.hasNext()){
            Element tr = (Element) ite.next();
            Elements tds = tr.getElementsByTag("td");
            Elements as = tds.get(0).getElementsByTag("a");
            if(as == null || as.size() == 0){
                continue;
            }
            String href = as.get(0).attr("href");
            place= new Place();
            place.setUrl(getNextUrl(url, href));
            place.setCode(Long.parseLong(as.text()));
            place.setName(tds.get(1).text());
            places.add(place);
        }
        return places;
    }

    private static Elements getHtmlTrByClassName(String url, String className){
        Document doc = RequestAndResponseTool.sendRequstAndGetResponse(url).getDoc();
        return doc.getElementsByClass(className);
    }

    private static String getNextUrl(String curUrl, String relativeUrl){
        return curUrl.substring(0,curUrl.lastIndexOf('/') + 1) + relativeUrl;
    }

    public static List<Village> getVillages(String url){
        List<Village> villages = new ArrayList<>();
        Elements trs = getHtmlTrByClassName(url, "villagetr");
        Village village = null;
        for (Element tr : trs){
            Elements tds = tr.getElementsByTag("td");
            village = new Village();
            village.setCode(Long.parseLong(tds.get(0).text()));
            village.setClassCode(Integer.parseInt(tds.get(1).text()));
            village.setName(tds.get(2).text());
            villages.add(village);
        }
        return villages;
    }

    public static Long getProvinceCodeByUrl(String url){
        return getCode(url);
    }

    public static Long getTownCodeByUrl(String url){
        return getCode(url) * 1000;
    }

    public static Long getCountyCodeByUrl(String url){
        return getCode(url) * 1000000;
    }

    public static Long getCityCodeByUrl(String url){
        return getCode(url) * 100000000;
    }

    private static Long getCode(String url){
        String codeStr = url.substring(url.lastIndexOf('/') + 1,url.lastIndexOf('.'));
        return Long.parseLong(codeStr);
    }

}