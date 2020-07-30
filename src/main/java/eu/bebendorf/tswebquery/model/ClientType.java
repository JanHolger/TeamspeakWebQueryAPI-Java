package eu.bebendorf.tswebquery.model;

public enum ClientType {
    REGULAR(0),
    QUERY(1);
    int type;
    ClientType(int type){
        this.type = type;
    }
    public static ClientType fromType(int type){
        for(ClientType t : values()){
            if(t.type == type){
                return t;
            }
        }
        return null;
    }
}
