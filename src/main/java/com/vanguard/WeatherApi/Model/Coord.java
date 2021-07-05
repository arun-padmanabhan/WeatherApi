package com.vanguard.WeatherApi.Model;

import javax.persistence.*;

@Embeddable
@Access(AccessType.FIELD)
public class Coord {
    public double lon;
    public double lat;
}
