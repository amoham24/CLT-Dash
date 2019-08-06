/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.ArrayList;
import java.util.Date;
import model.Profile;

/**
 *
 * @author MrAye
 */
public class ProfileDB {

    static ArrayList<Profile> users = new ArrayList<>();
    //static ArrayList<UserProfile> userProfiles = new ArrayList<>();

    public static ArrayList<Profile> getUsers() {
        return users;
    }

    public static boolean isThere(String email) {
        if (email == null) {
            return false;
        }
        for (Profile p : users) {
            
            System.out.println("user is " + p);
            System.out.println("users email is " + p.getEmail());
            if (p.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static void addProfile(Profile p) {
        users.add(p);
    }

    public static Profile getProfile(String email) {
        for (Profile p : users) {
            if (p.getEmail().equals(email)) {
                return p;
            }
        }
        return null;
    }

    public static boolean isAdmin(String email) {
        ArrayList<String> admins = new ArrayList<>();
        admins.add("amoham24@uncc.edu");

        //add more mods here
        for (String i : admins) {
            if (i.equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Profile> getTopTen() {
        ArrayList<Profile> people = new ArrayList<>();
        for(Profile p : users) { 
            people.add(p);
        }
        
        addDefaults(people);
        
        Profile[] sorted = new Profile[people.size()];
        for (int i = 0; i < people.size(); i++) {
            sorted[i] = people.get(i);
        }
        Profile[] list = bubbleSort(sorted);
        ArrayList<Profile> top10 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            top10.add(list[i]);
        }
        System.out.println(top10);
        return top10;

    }
    
    public static ArrayList<Profile> getLeaderboard() {
        ArrayList<Profile> people = new ArrayList<>();
        for(Profile p : users) { 
            people.add(p);
        }
        
        
        
        addDefaults(people);
        
        Profile[] sorted = new Profile[people.size()];
        for (int i = 0; i < people.size(); i++) {
            sorted[i] = people.get(i);
        }
        Profile[] list = bubbleSort(sorted);
        ArrayList<Profile> top10 = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            top10.add(list[i]);
        }
        return top10;

    }
    
    public static void addDefaults(ArrayList<Profile> list) {
        long[] times1 = {0,377000000, 744000000,1120000000, 1442000000, 1800110000};
        list.add(new Profile("widowbaby@email", 5.0, times1));
        
        long[] times2 = new long[6];
        for(int i=1;i< 6;i++) {
            times2[i] = twoHoursLess(times1[i]);
        }
        list.add(new Profile("lilianpumpernickle@email", 7.0, times2));
        
        long[] times3= new long[6];
        for(int i=1;i< 6;i++) {
            times3[i] = twoHoursLess(times2[i]);
        }
        list.add(new Profile("treswayz@email", 12.0, times3));
        
        long[] times4 = new long[6];
        for(int i=1;i< 6;i++) {
            times4[i] = twoHoursLess(times3[i]);
        }
        list.add(new Profile("daddyillson@email", 15.0, times4));
        
        long[] times5 = new long[6];
        for(int i=1;i< 6;i++) {
            times5[i] = twoHoursLess(times4[i]);
        }
        list.add(new Profile("jsonajx@email", 21.0, times5));
        
        long[] times6 = new long[6];
        for(int i=1;i< 6;i++) {
            times6[i] = twoHoursLess(times5[i]);
        }
        list.add(new Profile("ethanbradberry@email", 25.0, times6));
        
        long[] times7 = new long[6];
        for(int i=1;i< 6;i++) {
            times7[i] = twoHoursLess(times5[i]);
        }
        list.add(new Profile("pambeasly@email", 31.0, times7));
        
        long[] times8 = new long[6];
        for(int i=1;i< 6;i++) {
            times8[i] = twoHoursLess(times7[i]);
        }
        list.add(new Profile("jamesbudd@email", 35.0, times8));
        
        long[] times9 = new long[6];
        for(int i=1;i< 6;i++) {
            times9[i] = twoHoursLess(times8[i]);
        }
        list.add(new Profile("vivaclt@email", 40.0, times9));
        
        long[] times10 = new long[6];
        for(int i=1;i< 6;i++) {
            times10[i] = twoHoursLess(times9[i]);
        }
        list.add(new Profile("thebigboy@email", 50.0, times10));
        
        
//        ArrayList<ArrayList<Long>> defaults = new ArrayList<>();
//        ArrayList<Long> time = new ArrayList<>(); //50 hours
//        time.add(0l);
//        time.add(180000000l);
//        time.add(360000000l);
//        time.add(540000000l);
//        time.add(720000000l);
//        time.add(900000000l);
//        defaults.add(time);
//        
//        for(int i = 1; i < 9; i++) {
//            ArrayList<Long> timex = new ArrayList<>();
//            for(Long x : defaults.get(i-1)) {
//                timex.add(x - 7200000l);
//            }
//            defaults.add(timex);
//        }
//        
//        for(ArrayList<Long> i : defaults) {
//            long[] temp = new long[i.size()];
//            for(int x = 0; x< temp.length; x++) {
//                temp[x] = i.get(x);
//            }
//            list.add(new Profile("email@email.com",scoreCalc(i),temp));
//        }
    }
    public static long twoHoursLess(long mill) {
        return mill /2 ;
    }

    public static Profile[] bubbleSort(Profile[] array) {
        int n = array.length;
        Profile temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (array[j - 1].getScore() < array[j].getScore()) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    public static String turnToUnits(long milliseconds) {
        int seconds = (int) (milliseconds / 1000) % 60;
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) );
        String out = hours + "h:" + minutes + "m:" + seconds + "s";
        return out;
    }
    public static double turnToMinutes(long milliseconds) {
        double minutes =  milliseconds / (1000.0 * 60.0);
        return minutes;
    }
}
