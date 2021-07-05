package com.vanguard.WeatherApi.Model;

import javax.persistence.*;

@Embeddable
public class Weather {
    public int id;
    public String main;
    public String description;
    public String icon;
}
