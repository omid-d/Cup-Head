package Controller;
import Model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ProfileController {
    private static ProfileController instance;

    private ProfileController()
    {
    }

    public static ProfileController getInstance()
    {
        if(instance==null)
        {
            instance=new ProfileController();
        }
        return instance;
    }


    public String changeUsername(String username){
        for (User user : User.getUsers()) {
            if(user.getUsername().equals(username) && !user.equals(User.getUserLogged())){
                return "user with username " + username + " already exists";
            }
        }
        User.getUserLogged().setUsername(username);
        User.updateUsersInfo();
        return "username changed successfully";
    }

    public String changePassword(String newPassword)
    {
        User.getUserLogged().setPassword(newPassword);
        User.updateUsersInfo();
        return "password changed successfully!";
    }
}
