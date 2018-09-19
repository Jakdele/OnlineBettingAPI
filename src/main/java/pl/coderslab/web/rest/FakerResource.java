package pl.coderslab.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.Service.FakerService;

@RestController
@RequestMapping("/api")
public class FakerResource {

    @Autowired
    public FakerService fakerService;

    @GetMapping(path = "/fake-today-games")
    public String sample() {
        return fakerService.getTodayGames().toString();
    }

    @GetMapping(path = "/fake-countries")
    public String countries() {
        return fakerService.getCountries().toString();
    }

    @GetMapping(path = "/fake-leagues")
    public String leagues() {
        return fakerService.getLeagues().toString();
    }

    @GetMapping(path = "/fake-country-leagues")
    public String countryLeagues() {
        return fakerService.getLeaguesInCountry().toString();
    }

    @GetMapping(path = "/fake-results")
    public String results() {
        return fakerService.getTodayGames().toString();
    }
    @GetMapping(path = "/fake-teams")
    public String teams() {
        return fakerService.getTeams().toString();
    }
    @GetMapping(path = "/fake-players")
    public String players() {
        return fakerService.getPlayers().toString();
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }
}
