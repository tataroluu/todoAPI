package com.bilgeadam.todoAPI.repository;

import com.bilgeadam.todoAPI.constants.UsersSqlConstants;
import com.bilgeadam.todoAPI.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UsersRepository {
    @Autowired
    public JdbcTemplate jdbcTemplate;
    @Autowired
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Users> getAll() {
        List<Users> liste;

        RowMapper<Users> row_mapper = new RowMapper<Users>() {

            @Override
            public Users mapRow(ResultSet result, int rowNum) throws SQLException {
                return new Users(result.getLong(1), result.getString("username"), result.getString("password"),result.getLong(4));
            }
        };
        liste = jdbcTemplate.query(UsersSqlConstants.getGetAllUserSql(), row_mapper);
        return liste;
    }
}
