package cn.sunxyz.generator.mapper.provider;

import cn.sunxyz.generator.jdbc.SqlLibrary;

public class TableProvider {
	
	public String selectByTableName(String name){
		String sql = SqlLibrary.SHOW_TABLE.toString();
		sql += " " + name;
		return sql;
	}

}
