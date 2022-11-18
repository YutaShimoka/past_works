package demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.dto.HogeDto;
import demo.service.HogeService;

@RestController
@RequestMapping("/api/hoge")
public class HogeController {
    private final HogeService hogeService;

    // コンストラクタ
    public HogeController(HogeService hogeService) {
        this.hogeService = hogeService;
    }

    @GetMapping("{id}")
    public HogeDto getHoge(@PathVariable String id) {
        return this.hogeService.get(id);
    }

    @GetMapping("")
    public List<HogeDto> getHoges() {
        return this.hogeService.readAll();
    }

    @PostMapping("")
    public void registHoge(@RequestBody HogeDto hogeDto) {
        this.hogeService.create(hogeDto);
    }

    @PutMapping("")
    public void editHoge(@RequestBody HogeDto hogeDto) {
        this.hogeService.update(hogeDto);
    }

    @DeleteMapping("{id}")
    public void deleteHoge(@PathVariable String id) {
        this.hogeService.delete(id);
    }
}
