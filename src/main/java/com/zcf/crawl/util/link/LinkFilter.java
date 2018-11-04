package com.zcf.crawl.util.link;

/**
 * @Author zcf
 * @Create 2018/10/22 14:09
 * @Desc
 */
public interface LinkFilter {
    public boolean accept(String url);
}