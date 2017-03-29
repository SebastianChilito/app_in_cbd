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
public class Users {
    
    private int _id;
    private String name;
    private String last_name;
    private int age;
    private String email;
    private String phone;
    private String cellphone;
    private String rol;
    private String specialty;
    private Articles article;
    private Center center;
    private String office;

    public Users(int _id, String name, String last_name, int age, String email,
            String phone, String cellphone, String rol, String specialty,
            Articles article, Center center, String office) {
        this._id = _id;
        this.name = name;
        this.last_name = last_name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.cellphone = cellphone;
        this.rol = rol;
        this.specialty = specialty;
        this.article = article;
        this.center = center;
        this.office = office;
    }

    public int getId() {
        return _id;
    }
    public void setId(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellphone() {
        return cellphone;
    }
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getSpecialty() {
        return specialty;
    }
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Articles getArticle() {
        return article;
    }
    public void setArticle(Articles article) {
        this.article = article;
    }

    public Center getCenter() {
        return center;
    }
    public void setCenter(Center center) {
        this.center = center;
    }

    public String getOffice() {
        return office;
    }
    public void setOffice(String office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return "Users{" + "_id=" + _id + ", name=" + name + ", last_name=" + last_name + ", age=" + age + ", email=" + email + ", phone=" + phone + ", cellphone=" + cellphone + ", rol=" + rol + ", specialty=" + specialty + ", article=" + article + ", center=" + center + ", office=" + office + '}';
    }
    
}
