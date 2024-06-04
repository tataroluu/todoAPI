package com.bilgeadam.todoAPI.constants;


public class UsersSqlConstants {

    //SELECT id, username, password, \"RoleId\"\n" + "\tFROM public.users;
    private static final String GET_ALL_USERS_SQL="SELECT id, username, password, \"RoleId\" FROM public.users;";
    public static String getGetAllUserSql() {
        return GET_ALL_USERS_SQL;
    }
}
