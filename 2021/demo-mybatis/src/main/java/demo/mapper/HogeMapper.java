package demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HogeMapper<HogeDto> {

    void create(HogeDto hogeDto);

    void edit(HogeDto hogeDto);

    void remove(String id);

    HogeDto find(String id);

    List<HogeDto> findAll();
}
