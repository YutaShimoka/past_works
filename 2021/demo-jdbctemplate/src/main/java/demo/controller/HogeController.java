package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.entity.Hoge;
import demo.service.HogeService;

@RestController
@RequestMapping("/api/hoge")
public class HogeController {

    @Autowired
    private HogeService service;

    @GetMapping(path = "{id}")
    public Hoge getHoge(@PathVariable String id) {
        return this.service.readById(id);
    }

    @GetMapping()
    public List<Hoge> getHoges() {
        return this.service.readAll();
    }
}
