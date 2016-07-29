package cn.sunxyz.generator.build.template;

import java.util.Set;

import cn.sunxyz.generator.build.information.AbstractClazzInformation;

public abstract class AbstractBuildTemplate<T extends AbstractClazzInformation> implements ITemplate<T> {

	/**
	 * 
	 * 初始化类模板
	 * 
	 * @param t
	 * @return 设定文件
	 */
	public StringBuilder init(T t) {
		return init(t, "class","");
	}

	/**
	 * 
	* 构建初始模板
	* @param t 模板信息
	* @param clazzType 类或接口
	* @param suffix 类名称后缀
	* @return    设定文件
	 */
	public StringBuilder init(T t, String clazzType, String suffix) {
		StringBuilder clazz = new StringBuilder();
		// 构建包名
		clazz.append("package ");
		clazz.append(t.getPackageName());
		clazz.append(";\n\n");
		// 构建导入路径
		Set<String> importPaths = t.getImportPath();
		for (String importPath : importPaths) {
			clazz.append("import ");
			clazz.append(importPath);
			clazz.append(";\n");
		}
		if (!importPaths.isEmpty()) {
			clazz.append("\n");
		}
		// 构建包名
		clazz.append("public " + clazzType + " ");
		clazz.append(t.getClazzName()+suffix);
		clazz.append(" {\n\n");

		return clazz;
	}
	
	/**
	 * 
	* 返回空格 控制缩进
	* @param num
	* @return    设定文件
	 */
	protected String reulst(int num){
		String str = "";
		for(int i=0;i<num;i++){
			str+="    ";
		}
		return str;
	}

}
