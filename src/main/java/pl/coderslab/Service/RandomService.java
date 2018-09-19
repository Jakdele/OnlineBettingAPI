package pl.coderslab.Service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class RandomService {

    public static int randomNumber(ArrayList<JSONObject> array){
        Random randTeam = new Random();
        return randTeam.nextInt(array.size());
    }


}
