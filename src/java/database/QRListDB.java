/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.ArrayList;
import java.util.Hashtable;
import model.Riddle;

/**
 *
 * @author MrAye
 */
public class QRListDB {

    static ArrayList<Riddle> s1 = new ArrayList<Riddle>() {{
        add(new Riddle("0", "Riddle 1", "You'll spend most of your time here, in all of its glory. I'm pretty well known, and I have the most stories..."));
        add(new Riddle("1", "Riddle 2", "A basketball court with a place you can dine, here in this building you're always in time."));
        add(new Riddle("2", "Riddle 3", "A place for the bold. Never here? You'll regret. You'll stay here indoors, but you'll still break a sweat."));
        add(new Riddle("3", "Riddle 4", "For this side of campus, some might dismiss. But I assure you there's no building more epic than this!"));
        add(new Riddle("4", "Riddle 5", "I hold the place to where your car lies. But at which one you'll ask? Where does the sun rise?"));
}};

    public QRListDB() {

    }

    public  static ArrayList<Riddle> getS1() {
        return s1;
    }

    

    public static boolean isOurQRCode(String code) {
        return (find(code) != null);
    }
    
    public static void editRiddle(String code, String newTitle, String newRiddle) {
        System.out.println(code);
        System.out.println(newTitle);
        System.out.println(newRiddle);
        find(code).setTitle(newTitle);
        find(code).setRiddle(newRiddle);
    }
    public static Riddle find(String code) {
        System.out.println("in find");
        for(Riddle i : s1) {
            System.out.println(i.getCode().equals(code));
            if(i.getCode().equals(code)) {
                return i;
            }
        }
        return null;
    }
}
