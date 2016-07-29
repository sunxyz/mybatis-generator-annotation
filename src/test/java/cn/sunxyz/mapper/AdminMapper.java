package cn.sunxyz.mapper;

import java.math.BigInteger;
import cn.sunxyz.enitity.Admin;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.annotations.SelectProvider;
import cn.sunxyz.mapper.provider.AdminMapperProvider;

public interface AdminMapper {

    @InsertProvider(type = AdminMapperProvider.class, method = "insert")
    int insert(Admin admin);

    @DeleteProvider(type = AdminMapperProvider.class, method = "deleteByKey")
    int deleteByKey(BigInteger id);

    @UpdateProvider(type = AdminMapperProvider.class, method = "update")
    int update(Admin admin);

    @SelectProvider(type = AdminMapperProvider.class, method = "selectByKey")
    Admin selectByKey(BigInteger id);

}