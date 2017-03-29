/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Models.Articles;
import inventarios_cbd.JSONParser;
import java.awt.Component;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
/**
 *
 * @author Sebastian
 */
public class Utils {
    
    JSONParser jsonParser;
    public final String URL = "http://clinicabiotecnologiadental.com/inventory/";
    
    private String url_send = null; 
    public final String TAG_SUCCESS = "success";
    public final String TAG_MESSAGE = "message";
    public final String TAG_DATA = "data";
    public final String TAG_CODE = "code";
    
    
    public Utils() {
        jsonParser = new JSONParser();
    }
    
    /**
     * Validacion de campos(String)
     * @param strArr
     * @return true: null or empty
     */
    public boolean isNullOrEmpty(String... strArr) {
       for (String st : strArr) {
            if  (st == null || st.equals(""))
               return true;
       } 
       return false;
    }
    
    /**
     * Veirifica connecion a internet
     * @param api URL de la api
     * @return verificador de acceso
     */
    public boolean netIsAvailable(String api) {
        try {
            URL url = new URL(api);
            URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }
    
    /**
     * Obtiene el numero serial codificado del codigo de barras
     * @param article
     * @param centerCons
     * @return 
     */
    public String getIntForCodebar(Articles article, String centerCons) {
        String codebar;
        StringBuilder sbStr =  new StringBuilder();
        
        sbStr.append(getInitialsName(article.getName()));
        sbStr.append(getIntDates(article.getExpiration_date()));
        sbStr.append(article.getDuration());
        sbStr.append(centerCons);
        codebar = sbStr.toString();
        return codebar;
    }
    
    /**
     * Obtiene el codigo ASCII de cada primera letra de la cadena
     * @param name
     * @return 
     */
    public int getInitialsName(String name){
        int res = 0;
        StringBuilder sbStr =  new StringBuilder();
        StringBuilder sbInt =  new StringBuilder();
        StringTokenizer st = new StringTokenizer(name," ");
        
        while(st.hasMoreTokens()){
            sbStr.append(st.nextToken().charAt(0));
        }
        for (int i = 0; i < sbStr.toString().length(); i++) {
            //sbInt.append(sbStr.codePointAt(i));
            res += sbStr.codePointAt(i);
        }
        //res = Integer.parseInt(sbInt.toString());
        return res;
    } 
    
    /**
     * Obtiene los datos entereso de la fecha yyyy/mm/dd  
     * @param date
     * @return ddmmyy(int)
     */
    public int getIntDates(Date date){
        String str;
        int res;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        str = sdf.format(date);
        StringTokenizer st = new StringTokenizer(str,"/");
        StringBuilder sbInt =  new StringBuilder();
        while(st.hasMoreTokens()){
            sbInt.append(st.nextToken());
        }
        res = Integer.parseInt(sbInt.toString());
        return res;
    }
   
    /**
     * Convierte un objecto a un mapa (Clave, Valor);
     * @param obj
     * @return 
     * @throws java.lang.Exception
     */
    public static Map<String, Object> objectToMap(Articles obj) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        BeanInfo info = Introspector.getBeanInfo(obj.getClass());
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            Method reader = pd.getReadMethod();
            if (reader != null)
                result.put(pd.getName(), reader.invoke(obj));
        }
        return result;
    }
    
    /**
     * Enviar los datos a la API dependiendo de la url
     * @param url
     * @param c
     */
    public void sendDataApi(String url, Component c){
        new Thread(new Runnable() {
                int success;
                @Override
                public void run() {
                    try{
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("pa_identification", "1"));
                        
                        if(netIsAvailable(URL)){
                            JSONObject json = jsonParser.makeHttpRequest(
                                url, "GET", params);
                            success = json.getInt(TAG_CODE);

                            if(success == 202){
                                showDialog(c, "Los datos se han guardado sactisfactoriamente", "Guardar Datos", INFORMATION_MESSAGE);
                                System.out.println(json.toString());
                            }
                        } else{
                            showDialog(c, "Verifique la conexion a internet\n "
                                    + "No se guardó la información", "Conexión", ERROR_MESSAGE);
                        }
                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                }
              }).start();
    }
   
    /**
     * Enviar los datos a la API dependiendo de la url y los 
     * @param map
     * @param str_add
     * @param c
     */
    public void sendDataApiEx(Component c, String str_add, Map<String,String> map){
        
        new Thread(new Runnable() {
                int success;
                @Override
                public void run() {
                    try{
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                        }
                        //params.add(new BasicNameValuePair("pa_identification", "1"));            
                        if(netIsAvailable(URL + str_add)){
                            JSONObject json = jsonParser.makeHttpRequest(
                                URL + str_add, "POST", params);
                            success = json.getInt(TAG_CODE);

                            if(success == 202){
                                showDialog(c, "Los datos se han guardado sactisfactoriamente", "Guardar Datos", INFORMATION_MESSAGE);
                                System.out.println(json.toString());
                            }
                        } else{
                            showDialog(c, "Verifique la conexion a internet\n "
                                    + "No se guardó la información", "Conexión", ERROR_MESSAGE);
                        }
                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                }
              }).start();
        url_send = null;
    }
    
    public ArrayList getDataApi(Component c, String str_add){
        System.out.println(URL +str_add);
        ArrayList al =  new ArrayList();
        new Thread(new Runnable() {
                int success;
                @Override
                public void run() {
                    try{
                        if(netIsAvailable(URL +str_add)){
                        //if(true){
                            JSONObject json = jsonParser.makeHttpRequest(
                                URL +str_add, "GET", null);
                            success = json.getInt(TAG_CODE);
                            System.out.println(json.toString());
                            if(success == 202){
                                showDialog(c, "OK", "Información", INFORMATION_MESSAGE);
                                //System.out.println(json.toString());
                            }
                        } else{
                            showDialog(c, "Verifique la conexion a internet\n "
                                    + "No se guardó la información", "Conexión", ERROR_MESSAGE);
                        }
                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                }
              }).start();
        return al;
    }
    
    /**
     * Muestra cuadro de dialogo
     * @param c
     * @param message
     * @param title
     * @param type
     */
    public void showDialog(Component c, String message, String title, int type){
        JOptionPane.showInternalMessageDialog(c, message, title, type);
    }

}

