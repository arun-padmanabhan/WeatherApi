package com.vanguard.WeatherApi.Model;

import javax.persistence.*;

@Embeddable @Access(AccessType.FIELD)
public class Wind {
    public double speed;
    public int deg;
}
