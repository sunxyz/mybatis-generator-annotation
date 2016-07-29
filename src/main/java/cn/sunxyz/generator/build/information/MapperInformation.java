package cn.sunxyz.generator.build.information;

import java.util.Map;
import java.util.Set;

import cn.sunxyz.generator.build.Config;

/**
 * 
* mapper信息
* @author 神盾局
* @date 2016年7月28日 下午5:31:53
*
 */
public class MapperInformation extends AbstractClazzInformation {

	public MapperInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MapperInformation(String packageName, Set<String> importPath, String clazzName,
			Map<Config, String> keyNameMap) {
		super(packageName, importPath, clazzName, keyNameMap);
		// TODO Auto-generated constructor stub
	}

	
	
}
