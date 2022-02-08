package com.ranauro.gln.entity;

/**
 * @Project Lezione38.2
 * @AUTHOR giulianoranauro on 08/02/22
 */
public class Sample {
    public static int ids = 0;
    private int id;
    private String measureUnit;
    private String simpleValue;
    private long timestamp;
    private String attribute;

    public Sample(String sv, long ts, String mu, String at){
        id = ++ids;
        simpleValue = sv;
        timestamp = ts;
        measureUnit = mu;
        attribute = at;
    }

    public Sample(){}

    public static int getIds() {
        return ids;
    }

    public static void setIds(int ids) {
        Sample.ids = ids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public String getSimpleValue() {
        return simpleValue;
    }

    public void setSimpleValue(String simpleValue) {
        this.simpleValue = simpleValue;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
