/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.jug.torun.merces.rest;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author faramir
 */
//@RestController
public class MercesController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

//    @RequestMapping("/greeting")
//    public Greeting greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
//        return new Greeting(counter.incrementAndGet(),
//                String.format(template, name));
//    }
}
