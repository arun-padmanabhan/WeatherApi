package com.vanguard.WeatherApi.Model;

import javax.persistence.*;

@Embeddable
@Access(AccessType.FIELD)
public class Sys {
    public int type;
    public int id;
    public double message;
    public String country;
    public int sunrise;
    public int sunset;
}
