package com.techelevator.controller;

import com.techelevator.dao.ReportDAO;
import com.techelevator.model.FoodDTO;
import com.techelevator.model.ReportDTO;
import com.techelevator.model.ReportFilterDataDTO;
import com.techelevator.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class RecipeController {
@Autowired
    RecipeService recipeService;
    public RecipeController() {

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{carbs}/food", method = RequestMethod.GET)
    public FoodDTO test(@PathVariable int carbs) {
        List<Integer> list = recipeService.getSearchResults(carbs);
        int b = (int)(Math.random()*((list.size()-1)-0+1)+0);
        return recipeService.getFoodInfo(list.get(b));
    }

//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value = "/test1", method = RequestMethod.GET)
//    public FoodDTO test1() {
//        int id = 645092;
//        return recipeService.getFoodInfo(id);
//    }

}