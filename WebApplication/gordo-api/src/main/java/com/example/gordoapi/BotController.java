package com.example.gordoapi;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BotController {

    @Autowired
    private BotRepository repository;

    @PostMapping("/bot/create")
    public Bot botCreateBot(@RequestBody Bot newBot) {
        repository.save(newBot);
        return newBot;
    }

    @PostMapping("/bot/create/{name}")
    public Bot botCreateBot(@PathVariable String name) {
        Bot b = new Bot(name);
        repository.save(b);
        return b;
    }

    @PutMapping("/bot/update/{id}")
    public void botUpdate(@PathVariable String id, @RequestBody Bot newBot) {
        if (repository.existsById(id)){
            Bot b = repository.findById(id).get(); // should never be null
            b.update(newBot);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
    }

    @GetMapping("/bot/get/{id}")
    public Bot get(@PathVariable String id) {
        if (repository.existsById(id)){
            return repository.findById(id).get(); // should never be null
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
    }

    @PostMapping("/bot/execute/{id}")
    public void botExecute(@PathVariable String id, @RequestBody Step s) {
        if (repository.existsById(id)){
            Bot b = repository.findById(id).get(); // should never be null
            b.execute(s);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
    }
}
