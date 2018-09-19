package pl.coderslab.Service;

import com.github.javafaker.Faker;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

import static pl.coderslab.Service.RandomService.randomNumber;

@Service
public class FakerService {
    private ArrayList<JSONObject> todayGames = new ArrayList<>();
    private ArrayList<JSONObject> countries = new ArrayList<>();
    private ArrayList<JSONObject> leagues = new ArrayList<>();
    private ArrayList<JSONObject> teams = new ArrayList<>();
    private ArrayList<JSONObject> leaguesInCountry = new ArrayList<>();
    private ArrayList<JSONObject> players = new ArrayList<>();
    private ArrayList<JSONObject> matchResults = new ArrayList<>();
    private LocalDate cal;


    @Scheduled(fixedRate = 5000)
    public void regenerate() throws JSONException {
        Faker faker = new Faker();
        todayGames.clear();
        cal = LocalDate.now();
        teams=getTeams();
        leagues=getLeaguesInCountry();
        for (int i = 0; i < 1; i++) {
            JSONObject oJsonInner = new JSONObject();
            oJsonInner.put("Match_id", i+1 );
            oJsonInner.put("matchDate",cal );
            oJsonInner.put("firstTeam", teams.get(randomNumber(teams)));
            oJsonInner.put("firstPoints", faker.number().randomDigitNotZero());
            oJsonInner.put("secondTeam", teams.get(randomNumber(teams)));
            oJsonInner.put("secondPoints", faker.number().randomDigitNotZero());
            oJsonInner.put("League", leagues.get(randomNumber(leagues)));
            todayGames.add(oJsonInner);
        }
    }

    FakerService() throws JSONException {

        this.generateCountries();
//        this.generateLeagues();
        this.generateCountryLeagues();
        this.generateTeams();
        this.regenerate();
        this.generateLeaguesWithLimit(2);
        this.generatePlayers();
    }

    public ArrayList<JSONObject> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<JSONObject> players) {
        this.players = players;
    }

    public ArrayList<JSONObject> getTodayGames() {
        return todayGames;
    }

    public ArrayList<JSONObject> getCountries() {
        return countries;
    }

    public ArrayList<JSONObject> getLeagues() {
        return leagues;
    }



    public ArrayList<JSONObject> getTeams() {
        return teams;
    }

    public ArrayList<JSONObject> getLeaguesInCountry() {
        return leaguesInCountry;
    }

//    @Scheduled(fixedRate = 5000)
    public void generateCountries() throws JSONException {
        Faker faker = new Faker();
        countries.clear();
        for (int i = 0; i < 4; i++) {
            JSONObject countryJson = new JSONObject();
            countryJson.put("Country_id", i + 1);
            countryJson.put("Country", faker.address().country());
            countryJson.put("Capital", faker.address().cityName());
            countries.add(countryJson);
        }

    }

//    @Scheduled(fixedRate = 5000)
//    public void generateLeagues() throws JSONException {
//        Faker faker = new Faker();
//        leagues.clear();
//        for (int i = 0; i < 20; i++) {
//            JSONObject leagueJsaon = new JSONObject();
//            leagueJsaon.put("id", i);
//            leagueJsaon.put("League1", faker.team().sport());
//            leagueJsaon.put("League2", faker.team().sport());
//            leagueJsaon.put("League3", faker.team().sport());
//            leagueJsaon.put("League4", faker.team().sport());
//            leagueJsaon.put("League5", faker.team().sport());
//            leagues.add(leagueJsaon);
//
//        }
//    }


    public ArrayList<JSONObject> generateLeaguesWithLimit(int limit) throws JSONException {
        Faker faker = new Faker();
        leagues.clear();
        for (int i = 1; i <= limit; i++) {
            JSONObject leagueJson = new JSONObject();
            leagueJson.put("League_id", i);
            leagueJson.put("League", faker.team().sport());
            leagueJson.put("Teams", generateTeams());
            leagues.add(leagueJson);

        }
        return leagues;
    }

    public ArrayList<JSONObject> generateCountryLeagues() throws JSONException {
        leaguesInCountry.clear();
        countries = getCountries();
        for (int i = 0; i < 1; i++) {
            JSONObject countryJson = countries.get(i);
            JSONObject leaguesJSON = new JSONObject();
            leaguesJSON.put("Country", countryJson);
            leaguesJSON.put("Leagues", generateLeaguesWithLimit(1));
            leaguesInCountry.add(leaguesJSON);
        }
        return leaguesInCountry;
    }

    public ArrayList<JSONObject> generateTeams() throws JSONException {
        Faker faker = new Faker();
        teams.clear();
        for (int i = 0; i < 35; i++) {
            JSONObject teamJSON = new JSONObject();
            teamJSON.put("Team_id", i + 1);
            teamJSON.put("Team", faker.team().name());
            teams.add(teamJSON);
        }
        return teams;
    }


    public ArrayList<JSONObject> generatePlayers() throws JSONException {
        Faker faker = new Faker();
        players.clear();
        int teamId = 1;
        for (int i = 0; i < 550; i++) {
            JSONObject playerJSON = new JSONObject();
            playerJSON.put("firstName", faker.name().firstName());
            playerJSON.put("lastName", faker.name().lastName());

            if(teamId>50){
                teamId = 1;
            }
            playerJSON.put("team_id", teamId);
            teamId++;
            players.add(playerJSON);
        }
        return players;
    }


}
