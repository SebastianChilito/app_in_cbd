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
public class Login {

    private int _id;
    private String username;
    private String password;
    private Users user;

    public Login(int _id, String username, String password, Users user) {
        this._id = _id;
        this.username = username;
        this.password = password;
        this.user = user;
    }

    public int getId() {
        return _id;
    }
    public void setId(int _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Login{" + "_id=" + _id + ", username=" + username + ", password=" + password + ", user=" + user + '}';
    }
    
}
