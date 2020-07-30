package eu.bebendorf.tswebquery.model;

import com.google.gson.JsonObject;

public class Channel extends PropertyMap {
    public Channel(){}
    public Channel(JsonObject object){super(object);}
    public String getName(){
        return get(Property.CHANNEL_NAME);
    }
    public int getId(){
        return getInt(Property.CID);
    }
    public int getParentId(){
        return getInt(Property.PID);
    }
    public int getTotalClients(){
        return getInt(Property.TOTAL_CLIENTS);
    }
    public int getOrder(){
        return getInt(Property.CHANNEL_ORDER);
    }
}
