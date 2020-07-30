package eu.bebendorf.tswebquery.model;

import com.google.gson.JsonObject;

public class ClientInfo extends PropertyMap {
    public ClientInfo(){}
    public ClientInfo(JsonObject object){super(object);}
    public String getNickName(){
        return get(Property.CLIENT_NICKNAME);
    }
    public int getDatabaseId(){
        return getInt(Property.CLIENT_DATABASE_ID);
    }
    public int getChannelId(){
        return getInt(Property.CID);
    }
    public String getUniqueId(){
        return get(Property.CLIENT_UNIQUE_IDENTIFIER);
    }
    public ClientType getType(){
        return ClientType.fromType(getInt(Property.CLIENT_TYPE));
    }
}
