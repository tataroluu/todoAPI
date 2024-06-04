package com.bilgeadam.todoAPI.constants;

import lombok.Getter;

@Getter
public class TodoSqlConstants {

    private static final String SAVE_TODO_SQL="INSERT INTO public.\"toDoWork\"(id, \"userId\", todo, date, priority, \"inProgress\", done) VALUES (:id, :userId, :todo, :date, :priority, :inProgress, :done);";
    private static final String GET_ALL_TODO_SQL="SELECT id, \"userId\", todo, date, priority, \"inProgress\", done FROM public.\"toDoWork\";";
    private static final String DELETE_BY_ID_TODO_SQL  = "DELETE FROM public.\"toDoWork\"\n" + "\tWHERE id=?;";
    private static final String UPDATE_BY_ID_TODO_SQL="UPDATE public.\"toDoWork\"\n" + "\tSET id=?, \"userId\"=?, todo=?\n" + "\tWHERE id=?;";
    public static String getSaveTodoSql() {
        return SAVE_TODO_SQL;
    }
    public static String getGetAllTodoSql() {
        return GET_ALL_TODO_SQL;
    }
    public static String getDeleteByIdTodoSql() {
        return DELETE_BY_ID_TODO_SQL;
    }
    public static String getUpdateByIdTodoSql() {
        return UPDATE_BY_ID_TODO_SQL;
    }
}
