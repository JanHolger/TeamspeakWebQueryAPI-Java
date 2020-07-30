package eu.bebendorf.tswebquery.model;

import com.google.gson.JsonObject;

import java.util.Date;

public class Token extends PropertyMap {
    public Token(){}
    public Token(JsonObject object){super(object);}
    public String getToken(){
        return get(Property.TOKEN);
    }
    public Date getCreated(){
        return getDate(Property.TOKEN_CREATED);
    }
    public String getDescription(){
        return get(Property.TOKEN_DESCRIPTION);
    }
}
