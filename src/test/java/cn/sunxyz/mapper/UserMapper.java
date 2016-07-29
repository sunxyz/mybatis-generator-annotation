package cn.sunxyz.mapper;

import cn.sunxyz.mapper.provider.UserMapperProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.annotations.SelectProvider;
import cn.sunxyz.enitity.User;

public interface UserMapper {

    @InsertProvider(type = UserMapperProvider.class, method = "insert")
    int insert(User user);

    @DeleteProvider(type = UserMapperProvider.class, method = "deleteByKey")
    int deleteByKey(Integer id);

    @UpdateProvider(type = UserMapperProvider.class, method = "update")
    int update(User user);

    @SelectProvider(type = UserMapperProvider.class, method = "selectByKey")
    User selectByKey(Integer id);

}