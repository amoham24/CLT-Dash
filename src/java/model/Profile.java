/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.ProfileDB;
import database.QRListDB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author MrAye
 */
public class Profile implements Serializable {

    private UserRiddles riddles;
    private String email;
    private int mapProgress;
    private double score;
    private long[] times = new long[6];
    private boolean[] skipped = new boolean[6];
    private boolean completed = false;

    public Profile(String email) {
        this.email = email;
        mapProgress = 1;
        score = 0.0;
        riddles = new UserRiddles();
        times[0] = (new Date()).getTime();
    }

    public Profile(String email, double score, long[] times) {
        this.email = email;
        this.score = score;
        this.times = times;
    }

    private void incrimentMap() {
        mapProgress++;
    }

    private void addScore() {
        score += (50.0 / ProfileDB.turnToMinutes(times[mapProgress] - times[mapProgress - 1]));
    }

    public int getMapProgress() {
        return mapProgress;
    }

    public double getScore() {
        return score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDone() {
        return riddles.getMyList().isEmpty();
    }

    public Riddle getCurrentRiddle() {
        if (riddles.getMyList().isEmpty()) {
            return new Riddle("XXX", "Congratulations! You've finished the CLT Dash Scavenger Hunt!", "If you wish to play again just hit the redo button.");
        }
        return QRListDB.find(riddles.getMyList().get(0).getCode());
    }

    public UserRiddles getRiddles() {
        return riddles;
    }

    public void setRiddles(UserRiddles riddles) {
        this.riddles = riddles;
    }

    public void skip() {
        skipped[mapProgress] = true;
        times[mapProgress] = (new Date()).getTime();
        incrimentMap();
    }

    public void advance() {
        times[mapProgress] = (new Date()).getTime();
        if (!completed) {
            addScore();
        }
        incrimentMap();
    }

    public boolean isAdmin() {
        return ProfileDB.isAdmin(email);
    }

    public ArrayList<String> getTimes() {
        ArrayList<String> theTimes = new ArrayList<>();
        for (int i = 1; i < times.length; i++) {
            if (times[i] == 0) {
                theTimes.add("DNF");
            } else if (skipped[i]) {
                theTimes.add("SKP");
            } else {
                long x = times[i] - times[i - 1];
                theTimes.add(ProfileDB.turnToUnits(x));
            }
        }
        return theTimes;
    }

    public String getUserName() {
        return email.substring(0, email.indexOf('@'));
    }

    public void redo() {
        ArrayList<Riddle> redoList = new ArrayList<>();
        for (Riddle r : QRListDB.getS1()) {
            redoList.add(r);
        }
        this.riddles.setMyList(redoList);
        this.completed = true;
        this.mapProgress = 1;
    }

    public int getRank() {
        ArrayList<Profile> x = ProfileDB.getLeaderboard();
        for (int i = 1; i <= x.size(); i++) {
            if (x.get(i-1).getEmail().equals(this.email)) {
                return i;
            }
        }
        return 0;
    }
}
