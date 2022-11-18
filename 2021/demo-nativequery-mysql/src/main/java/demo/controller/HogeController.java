package demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.dto.HogeDto;
import demo.entity.Hoge;
import demo.service.HogeService;

@RestController
@RequestMapping("/api/hoge")
public class HogeController {

    @Autowired
    private HogeService service;

    @GetMapping("")
    public List<HogeDto> getHogesBySourceId(@RequestParam("source_id") String sourceId) {
        List<Hoge> hogeList = this.service.readBySourceId(sourceId);
        return hogeList.stream().map(hoge -> HogeDto.valueOf(hoge)).collect(Collectors.toList());
    }
}
