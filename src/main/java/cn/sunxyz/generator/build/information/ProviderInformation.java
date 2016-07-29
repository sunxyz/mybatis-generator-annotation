package cn.sunxyz.generator.build.information;

import java.util.Map;
import java.util.Set;

import cn.sunxyz.generator.build.Config;

/**
 * 
 * 动态生成sql信息
 * 
 * @author 神盾局
 * @date 2016年7月29日 上午9:03:16
 *
 */
public class ProviderInformation extends EntityInformation {
	
	private Map<String, String> property2ColumnMap;//实体类字段与数据库字段映射

	private String tableName;//表名
	
	public ProviderInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProviderInformation(String packageName, Set<String> importPath, String clazzName,
			Map<Config, String> keyNameMap, Map<String, String> propertyMap) {
		super(packageName, importPath, clazzName, keyNameMap, propertyMap);
		// TODO Auto-generated constructor stub
	}

	public ProviderInformation(String packageName, Set<String> importPath, String clazzName,
			Map<Config, String> keyNameMap, Map<String, String> propertyMap, Map<String, String> property2ColumnMap) {
		super(packageName, importPath, clazzName, keyNameMap, propertyMap);
		this.property2ColumnMap = property2ColumnMap;
	}
	
	public ProviderInformation(String packageName, Set<String> importPath, String clazzName,
			Map<Config, String> keyNameMap, Map<String, String> propertyMap, Map<String, String> property2ColumnMap,String tableName) {
		this(packageName, importPath, clazzName, keyNameMap, propertyMap,property2ColumnMap);
		this.tableName = tableName;
	}

	public Map<String, String> getProperty2ColumnMap() {
		return property2ColumnMap;
	}

	public void setProperty2ColumnMap(Map<String, String> property2ColumnMap) {
		this.property2ColumnMap = property2ColumnMap;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	
	
	

}
