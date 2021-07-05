package com.vanguard.WeatherApi.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class WeatherResponse {
    @Id
    public int id;
    @Embedded
    public Coord coord;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "weather", joinColumns = @JoinColumn(name = "weatherResponse_id"))
    public List<Weather> weather;
    public String base;
    @Embedded
    public Main main;
    public int visibility;
    @Embedded
    public Wind wind;
    public int dt;
    public int timeZone;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "id", column = @Column(name = "sys_id"))
    })
    public Sys sys;
    public String name;
    public int cod;
}
