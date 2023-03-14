package com.example.gordoapi;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private static ArrayList<Greeting> arr = new ArrayList<Greeting>();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        Greeting g = new Greeting(counter.incrementAndGet(), String.format(template, name));
        arr.add(g);
        return g;
    }

    @GetMapping("/getContent/{id}")
    public Greeting getContent(@PathVariable int id) {
//        int ids = Integer.valueOf(id);
        return arr.get(id);
    }

    @PutMapping("/setContent/{id}") // temporarily a Get not Put bc browser default is Get
    public void setContent(@PathVariable int id, @RequestBody Greeting newGreeting) {
        arr.get(id).setContent(newGreeting.getContent());
    }

}
