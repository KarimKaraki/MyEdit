/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author karim
 */
public class My_File {
    public String name;
    public String Creator;
    public String[] members;
    public String[] Admins;
    public String[] only_view;
    public String content;

    public My_File(String name){
        this.name = name;
    }
    public My_File(String name, String Creator, String[] members, String[] Admins, String[] only_view, String content) {
        this.name = name;
        this.Creator = Creator;
        this.members = members;
        this.Admins = Admins;
        this.only_view = only_view;
        this.content = content;
    }
    public My_File(String name, String Creator, String[] members, String[] Admins, String[] only_view) {
        this.name = name;
        this.Creator = Creator;
        this.members = members;
        this.Admins = Admins;
        this.only_view = only_view;
    }
    public My_File(String name, String[] members, String[] Admins, String[] only_view) {
        this.name = name;
        this.members = members;
        this.Admins = Admins;
    }
    
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }

    public String[] getMembers() {
        return members;
    }

    public void setMembers(String[] members) {
        this.members = members;
    }

    public String[] getAdmins() {
        return Admins;
    }

    public void setAdmins(String[] Admins) {
        this.Admins = Admins;
    }


    public String[] getOnly_view() {
        return only_view;
    }

    public void setOnly_view(String[] only_view) {
        this.only_view = only_view;
    }
    
    public String getContent(){
        return content;
    }
    
    
    
}
