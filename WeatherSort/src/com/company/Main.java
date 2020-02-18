package com.company;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

/*
1) Собрать номера городов (не менее 10) +
2) Зарегистрироваться на сайте и получить APPID +
3) Используя заготовку написать программу, собирающую
сведения о температуре в перечисленных городах
4) Вывести названия городов и температуры в них
от более тёплых более холодным
5) Получить названия городов можно из JSON-данных,
для этого придётся дополнить класс Weather
 */
class M {
    int humidity, pressure;
    double temp_max, temp_min, temp;

    @Override
    public String toString() {
        return "M{" +
                "humidity=" + humidity +
                ", pressure=" + pressure +
                ", temp_max=" + temp_max +
                ", temp_min=" + temp_min +
                ", temp=" + temp +
                '}';
    }
}
class Weather {
    M main;
    String name;

    @Override
    public String toString() {
        return "Weather{" +
                "main=" + main.toString() +
                '}';
    }
}

//Astana - 1526273, Almaty - 1526384, Khankh - 2028534, Dublin - 2964574, Wellington - 2179537, Kathmandu - 1283240, Osaka - 1853908, Kyoto - 1857910, Rome - 3169070, Verona - 3164527

public class Main {
    final static String API_URL = "https://api.openweathermap.org/data/2.5/weather?id=%d&appid=221d52e7870fac7aa605548ee394750d";
    public static Map<String, Double> map = new HashMap<>();

    public static void main(String[] args) throws IOException, InterruptedException {



        class ValueComparator implements Comparator {

            Map base;
            public ValueComparator(Map base) {
                this.base = base;
            }

            public int compare(Object a, Object b) {

                if((Double)base.get(a) < (Double)base.get(b)) {
                    return 1;
                } else if((Double)base.get(a) == (Double)base.get(b)) {
                    return 0;
                } else {
                    return -1;
                }
            }
        }


        ValueComparator bvc =  new ValueComparator(map);
        TreeMap<String,Double> sorted_map = new TreeMap(bvc);



        class MyThread extends Thread {

            public MyThread(String url) {
                this.url = url;
            }

            String url; Weather weather;

            @Override
            public void run() {
                try {
                    URL weather_url = new URL(url);
                    InputStream stream = (InputStream) weather_url.getContent();
                    Gson gson = new Gson();
                    weather = gson.fromJson(new InputStreamReader(stream), Weather.class);
                    map.put(weather.name, weather.main.temp_max);
                    //System.out.println(map);
                } catch (IOException e) {}
            }
        }

        int[] cities = {1526273, 1526384, 2028534, 2964574, 2179537, 1283240, 1853908, 1857910, 3169070, 3164527};

        MyThread [] weather_thread = new MyThread[cities.length];
        for (int i = 0; i < cities.length; i++) {
            weather_thread[i] = new MyThread(String.format(API_URL, cities[i]));
            weather_thread[i].start();
            weather_thread[i].join();
        }

        sorted_map.putAll(map);
        for (String key : sorted_map.keySet()) {
            System.out.println(key + "/"+sorted_map.get(key));
        }
    }
}

