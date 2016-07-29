package cn.sunxyz.mapper.provider;

import java.math.BigInteger;
import cn.sunxyz.enitity.Admin;
import org.apache.ibatis.jdbc.SQL;

public class AdminMapperProvider {

    public String insert(Admin admin) {
        return new SQL() {
            {
                INSERT_INTO("admin");
                VALUES("role", "#{role}");
                VALUES("name", "#{name}");
                VALUES("id", "#{id}");
            }
        }.toString();
    }

    public String deleteByKey(BigInteger id) {
        return new SQL() {
            {
                DELETE_FROM("admin");
                WHERE("id = #{id}");
            }
        }.toString();
    }

    public String update(Admin admin) {
        return new SQL() {
            {
                UPDATE("admin");
                SET("role = #{role}");
                SET("name = #{name}");
                WHERE("id = #{id}");
            }
        }.toString();
    }

    public String selectByKey(BigInteger id) {
        return new SQL() {
            {
                SELECT(" role ,name ,id ");
                FROM("admin");
                WHERE("id = #{id}");
            }
        }.toString();
    }

}