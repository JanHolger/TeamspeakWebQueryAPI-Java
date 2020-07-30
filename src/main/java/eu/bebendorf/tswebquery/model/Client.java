package eu.bebendorf.tswebquery.model;

import com.google.gson.JsonObject;

public class Client extends PropertyMap {
    public Client(){}
    public Client(JsonObject object){super(object);}
    public String getNickName(){
        return get(Property.CLIENT_NICKNAME);
    }
    public int getId(){
        return getInt(Property.CLID);
    }
    public int getDatabaseId(){
        return getInt(Property.CLIENT_DATABASE_ID);
    }
    public int getChannelId(){
        return getInt(Property.CID);
    }
    public ClientType getType(){
        return ClientType.fromType(getInt(Property.CLIENT_TYPE));
    }
}
