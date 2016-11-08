package com.ldrong.citydemo.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2016/10/31.
 */
@Entity
public class City {

    private String COUNTY_CODE;
    private String CITY_CODE;
    private String COUNTY_NAME;
    private String CITY_NAME;
    private String PROVINCE_NAME;
    private String PROVINCE_CODE;
    @Generated(hash = 1546990309)
    public City(String COUNTY_CODE, String CITY_CODE, String COUNTY_NAME,
            String CITY_NAME, String PROVINCE_NAME, String PROVINCE_CODE) {
        this.COUNTY_CODE = COUNTY_CODE;
        this.CITY_CODE = CITY_CODE;
        this.COUNTY_NAME = COUNTY_NAME;
        this.CITY_NAME = CITY_NAME;
        this.PROVINCE_NAME = PROVINCE_NAME;
        this.PROVINCE_CODE = PROVINCE_CODE;
    }
    @Generated(hash = 750791287)
    public City() {
    }
    public String getCOUNTY_CODE() {
        return this.COUNTY_CODE;
    }
    public void setCOUNTY_CODE(String COUNTY_CODE) {
        this.COUNTY_CODE = COUNTY_CODE;
    }
    public String getCITY_CODE() {
        return this.CITY_CODE;
    }
    public void setCITY_CODE(String CITY_CODE) {
        this.CITY_CODE = CITY_CODE;
    }
    public String getCOUNTY_NAME() {
        return this.COUNTY_NAME;
    }
    public void setCOUNTY_NAME(String COUNTY_NAME) {
        this.COUNTY_NAME = COUNTY_NAME;
    }
    public String getCITY_NAME() {
        return this.CITY_NAME;
    }
    public void setCITY_NAME(String CITY_NAME) {
        this.CITY_NAME = CITY_NAME;
    }
    public String getPROVINCE_NAME() {
        return this.PROVINCE_NAME;
    }
    public void setPROVINCE_NAME(String PROVINCE_NAME) {
        this.PROVINCE_NAME = PROVINCE_NAME;
    }
    public String getPROVINCE_CODE() {
        return this.PROVINCE_CODE;
    }
    public void setPROVINCE_CODE(String PROVINCE_CODE) {
        this.PROVINCE_CODE = PROVINCE_CODE;
    }


}
