package cn.sunxyz.mapper.provider;

import cn.sunxyz.enitity.User;
import org.apache.ibatis.jdbc.SQL;

public class UserMapperProvider {

    public String insert(User user) {
        return new SQL() {
            {
                INSERT_INTO("user");
                VALUES("first_name", "#{firstName}");
                VALUES("id", "#{id}");
                VALUES("age", "#{age}");
            }
        }.toString();
    }

    public String deleteByKey(Integer id) {
        return new SQL() {
            {
                DELETE_FROM("user");
                WHERE("id = #{id}");
            }
        }.toString();
    }

    public String update(User user) {
        return new SQL() {
            {
                UPDATE("user");
                SET("first_name = #{firstName}");
                SET("age = #{age}");
                WHERE("id = #{id}");
            }
        }.toString();
    }

    public String selectByKey(Integer id) {
        return new SQL() {
            {
                SELECT(" first_name ,id ,age ");
                FROM("user");
                WHERE("id = #{id}");
            }
        }.toString();
    }

}