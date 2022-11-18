package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.entity.Hoge;
import demo.mapper.HogeMapper;

@Service
public class HogeService {

    @Autowired
    private HogeMapper mapper;

    @Transactional(readOnly = true)
    public Hoge readById(String id) {
        return this.mapper.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Hoge> readAll() {
        return this.mapper.findAll();
    }

}
