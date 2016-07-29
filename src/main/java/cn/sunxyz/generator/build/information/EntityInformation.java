package cn.sunxyz.generator.build.information;

import java.util.Map;
import java.util.Set;

import cn.sunxyz.generator.build.Config;

/**
 * 
 * 构建实体类信息
 * 
 * @author 神盾局
 * @date 2016年7月28日 下午2:33:14
 *
 */
public class EntityInformation extends AbstractClazzInformation {

	private Map<String, String> propertyMap;// 属性名称和对应类型

	public EntityInformation() {
		super();
	}

	public EntityInformation(String packageName, Set<String> importPath, String clazzName,
			Map<Config, String> keyNameMap, Map<String, String> propertyMap) {
		super(packageName, importPath, clazzName, keyNameMap);
		this.propertyMap = propertyMap;
	}

	public Map<String, String> getPropertyMap() {
		return propertyMap;
	}

	public void setPropertyMap(Map<String, String> propertyMap) {
		this.propertyMap = propertyMap;
	}

}
