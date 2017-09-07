package servlets;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import weather.data.Location;
import weather.service.AbstractableService;

import java.util.List;

@Controller
public class RestController {

    @Autowired
    private AbstractableService<Location, String> locationService;

    @RequestMapping(value="/getforecast", produces = "application/json")
    public String get(@RequestParam("city") String city, Model map){
        Location location = locationService.get(city);
        map.addAttribute("location",location);
        return "jsonTemplate";
    }

    @RequestMapping(value="/getlocations", produces = "application/json")
    public String getAll(Model map){
        List<Location> locations = locationService.getAll();
        map.addAttribute("locations",locations);
        return "jsonTemplate";
    }
}
