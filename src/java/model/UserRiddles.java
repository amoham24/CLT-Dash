/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.QRListDB;
import java.util.ArrayList;

/**
 *
 * @author MrAye
 */
public class UserRiddles {

    ArrayList<Riddle> listDB;
    ArrayList<Riddle> myList = new ArrayList<>();

    public UserRiddles() {
        listDB = QRListDB.getS1();
        for(Riddle r : QRListDB.getS1()) {
            myList.add(new Riddle(r.getCode(), r.getTitle(), r.getRiddle()));
        }
        
    }

    public ArrayList<Riddle> getMyList() {
        return myList;
    }

    public Riddle next(String code) {
        System.out.println(listDB);
        System.out.println(myList);
        if (myList.isEmpty()) {
            return new Riddle("empty", "Title of done", "info of done");
        } else {
            if (QRListDB.isOurQRCode(code)) {
                if (myList.get(0).getCode().equals(code)) {
                    if (myList.size() == 1) {
                        myList.remove(0);
                        return new Riddle("win", "title of win", "Info of win");
                    } else {
                        return myList.remove(0);
                    }
                }
                if (this.inMyList(code)) {
                    return new Riddle("notnow", "Title of notnow", "Wrong of Info");
                } else {
                    return new Riddle("alreadydone", "Title of notnow", "Wrong of Info");
                }
            } else {
                return new Riddle("notours", "Title of notours", "Wrong of Info");
            }
        }
    }

    public boolean inMyList(String code) {
        for (Riddle i : myList) {
            if (i.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public void setMyList(ArrayList<Riddle> myList) {
        this.myList = myList;
    }

}
