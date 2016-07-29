package cn.sunxyz.generator.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import cn.sunxyz.generator.jdbc.TableInformation;
import cn.sunxyz.generator.mapper.provider.TableProvider;

public interface TableMapper {
	
	@SelectProvider(type=TableProvider.class, method="selectByTableName")
	@Results(value={
			@Result(column="field", property="field"),
			@Result(column="type",property="type"),
			@Result(column="key",property="key")
	})
	List<TableInformation> selectByTableName(String name);


}
