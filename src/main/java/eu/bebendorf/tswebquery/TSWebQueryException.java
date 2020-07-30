package eu.bebendorf.tswebquery;

public class TSWebQueryException extends Exception {
    private TSWebQueryResponse response;
    public TSWebQueryException(TSWebQueryResponse response){
        super(response.status.message);
        this.response = response;
    }
    public TSWebQueryResponse getResponse(){
        return response;
    }
    public int getCode(){
        return response.status.code;
    }
}
