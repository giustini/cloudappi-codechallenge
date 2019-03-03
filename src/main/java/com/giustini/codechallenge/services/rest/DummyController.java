package com.giustini.codechallenge.services.rest;

import com.giustini.codechallenge.models.Dummy;
import com.giustini.codechallenge.services.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("dummies")
public class DummyController {

    @Autowired
    DummyService dummyService;

    @GetMapping
    public ResponseEntity<List<Dummy>> getDummies() {
        return new ResponseEntity<>(dummyService.getDummies(), HttpStatus.OK);
    }
}
