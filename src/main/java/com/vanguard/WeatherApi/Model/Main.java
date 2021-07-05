package com.vanguard.WeatherApi.Model;

import javax.persistence.*;

@Embeddable
@Access(AccessType.FIELD)
public class Main {
    public double temp;
    public int pressure;
    public int humidity;
    public double temp_min;
    public double temp_max;
}
