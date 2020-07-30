package eu.bebendorf.tswebquery.model;

import com.google.gson.JsonObject;

public class ServerGroup extends PropertyMap {
    public ServerGroup(){}
    public ServerGroup(JsonObject object){super(object);}
    public int getId(){
        return getInt(Property.SGID);
    }
    public String getName(){
        return get(Property.NAME);
    }
    public ServerGroupType getType(){
        return ServerGroupType.fromType(getInt(Property.TYPE));
    }
}
