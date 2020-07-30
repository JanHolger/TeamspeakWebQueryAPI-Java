package eu.bebendorf.tswebquery.model;

import com.google.gson.JsonObject;

public class ServerInfo extends PropertyMap {
    public ServerInfo(){}
    public ServerInfo(JsonObject object){super(object);}
    public String getName(){
        return get(Property.VIRTUALSERVER_NAME);
    }
    public int getMaxClients(){
        return getInt(Property.VIRTUALSERVER_MAXCLIENTS);
    }
    public int getNeededIdentitySecurityLevel(){
        return getInt(Property.VIRTUALSERVER_NEEDED_IDENTITY_SECURITY_LEVEL);
    }
    public String getWelcomeMessage(){
        return get(Property.VIRTUALSERVER_WELCOMEMESSAGE);
    }
    public int getPort(){
        return getInt(Property.VIRTUALSERVER_PORT);
    }
    public ServerStatus getStatus(){
        return ServerStatus.fromString(get(Property.VIRTUALSERVER_STATUS));
    }
    public void setWelcomeMessage(String welcomeMessage){
        put(Property.VIRTUALSERVER_WELCOMEMESSAGE, welcomeMessage);
    }
    public void setNeededIdentitySecurityLevel(int neededIdentitySecurityLevel){
        put(Property.VIRTUALSERVER_NEEDED_IDENTITY_SECURITY_LEVEL, neededIdentitySecurityLevel);
    }
    public void setMaxClients(int maxClients){
        put(Property.VIRTUALSERVER_MAXCLIENTS, maxClients);
    }
    public void setName(String name){
        put(Property.VIRTUALSERVER_NAME, name);
    }
}
