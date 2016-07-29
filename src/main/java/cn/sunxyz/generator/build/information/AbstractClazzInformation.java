package cn.sunxyz.generator.build.information;

import java.util.Map;
import java.util.Set;

import cn.sunxyz.generator.build.Config;

/**
 * 
 * 待构建类信息
 * 
 * @author 神盾局
 * @date 2016年7月28日 下午3:58:19
 *
 */
public abstract class AbstractClazzInformation {

	protected String packageName;// 包名

	protected Set<String> importPath;// 导入路径

	protected String ClazzName;// 类名称

	protected Map<Config,String> keyNameMap;// 主鍵名称/主鍵類型
	
	public AbstractClazzInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AbstractClazzInformation(String packageName, Set<String> importPath, String clazzName,
			Map<Config, String> keyNameMap) {
		super();
		this.packageName = packageName;
		this.importPath = importPath;
		ClazzName = clazzName;
		this.keyNameMap = keyNameMap;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Set<String> getImportPath() {
		return importPath;
	}

	public void setImportPath(Set<String> importPath) {
		this.importPath = importPath;
	}

	public String getClazzName() {
		return ClazzName;
	}

	public void setClazzName(String clazzName) {
		ClazzName = clazzName;
	}

	/**
	 * 获取主键字段的名称和类型
	 * @return    设定文件
	 */
	public Map<Config, String> getKeyNameMap() {
		return keyNameMap;
	}

	public void setKeyNameMap(Map<Config, String> keyNameMap) {
		this.keyNameMap = keyNameMap;
	}

	
	
}
