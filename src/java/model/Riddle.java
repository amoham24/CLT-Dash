/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author MrAye
 */
public class Riddle implements Serializable {

    private String code;
    private String title;
    private String riddle;

    public Riddle() {
    }

    public Riddle(String code, String title, String riddle) {
        this.code = code;
        this.title = title;
        this.riddle = riddle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRiddle() {
        return riddle;
    }

    public void setRiddle(String riddle) {
        this.riddle = riddle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
