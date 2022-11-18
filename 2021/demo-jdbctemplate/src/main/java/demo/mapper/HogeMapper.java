package demo.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import demo.entity.Hoge;

@Repository
public class HogeMapper {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<Hoge> rowMapper = new DataClassRowMapper<>(Hoge.class);

    public Hoge findById(String id) {
        MapSqlParameterSource paramas = new MapSqlParameterSource().addValue("id", id);
        String query = "select * from hoge where id = :id";
        return jdbcTemplate.queryForObject(query, paramas, this.rowMapper);
    }

    public List<Hoge> findAll() {
        MapSqlParameterSource paramas = new MapSqlParameterSource();
        String query = "select * from hoge";
        return jdbcTemplate.query(query, paramas, this.rowMapper);
    }
}
