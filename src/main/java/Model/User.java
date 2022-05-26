package Model;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class User {
    private static ArrayList<User> users=new ArrayList<>();

    private String username;
    private String password;
    private String avatar;
    private int score=0;
    private int highScore=0;
    private static User userLogged;
    public User(String username,String password){
        this.username=username;
        this.password=password;
        this.avatar= getClass().getResource("/images/avatars/"+new Random().nextInt(6)+".png").toString();
        users.add(this);
        User.updateUsersInfo();
    }

    public static void setUsers (ArrayList<User> users) {
        User.users = users;
    }

    public static ArrayList<User> getUsers () {
        return users;
    }

    public static void updateUsersInfo()
    {
        try {
            FileWriter writer=new FileWriter("src\\main\\resources\\json\\users.json");
            writer.write(new Gson().toJson(User.getUsers()));
            writer.close();
            for (User user : User.getUsers()) {
                user.setScore(0);
            }
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    public String getUsername () {
        return username;
    }

    public String getPassword () {
        return password;
    }

    public static User getUserLogged () {
        return userLogged;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public static void setUserLogged (User userLogged) {
        User.userLogged = userLogged;
    }

    public int getScore () {
        return score;
    }

    public void setScore (int score) {
        this.score = score;
    }

    public int getHighScore () {
        return highScore;
    }

    public void setHighScore (int highScore) {
        this.highScore = highScore;
    }

    public String getAvatar () {
        return avatar;
    }

    public void setAvatar (String avatar) {
        this.avatar = avatar;
    }
}
