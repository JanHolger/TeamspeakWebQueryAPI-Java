package eu.bebendorf.tswebquery.model;

public enum ServerStatus {
    ONLINE,
    ONLINE_VIRTUAL,
    OFFLINE,
    UNKNOWN;
    public static ServerStatus fromString(String status){
        for(ServerStatus s : values()){
            if(s.name().replace('_', ' ').equalsIgnoreCase(status)){
                return s;
            }
        }
        return ServerStatus.UNKNOWN;
    }
}
