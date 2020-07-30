package eu.bebendorf.tswebquery.model;

import com.sun.org.apache.xerces.internal.util.Status;

public enum ServerStatus {
    ONLINE,
    OFFLINE,
    UNKNOWN;
    public static ServerStatus fromString(String status){
        for(ServerStatus s : values()){
            if(s.name().equalsIgnoreCase(status)){
                return s;
            }
        }
        return ServerStatus.UNKNOWN;
    }
}
