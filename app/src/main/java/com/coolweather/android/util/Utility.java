package com.coolweather.android.util;

import android.text.TextUtils;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xmz on 2017/12/15.
 */

public class Utility {
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObjects = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceCode(provinceObjects.getInt("id"));
                    province.setProvinceName(provinceObjects.getString("name"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCity = new JSONArray(response);
                for (int i = 0; i < allCity.length(); i++) {
                    JSONObject cityObjects = allCity.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObjects.getString("name"));
                    city.setCityCode(cityObjects.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allcounty = new JSONArray(response);
                for (int i = 0; i < allcounty.length(); i++) {
                    JSONObject countyObjects = allcounty.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObjects.getString("name"));
                    county.setCityId(cityId);
                    county.setWeatherId(countyObjects.getString("weather_id"));
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
