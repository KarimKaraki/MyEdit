/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author karim
 */
public class User {
    public String name;
    public String pass;
    public String[] File_Names;

    public User(String name, String pass, String[] File_Names) {
        this.name = name;
        this.pass = pass;
        this.File_Names = File_Names;
    }
    public User(String name, String pass){
        this.name = name;
        this.pass = pass;
        
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String[] getFile_Names() {
        if(this.File_Names != null){
            return File_Names;
        }else{
            String[] empty = {""};
            return empty;
        }
    }

    public void setFile_Names(String[] File_Names) {
        this.File_Names = File_Names;
    }
    
    
    
}
