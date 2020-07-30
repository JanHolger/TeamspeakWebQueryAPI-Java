package eu.bebendorf.tswebquery;

import com.google.gson.JsonObject;
import eu.bebendorf.tswebquery.model.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TSWebQuery {

    private String url;
    private String apiKey;

    public TSWebQuery(String url, String apiKey){
        this.url = url;
        this.apiKey = apiKey;
    }

    public ServerInfo getServerInfo(int id) throws TSWebQueryException {
        return new ServerInfo(request(id, "serverinfo").getOrError(JsonObject.class).get(0));
    }

    public List<Channel> getChannels(int id) throws TSWebQueryException {
        List<Channel> channels = new ArrayList<>();
        request(id, "channellist").getOrError(JsonObject.class).forEach(e -> channels.add(new Channel(e)));
        return channels;
    }

    public List<Client> getClients(int id) throws TSWebQueryException {
        List<Client> clients = new ArrayList<>();
        request(id, "clientlist").getOrError(JsonObject.class).forEach(e -> clients.add(new Client(e)));
        return clients;
    }

    public List<ServerGroup> getServerGroups(int id) throws TSWebQueryException {
        List<ServerGroup> groups = new ArrayList<>();
        request(id, "servergrouplist").getOrError(JsonObject.class).forEach(e -> groups.add(new ServerGroup(e)));
        return groups;
    }

    public void editServer(int id, ServerInfo info) throws TSWebQueryException {
        request(id, "serveredit", info).orError();
    }

    public List<Token> getTokens(int id) throws TSWebQueryException {
        List<Token> tokens = new ArrayList<>();
        request(id, "tokenlist").getOrError(JsonObject.class).forEach(e -> tokens.add(new Token(e)));
        return tokens;
    }

    public void deleteToken(int id, String token) throws TSWebQueryException {
        request(id, "tokendelete", map("token", token)).orError();
    }

    public void startServer(int id) throws TSWebQueryException {
        request("serverstart", map("sid", String.valueOf(id))).orError();
    }

    public void stopServer(int id) throws TSWebQueryException {
        request("serverstop", map("sid", String.valueOf(id))).orError();
    }

    public void deleteServer(int id) throws TSWebQueryException {
        request("serverdelete", map("sid", String.valueOf(id))).orError();
    }

    public int createServer(ServerInfo info) throws TSWebQueryException {
        return request("servercreate", info).getOrError(JsonObject.class).get(0).get("sid").getAsInt();
    }

    public ClientInfo getClientInfo(int id, int clientId) throws TSWebQueryException {
        return new ClientInfo(request(id, "clientinfo", map("clid", String.valueOf(clientId))).getOrError(JsonObject.class).get(0));
    }

    public void sendGlobalMessage(String msg) throws TSWebQueryException {
        request("gm", map("msg", msg)).orError();
    }

    public Snapshot createSnapshot(int id) throws TSWebQueryException {
        return request(id, "serversnapshotcreate").getOrError(Snapshot.class).get(0);
    }

    public void deploySnapshot(int id, Snapshot snapshot) throws TSWebQueryException {
        request(id, "serversnapshotdeploy", map("version", snapshot.version, "data", snapshot.data)).orError();
    }

    public void deploySnapshot(int id, String version, String data) throws TSWebQueryException {
        request(id, "serversnapshotdeploy", map("version", version, "data", data)).orError();
    }

    public String addServerGroupToken(int id, int groupId, String description) throws TSWebQueryException {
        return request(id, "tokenadd", map("tokentype", "0", "tokenid1", String.valueOf(groupId), "tokenid2", "0", "tokendescription", description)).getOrError(JsonObject.class).get(0).get("token").getAsString();
    }

    public String addChannelGroupToken(int id, int groupId, String description) throws TSWebQueryException {
        return request(id, "tokenadd", map("tokentype", "0", "tokenid2", String.valueOf(groupId), "tokenid1", "0", "tokendescription", description)).getOrError(JsonObject.class).get(0).get("token").getAsString();
    }

    public TSWebQueryResponse request(int id, String command){
        return request(id, command, new HashMap<>());
    }

    public TSWebQueryResponse request(int id, String command, Map<String, String> params){
        return requestRaw("/"+id+"/"+command, params);
    }

    public TSWebQueryResponse request(String command){
        return request(command, new HashMap<>());
    }

    public TSWebQueryResponse request(String command, Map<String, String> params){
        return requestRaw("/"+command, params);
    }

    private TSWebQueryResponse requestRaw(String path){
        return request(path, new HashMap<>());
    }

    private TSWebQueryResponse requestRaw(String path, Map<String, String> params){
        return TSWebQueryResponse.from(requestHttp(path+"?"+queryParams(params)));
    }

    private static Map<String, String> map(String... strings){
        if(strings.length%2!=0)
            return null;
        Map<String, String> map = new HashMap<>();
        for(int i=0; i<strings.length; i+=2){
            map.put(strings[i], strings[i+1]);
        }
        return map;
    }

    private String requestHttp(String path){
        HttpURLConnection conn = null;
        try{
            URL theUrl = new URL(url+path);
            conn = (HttpURLConnection) theUrl.openConnection();
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("x-api-key", this.apiKey);
            conn.setDoInput(true);
            int responseCode = conn.getResponseCode();
            if(responseCode>299){
                return readAll(conn.getErrorStream());
            }else{
                return readAll(conn.getInputStream());
            }
        }catch(Exception e){
            try {
                return readAll(conn.getErrorStream());
            }catch(IOException | NullPointerException ex){}
        }
        return null;
    }

    private static String readAll(InputStream stream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int r;
        byte[] b = new byte[1024];
        while ((r = stream.read(b)) != -1)
            baos.write(b, 0, r);
        return new String(baos.toByteArray(), StandardCharsets.UTF_8);
    }

    private static String urlEncode(String value){
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }

    private static String queryParams(Map<String, String> params){
        List<String> p = new ArrayList<>();
        for(String key : params.keySet()){
            p.add(urlEncode(key)+"="+urlEncode(params.get(key)));
        }
        return String.join("&", p.toArray(new String[0]));
    }

}
