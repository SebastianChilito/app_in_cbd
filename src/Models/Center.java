/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Sebastian
 */
public class Center {
   
    private String name;
    private String address;
    private String phone;
    private String office;
    //private Users user;

    public Center(String name, String address, String phone, String office) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.office = office;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOffice() {
        return office;
    }
    public void setOffice(String office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return "Center{" + "name=" + name + ", address=" + address + ", phone=" + phone + ", office=" + office + '}';
    }
   
}
