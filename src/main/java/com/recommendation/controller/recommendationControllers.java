package com.recommendation.controller;

import com.recommendation.service.RecommendationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Recommendation Management System", description = "Operations pertaining to recommendations")
@RestController
public class recommendationControllers {
    // search the inventory given name
    @Autowired
    RecommendationService recommendationService;

    @ApiOperation(value = "obtain a list of recommended items for given user")
    @RequestMapping(value = "/getRecommendedItems", method = RequestMethod.GET)
    public List<?> getRecommendedItems(@ApiParam(value = "given userId", required = true)@RequestParam Integer userId) throws Exception {
        return recommendationService.makeRecommendation(userId);
    }
}
