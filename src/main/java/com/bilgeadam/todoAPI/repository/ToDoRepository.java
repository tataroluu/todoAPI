package com.bilgeadam.todoAPI.repository;

import com.bilgeadam.todoAPI.constants.TodoSqlConstants;
import com.bilgeadam.todoAPI.model.Todo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
@AllArgsConstructor
@NoArgsConstructor
public class ToDoRepository {

    @Autowired
    public JdbcTemplate jdbcTemplate;
    @Autowired
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public List<Todo> getAll() {
        List<Todo> liste;
        RowMapper<Todo> row_mapper = new RowMapper<Todo>()
        {
            @Override
            public Todo mapRow(ResultSet result, int rowNum) throws SQLException {
                return new Todo(result.getLong(1),
                                result.getLong(2),
                                result.getString(3),
                                result.getString(4),
                                result.getLong(5),
                                result.getBoolean(6),
                                result.getBoolean(7));
            }
        };
        liste = jdbcTemplate.query(TodoSqlConstants.getGetAllTodoSql(), row_mapper);
        return liste;
    }
    public boolean deleteById(Long id) {
        boolean isDeleted = false;
        try {
            jdbcTemplate.update(TodoSqlConstants.getDeleteByIdTodoSql(), id);
            isDeleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }
    public boolean save(Todo todo) {
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("id", todo.getID());
        paramMap.put("userId", todo.getUserId());
        paramMap.put("todo", todo.getTodo());
        paramMap.put("date", todo.getDateTime());
        paramMap.put("priority", todo.getPriority());
        paramMap.put("inProgress", todo.getInProgress());
        paramMap.put("done", todo.getDone());
        return namedParameterJdbcTemplate.update(TodoSqlConstants.getSaveTodoSql(), paramMap) == 1;
    }
    public boolean update(Todo todo, Long id) {
        return jdbcTemplate.update(TodoSqlConstants.getUpdateByIdTodoSql(),
                new Object[]{
                        todo.getID(),
                        todo.getUserId(),
                        todo.getTodo(),
                        id}) == 1;
    }
}
