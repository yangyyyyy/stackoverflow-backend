package com.group8.backend.controller;

import com.group8.backend.VO.ResponseVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/chart")
public class ChartController {

    // This is ia test api for remote deploy
    @GetMapping("/hello")
    public ResponseVO hello(){
        return ResponseVO.buildSuccess("hello");
    }



}
