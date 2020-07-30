package eu.bebendorf.tswebquery.model;

public enum  ServerGroupType {
    TEMPLATE(0),
    REGULAR(1),
    QUERY(2);
    int type;
    ServerGroupType(int type){
        this.type = type;
    }
    public static ServerGroupType fromType(int type){
        for(ServerGroupType t : values()){
            if(t.type == type){
                return t;
            }
        }
        return null;
    }
}
