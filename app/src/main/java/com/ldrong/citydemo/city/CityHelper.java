package com.ldrong.citydemo.city;

import com.ldrong.citydemo.conn.AppContext;
import com.ldrong.citydemo.db.City;
import com.ldrong.citydemo.db.CityDao;
import com.ldrong.citydemo.db.DaoSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldr on 2016/8/26.
 */
public class CityHelper {
    private static final String TAG = "CityHelper";
    public static DaoSession session = AppContext.getGreenDaoSessino();


    /**
     * 从省份id获得省名
     */
    public static String getProvinceFromKey(String provinceID) {

        List<City> provinceList = session.getCityDao().queryBuilder().where(CityDao.Properties.PROVINCE_CODE.eq(provinceID)).list();

        if (provinceList != null && provinceList.size() != 0) {
            //Log.e(TAG, "" + provinceList.get(0).getPROVINCE_NAME());
            return provinceList.get(0).getPROVINCE_NAME();

        }
        return "";
    }

    /**
     * 从城市id获得城市名
     */
    public static String getCityFromKey(String cityID) {

        List<City> cityList = session.getCityDao().queryBuilder().where(CityDao.Properties.CITY_CODE.eq(cityID)).list();

        if (cityList != null && cityList.size() != 0) {
            return cityList.get(0).getCITY_NAME();
        }

        return "";
    }


    /**
     * 从区id获得区名
     */
    public static String getDistrictFromKey(String districtID) {
        List<City> districtList = session.getCityDao().queryBuilder().where(CityDao.Properties.COUNTY_CODE.eq(districtID)).list();

        if (districtList != null && districtList.size() != 0) {
            return districtList.get(0).getCOUNTY_NAME();
        }
        return "";
    }

    /**
     * 获得省份列表
     */
    public static List<String> getAllProvince() throws Exception {
        List<String> provinceList = new ArrayList<String>();

        List<City> dbPro = session.getCityDao().queryBuilder().list();

        if (dbPro != null) {
            for (City t : dbPro) {
                if (!provinceList.contains(t.getPROVINCE_NAME())) {
                    provinceList.add(t.getPROVINCE_NAME());
                }
            }
        }
        return provinceList;

    }

    /**
     * 根据省份名获得城市列表
     */
    public static List<String> getCitysByProvince(String provincename) throws Exception {
        List<String> cityList = new ArrayList<String>();

        List<City> proList = session.getCityDao().queryBuilder().where(CityDao.Properties.PROVINCE_NAME.eq(provincename)).list();
        if (proList != null && proList.size() != 0) {
            for (City td : proList) {
                if (!cityList.contains(td.getCITY_NAME())) {
                    cityList.add(td.getCITY_NAME());
                }
            }
        }

        return cityList;

    }

    /**
     * 根据城市名获得区列表
     */
    public static List<String> getDistrictsByProvince(String cityName) throws Exception {
        List<String> districtList = new ArrayList<String>();

        List<City> cityList = session.getCityDao().queryBuilder().where(CityDao.Properties.CITY_NAME.eq(cityName)).list();
        for (City td :
                cityList) {
            districtList.add(td.getCOUNTY_NAME());
        }


        return districtList;
    }

    /**
     * 根据省名获得id
     */
    public static String getProvinceKeyFromName(String province_name) throws Exception {

        List<City> proList = session.getCityDao().queryBuilder().where(CityDao.Properties.PROVINCE_NAME.eq(province_name)).list();

        if (proList != null && proList.size() != 0) {
            return proList.get(0).getPROVINCE_CODE();
        }
        return "";
    }


    /**
     * 根据城市获得id
     */
    public static String getCityKeyFromName(String city_name) throws Exception {

        List<City> cityList = session.getCityDao().queryBuilder().where(CityDao.Properties.CITY_NAME.eq(city_name)).list();

        if (cityList != null && cityList.size() != 0) {
            return cityList.get(0).getCITY_CODE();
        }

        return "";
    }

    /**
     * 根据区名+市名+省名获得信息
     */
    public static City getDistrictKeyFromName(String county_name, String city_name, String province_name) {

        List<City> targetList = session.getCityDao().queryBuilder().where(
                CityDao.Properties.COUNTY_NAME.eq(county_name),
                CityDao.Properties.CITY_NAME.eq(city_name),
                CityDao.Properties.PROVINCE_NAME.eq(province_name)).list();

        if (targetList != null && targetList.size() != 0) {
            return targetList.get(0);
        }
        return null;
    }

    /**
     * 得省的 集合
     *
     * @return
     * @throws Exception
     */
    public static List<String> fetchAllProvinceNames() throws Exception {

        List<City> targetList = session.getCityDao().queryBuilder().list();
        List<String> tempList = new ArrayList<>();
        if (targetList != null && targetList.size() != 0) {
            for (int i = 0; i < targetList.size(); i++) {
                if (!tempList.contains(targetList.get(i).getPROVINCE_NAME())) {
                    tempList.add(targetList.get(i).getPROVINCE_NAME());
                }
            }
        }
        return tempList;
    }


    /**
     * 某个省下 所有城市name
     *
     * @return
     * @throws Exception
     */
    public static List<String> fetchAllCityNames(String province_code) throws Exception {

        List<City> cityList = session.getCityDao().queryBuilder().where(CityDao.Properties.PROVINCE_CODE.eq(province_code)).list();
        List<String> cityName = new ArrayList<>();

        for (City td :
                cityList) {
            if (!cityName.contains(td.getCITY_NAME())) {
                cityName.add(td.getCITY_NAME());
            }
        }

        return cityName;
    }

    /**
     * 根据某个省，获取省下的所有城市信息
     *
     * @param province_code
     * @return
     */
    public static List<City> getAllCityEntityFromProvinceID(String province_code) {
        List<City> cityList = session.getCityDao().queryBuilder().where(CityDao.Properties.PROVINCE_CODE.eq(province_code)).list();

        return cityList;
    }

    /**
     * 模糊搜索城市
     *
     * @param key
     * @return
     */
    public static List<String> searchCity(String key) {
        List<City> temp = session.getCityDao().queryBuilder().where(CityDao.Properties.CITY_NAME.like("%" + key + "%")

        ).list();

        List<String> cityName = new ArrayList<>();
        for (City td :
                temp) {
            if (!cityName.contains(td.getCITY_NAME())) {
                cityName.add(td.getCITY_NAME());
            }
        }

        return cityName;
    }

    public static List<City> getAllCityInfo() {
        List<City> list = session.getCityDao().queryBuilder().list();
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).getCITY_NAME().equals(
                        list.get(i).getCITY_NAME())) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    public static List<City> getAllProvinceInfo() {
        List<City> list = session.getCityDao().queryBuilder().list();
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).getPROVINCE_NAME().equals(
                        list.get(i).getPROVINCE_NAME())) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

}