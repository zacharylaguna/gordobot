package com.example.gordoapi;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Bot {
    @Id
    private String id; // do not change ID
    private String name;

    private String ipaddress;

    private String port;

    public Bot(){
        this.name = "blank";
    }
    public Bot(String name){
        this.name = name;
        this.ipaddress = "localhost";
        this.port = "5000";
    }

    public Bot(String name, String ipaddress, String port) {
        this.name = name;
        this.ipaddress = ipaddress;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void update (Bot newBot){
        this.name = newBot.getName();
    }

    public void execute(Step s){
        // do stuff here to send step
    }
}
