package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.entity.Hoge;
import demo.repository.HogeRepository;

@Service
public class HogeService {

    @Autowired
    private HogeRepository repository;

    @Transactional(readOnly = true)
    public List<Hoge> readBySourceId(String sourceId) {
        return this.repository.findBySourceId(sourceId);
    }
}
