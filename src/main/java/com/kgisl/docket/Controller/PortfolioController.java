package com.kgisl.docket.Controller;

import java.util.List;

import com.kgisl.docket.Service.PortfolioService;
import com.kgisl.docket.model.Portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;


    @GetMapping("/get")
    public @ResponseBody ResponseEntity<List<Portfolio>> all() {
        System.out.println("GET ALL CALLED");
        return new ResponseEntity<>(portfolioService.getAll(), HttpStatus.OK);
    }
    
    @PostMapping(value = "/post", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<List<Portfolio>> post(@RequestBody List<Portfolio> portfolio) {
        portfolioService.save(portfolio);
        System.out.println("+++++++++++pos now" + portfolio);
        return new ResponseEntity<List<Portfolio>>(portfolio, HttpStatus.OK);
    }



    @RequestMapping(value = "/category/{cat}", method = RequestMethod.GET) // the values like nifty or cnxPharma or
                                                                           // cnxEnergy will come to {cat}
    public ResponseEntity<?> showUserTable(@PathVariable String cat, UriComponentsBuilder builder) { // since it is get
                                                                                                     // method
        System.out.println("???????????????" + cat);
        RestTemplate restTemplate = new RestTemplate();
        String nseDatas = restTemplate.getForObject(
                "https://www.nseindia.com/live_market/dynaContent/live_watch/stock_watch/" + cat + "StockWatch.json",
                String.class); // or however I use restTemplates, havent done it yet so still fuzzy but
                               // shouldnt be too tricky.
        return new ResponseEntity<>(nseDatas, HttpStatus.OK);
    }



    @RequestMapping(value = { "Data" }, method = { RequestMethod.GET })
    public @ResponseBody String showUserTable1() {
        System.out.println("---------------------------------");
        return null;

    }



    @DeleteMapping("/delete/{pId}")
    public ResponseEntity<?> delete(@PathVariable Long pId) {
        System.out.println("Delete Id" + pId);
        portfolioService.delete(pId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
