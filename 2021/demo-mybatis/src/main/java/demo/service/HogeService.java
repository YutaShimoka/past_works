package demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.dto.HogeDto;
import demo.mapper.HogeMapper;

@Service
public class HogeService {
    private final HogeMapper<HogeDto> hogeMapper;

    // コンストラクタ
    public HogeService(HogeMapper<HogeDto> hogeMapper) {
        this.hogeMapper = hogeMapper;
    }

    @Transactional
    public void create(HogeDto hogeDto) {
        this.hogeMapper.create(hogeDto);
    }

    @Transactional
    public void update(HogeDto hogeDto) {
        this.hogeMapper.edit(hogeDto);
    }

    @Transactional
    public void delete(String id) {
        this.hogeMapper.remove(id);
    }

    @Transactional(readOnly = true)
    public HogeDto get(String id) {
        return this.hogeMapper.find(id);
    }

    @Transactional(readOnly = true)
    public List<HogeDto> readAll() {
        return this.hogeMapper.findAll();
    }

}
