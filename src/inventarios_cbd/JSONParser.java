package inventarios_cbd;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;


public class JSONParser {
    static InputStream is = null;
    static JSONObject jsonObj ;
    static String json = "";
     // default no argument constructor for jsonpaser class
    public JSONParser() {

    }

    public JSONObject getJSONFromUrl(final String url) {
            // Making HTTP request
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
             // Executing POST request & storing the response from server locally.
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
        // Create a BufferedReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                                        is, "iso-8859-1"), 8);
                                         // Declaring string builder
            StringBuilder str = new StringBuilder();
             // string to store the JSON object.
            String strLine = null;
             // Building while we have string !equal null.
            while ((strLine = reader.readLine()) != null) {
                str.append(strLine + "\n");
            } // Close inputstream.
            is.close();
            // string builder data conversion to string.
            json = str.toString();
        } catch (Exception e) {
            //Log.e("Error", " something wrong with converting result " + e.toString());
        }
        // Try block used for pasrseing String to a json object
        try {
            jsonObj = new JSONObject(json);
        } catch (JSONException e) {
            //Log.e("json Parsering", "" + e.toString());
        } // Returning json Object.
         return jsonObj;
    }


    public JSONObject makeHttpRequest(String url, String method,
                                      List<NameValuePair> params) {
        // Make HTTP request
        
        try {
            // checking request method
            if("POST".equals(method)){
                        // now defaultHttpClient object
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
                System.out.println("POST PARAMS URL JSONParser : " + url);
            }else if("GET".equals(method)) {

                if(params == null){
                    // request method is GET Without params
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    //Log.e("URL get", url);
                    HttpGet httpGet = new HttpGet(url);
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    is = httpEntity.getContent();
                    System.out.println("GET URL JSONParser : " + url);

                }else{
                    // request method is GET
                    HttpClient httpClient = new DefaultHttpClient();
                    String paramString = URLEncodedUtils.format(params, "utf-8");
                    url += "?" + paramString;
                    //Log.e("URL get", url);
                    HttpGet httpGet = new HttpGet(url);
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    is = httpEntity.getContent();
                    System.out.println("URL GET PARAMS JSONParser : " + url);
                }

            } else if("PUT".equals(method)){
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPut httpPut = new HttpPut(url);
                httpPut.setEntity(new UrlEncodedFormEntity(params));
                HttpResponse httpResponse = httpClient.execute(httpPut);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
                System.out.println("URL PUT PARAMS JSONParser : " + url);
            }else if("DELETE".equals(method)){
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpDelete httpDelete = new HttpDelete(url);
                //httpDelete.setEntity(new UrlEncodedFormEntity(params));
                HttpResponse httpResponse = httpClient.execute(httpDelete);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
                System.out.println("URL DELETE PARAMS JSONParser : " + url);
            }
        } catch (UnsupportedEncodingException e) {
                //e.printStackTrace();
                System.out.println("hola1");
        } catch (ClientProtocolException e) {
                //e.printStackTrace();
                System.out.println("hola2");
        } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("hola3");
        }
            try {

                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        is, "iso-8859-1"), 8);
                StringBuilder str = new StringBuilder();
                String strLine = null;
                while ((strLine = reader.readLine()) != null) {
                    str.append(strLine + "\n");
                }
                is.close();
                json = str.toString();
                //Log.e("OBJETO JSON", ""+ json);
            } catch (Exception e) {

            }
            // now will try to parse the string into JSON object
            try {
                jsonObj = new JSONObject(json);
            } catch (JSONException e) {
                System.err.println("Error json, JSONParser 170" + e.toString());
            }
        return jsonObj;
    }
    
    public void ejemplo() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet("http://medicalhealthcard.net/conexion_app/login.php");
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            // The underlying HTTP connection is still held by the response object
            // to allow the response content to be streamed directly from the network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST call CloseableHttpResponse#close() from a finally clause.
            // Please note that if response content is not fully consumed the underlying
            // connection cannot be safely re-used and will be shut down and discarded
            // by the connection manager.
            try {
                System.out.println(response1.getStatusLine());
                HttpEntity entity1 = response1.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity1);
            } finally {
                response1.close();
            }

            HttpPost httpPost = new HttpPost("http://httpbin.org/post");
            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
            nvps.add(new BasicNameValuePair("username", "vip"));
            nvps.add(new BasicNameValuePair("password", "secret"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response2 = httpclient.execute(httpPost);

            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity2);
            } finally {
                response2.close();
            }
        } finally {
            httpclient.close();
        }
    }
}

