package eu.bebendorf.tswebquery;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class TSWebQueryResponse {
    private static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    public Status status;
    JsonArray body = new JsonArray();
    public <T> List<T> get(Class<T> clazz){
        if(clazz == null)
            return null;
        List<T> list = new ArrayList<>();
        for(JsonElement e : body){
            list.add(GSON.fromJson(e, clazz));
        }
        return list;
    }
    public <T> List<T> getOrError(Class<T> clazz) throws TSWebQueryException {
        orError();
        return get(clazz);
    }
    public boolean isSuccess(){
        return status.code == 0 || status.code == 1281;
    }
    public void orError() throws TSWebQueryException {
        if(!isSuccess())
            throw new TSWebQueryException(this);
    }
    public static class Status {
        public int code;
        public String message;
        public String extraMessage;
    }
    public static TSWebQueryResponse from(String json){
        return GSON.fromJson(json, TSWebQueryResponse.class);
    }
}
