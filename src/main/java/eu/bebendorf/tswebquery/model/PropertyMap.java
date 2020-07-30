package eu.bebendorf.tswebquery.model;

import com.google.gson.JsonObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class PropertyMap extends HashMap<String,String> {
    public PropertyMap(JsonObject object){
        object.entrySet().forEach(e -> put(e.getKey(), e.getValue().getAsString()));
    }
    public PropertyMap(){}
    public String get(Property property){
        return get(property.name().toLowerCase(Locale.ROOT));
    }
    public Integer getInt(Property property){
        String s = get(property);
        return s==null?null:Integer.parseInt(s);
    }
    public Boolean getBoolean(Property property){
        String s = get(property);
        return s==null?null:s.equals('1');
    }
    public Long getLong(Property property){
        String s = get(property);
        return s==null?null:Long.parseLong(s);
    }
    public Double getDouble(Property property){
        String s = get(property);
        return s==null?null:Double.parseDouble(s);
    }
    public Date getDate(Property property){
        Long l = getLong(property);
        return l==null?null:new Date(l*1000);
    }
    public void put(Property property, String value){
        put(property.name().toLowerCase(Locale.ROOT), value);
    }
    public void put(Property property, boolean value){
        put(property, value?"1":"0");
    }
    public void put(Property property, int value){
        put(property, String.valueOf(value));
    }
    public void put(Property property, long value){
        put(property, String.valueOf(value));
    }
    public void put(Property property, double value){
        put(property, String.valueOf(value));
    }
    public void remove(Property property){
        remove(property.name().toLowerCase(Locale.ROOT));
    }
}
