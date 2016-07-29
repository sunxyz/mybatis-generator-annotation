package cn.sunxyz.generator.build.template;

import java.util.Map;

import org.apache.log4j.Logger;

import cn.sunxyz.generator.build.Config;
import cn.sunxyz.generator.build.information.ProviderInformation;

/**
 * 
 * 构建实体类
 * 
 * @author 神盾局
 * @date 2016年7月28日 下午5:27:40
 *
 */
public class ProviderTemplate extends AbstractBuildTemplate<ProviderInformation> {

	private Logger log = Logger.getLogger(ProviderTemplate.class);

	@Override
	public String build(ProviderInformation t) {
		StringBuilder clazz = init(t, "class", "MapperProvider");
		start(t, clazz);
		log.debug(clazz);
		return clazz.toString();
	}

	private void start(ProviderInformation t, StringBuilder clazz) {
		buildInsert(t, clazz);
		buildDelete(t, clazz);
		buildUpdate(t, clazz);
		buildSelect(t, clazz);
		clazz.append("}");
	}

	private void buildInsert(ProviderInformation t, StringBuilder clazz) {
//		//有主键则默认去除
//		if (!t.getKeyNameMap().isEmpty()) {
//			String KeyFiledName = t.getKeyNameMap().get(Config.KEY_NAME);
//			t.getProperty2ColumnMap().remove(KeyFiledName);
//		}
		String clazzName = t.getClazzName();
		clazz.append(reulst(1));
		clazz.append("public String ");
		clazz.append("insert(");
		clazz.append(clazzName);
		clazz.append(" ");
		clazz.append(clazzName.substring(0, 1).toLowerCase());
		clazz.append(clazzName.substring(1));
		// 构建方法体
		buildInsertMethod(t, clazz);
	}

	private void buildDelete(ProviderInformation t, StringBuilder clazz) {
		if (t.getKeyNameMap().isEmpty()) {
			return;
		}
		clazz.append(reulst(1));
		clazz.append("public String ");
		clazz.append("deleteByKey(");
		clazz.append(t.getKeyNameMap().get(Config.KEY_TYPE));
		clazz.append(" ");
		clazz.append(t.getKeyNameMap().get(Config.KEY_NAME));
		// 构建方法体
		buildDeleteMethod(t, clazz);

	}

	private void buildUpdate(ProviderInformation t, StringBuilder clazz) {
		if (t.getKeyNameMap().isEmpty()) {
			return;
		}
		String clazzName = t.getClazzName();
		clazz.append(reulst(1));
		clazz.append("public String ");
		clazz.append("update(");
		clazz.append(clazzName);
		clazz.append(" ");
		clazz.append(clazzName.substring(0, 1).toLowerCase());
		clazz.append(clazzName.substring(1));
		// 构建方法体
		buildUpdateMethod(t, clazz);

	}

	private void buildSelect(ProviderInformation t, StringBuilder clazz) {
		if (t.getKeyNameMap().isEmpty()) {
			return;
		}
		clazz.append(reulst(1));
		clazz.append("public String ");
		clazz.append("selectByKey(");
		clazz.append(t.getKeyNameMap().get(Config.KEY_TYPE));
		clazz.append(" ");
		clazz.append(t.getKeyNameMap().get(Config.KEY_NAME));
		// 构建方法体
		buildSelectMethod(t, clazz);
	}

	/************************************** 创建方法的内部内容 ****************************************/
	/**
	 * 
	 * 创建一个insert方法的内部内容
	 * 
	 * @param t
	 * @param clazz
	 *            设定文件
	 */
	private void buildInsertMethod(ProviderInformation t, StringBuilder clazz) {
		buildCommonTop(clazz);
		// 拼写内部内容
		clazz.append("INSERT_INTO(\"");
		clazz.append(t.getTableName());
		clazz.append("\");\n");

		Map<String, String> p2cs = t.getProperty2ColumnMap();
		for (Map.Entry<String, String> p2c : p2cs.entrySet()) {
			clazz.append(reulst(4));
			clazz.append("VALUES(");
			clazz.append("\"");
			clazz.append(p2c.getValue());
			clazz.append("\", ");
			clazz.append("\"#{");
			clazz.append(p2c.getKey());
			clazz.append("}\"");
			clazz.append(");\n");
		}
		// 相同
		buildCommonBottom(clazz);

	}

	/**
	 * 
	 * 创建一个insert方法的内部内容
	 * 
	 * @param t
	 * @param clazz
	 *            设定文件
	 */
	private void buildDeleteMethod(ProviderInformation t, StringBuilder clazz) {
		buildCommonTop(clazz);
		// 拼写内部内容
		clazz.append("DELETE_FROM(\"");
		clazz.append(t.getTableName());
		clazz.append("\");\n");
		clazz.append(reulst(4));
		clazz.append("WHERE(\"");
		clazz.append(t.getProperty2ColumnMap().get(t.getKeyNameMap().get(Config.KEY_NAME)));
		clazz.append(" = #{");
		clazz.append(t.getKeyNameMap().get(Config.KEY_NAME));
		clazz.append("}\");\n");
		// 相同
		buildCommonBottom(clazz);

	}

	/**
	 * 
	 * 创建一个insert方法的内部内容
	 * 
	 * @param t
	 * @param clazz
	 *            设定文件
	 */
	private void buildUpdateMethod(ProviderInformation t, StringBuilder clazz) {
		String KeyFiledName = t.getKeyNameMap().get(Config.KEY_NAME);
		String keyColumName = t.getProperty2ColumnMap().get(KeyFiledName);
		buildCommonTop(clazz);
		// 拼写内部内容
		clazz.append("UPDATE(\"");
		clazz.append(t.getTableName());
		clazz.append("\");\n");
		Map<String, String> p2cs = t.getProperty2ColumnMap();
		for (Map.Entry<String, String> p2c : p2cs.entrySet()) {
			if(!p2c.getKey().equals(KeyFiledName)){
				clazz.append(reulst(4));
				clazz.append("SET(");
				clazz.append("\"");
				clazz.append(p2c.getValue());
				clazz.append(" = ");
				clazz.append("#{");
				clazz.append(p2c.getKey());
				clazz.append("}\"");
				clazz.append(");\n");
			}
			
		}
		clazz.append(reulst(4));
		clazz.append("WHERE(\"");
		clazz.append(keyColumName);
		clazz.append(" = #{");
		clazz.append(KeyFiledName);
		clazz.append("}\"");
		clazz.append(");\n");
		// 相同
		buildCommonBottom(clazz);

	}

	/**
	 * 
	 * 创建一个insert方法的内部内容
	 * 
	 * @param t
	 * @param clazz
	 *            设定文件
	 */
	private void buildSelectMethod(ProviderInformation t, StringBuilder clazz) {
		String KeyFiledName = t.getKeyNameMap().get(Config.KEY_NAME);
		String keyColumName = t.getProperty2ColumnMap().get(KeyFiledName);
		buildCommonTop(clazz);
		// 拼写内部内容
		
		clazz.append("SELECT(\" ");
		Map<String, String> p2cs = t.getProperty2ColumnMap();
		for(String property:p2cs.keySet()){
			String column = p2cs.get(property);
			clazz.append(column);
			clazz.append(" ,");
		}
		clazz = clazz.delete(clazz.length()-1, clazz.length());;
		clazz.append("\");\n");
		
		clazz.append(reulst(4));
		clazz.append("FROM(\"");
		clazz.append(t.getTableName());
		clazz.append("\");\n");
		
		clazz.append(reulst(4));
		clazz.append("WHERE(\"");
		clazz.append(keyColumName);
		clazz.append(" = #{");
		clazz.append(KeyFiledName);
		clazz.append("}\"");
		clazz.append(");\n");
		
		// 相同
		buildCommonBottom(clazz);

	}

	/**
	 * 
	 * 创建一个公共的头
	 * 
	 * @param clazz
	 *            设定文件
	 */
	private void buildCommonTop(StringBuilder clazz) {
		clazz.append(") {\n");
		clazz.append(reulst(2));
		clazz.append("return new SQL() {\n");
		clazz.append(reulst(3));
		clazz.append("{\n");
		clazz.append(reulst(4));
	}

	/**
	 * 
	 * 创建一个公共的底部
	 * 
	 * @param clazz
	 *            设定文件
	 */
	private void buildCommonBottom(StringBuilder clazz) {
		clazz.append(reulst(3));
		clazz.append("}\n");
		clazz.append(reulst(2));
		clazz.append("}.toString();\n");
		clazz.append(reulst(1));
		clazz.append("}\n\n");
	}

}
