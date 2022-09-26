/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author karim
 */
public class Socket2 extends Socket {
    String host;
    int port;
    String username;

    public Socket2(String host,int port,String name) throws IOException {
        super(host,port);
        this.host=host;
        this.port=port;
        this.username=name;
        
    }
    
    
}
