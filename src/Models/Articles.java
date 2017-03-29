/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author Sebastian
 */
public class Articles {
    
    private String ar_code;
    private String ar_name;
    private String ar_unit;
    private int ar_duration;//Meses 
    private double ar_total_price;
    private double ar_cost_unit;
    private String ar_lot;
    private String ar_invima;
    private int ar_output;    //
    private int ar_exist;      //Existencias
    private String ar_distributor;
    private Date ar_expiration_date;
    private String ar_trademark;
    
    
    public Articles(String ar_code, String ar_name, String ar_unit,
            int ar_duration, double ar_total_price, double ar_cost_unit, String ar_lot,
            String ar_invima, int ar_output, int ar_exist, String ar_distributor, Date ar_expiration_date, String ar_trademark) {
        
        this.ar_code = ar_code;
        this.ar_name = ar_name;
        this.ar_unit = ar_unit;
        this.ar_duration = ar_duration;
        this.ar_total_price = ar_total_price;
        this.ar_cost_unit = ar_cost_unit;
        this.ar_lot = ar_lot;
        this.ar_invima = ar_invima;
        this.ar_output = ar_output;
        this.ar_exist = ar_exist;
        this.ar_distributor = ar_distributor;
        this.ar_expiration_date = ar_expiration_date;
        this.ar_trademark = ar_trademark;
    }

    public Articles(String ar_name, int ar_duration, Date ar_expiration_date) {
        this.ar_name = ar_name;
        this.ar_duration = ar_duration;
        this.ar_expiration_date = ar_expiration_date;
    }
    
    public String getCode() {
        return ar_code;
    }
    public void setCode(String ar_code) {
        this.ar_code = ar_code;
    }

    public String getName() {
        return ar_name;
    }
    public void setName(String ar_name) {
        this.ar_name = ar_name;
    }

    public String getUnit() {
        return ar_unit;
    }
    public void setUnit(String ar_unit) {
        this.ar_unit = ar_unit;
    }

    public int getDuration() {
        return ar_duration;
    }
    public void setDuration(int ar_duration) {
        this.ar_duration = ar_duration;
    }

    public double getTotal_price() {
        return ar_total_price;
    }
    public void setTotal_price(double ar_total_price) {
        this.ar_total_price = ar_total_price;
    }
    
    public String getLot() {
        return ar_lot;
    }
    public void setLot(String ar_lot) {
        this.ar_lot = ar_lot;
    }

    public String getInvima() {
        return ar_invima;
    }
    public void setInvima(String ar_invima) {
        this.ar_invima = ar_invima;
    }

    public int getOutputs() {
        return ar_output;
    }
    public void setOutputs(int ar_output) {
        this.ar_output = ar_output;
    }

    public int getExist() {
        return ar_exist;
    }
    public void setExist(int ar_exist) {
        this.ar_exist = ar_exist;
    }

    public String getDistributor() {
        return ar_distributor;
    }
    public void setDistributor(String ar_distributor) {
        this.ar_distributor = ar_distributor;
    }

    public Date getExpiration_date() {
        return ar_expiration_date;
    }
    public void setExpiration_date(Date ar_expiration_date) {
        this.ar_expiration_date = ar_expiration_date;
    }

    public String getAr_trademark() {
        return ar_trademark;
    }
    public void setAr_trademark(String ar_trademark) {
        this.ar_trademark = ar_trademark;
    }

    @Override
    public String toString() {
        return "Articles{" + "ar_code=" + ar_code + ", ar_name=" + ar_name + ", ar_unit=" + ar_unit + ", ar_duration=" + ar_duration + ", ar_total_price=" + ar_total_price + ", ar_cost_unit=" + ar_cost_unit + ", ar_lot=" + ar_lot + ", ar_invima=" + ar_invima + ", ar_output=" + ar_output + ", ar_exist=" + ar_exist + ", ar_distributor=" + ar_distributor + ", ar_expiration_date=" + ar_expiration_date + ", ar_trademark=" + ar_trademark + '}';
    }

}
