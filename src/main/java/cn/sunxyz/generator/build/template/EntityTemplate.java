package cn.sunxyz.generator.build.template;

import java.util.Map;

import org.apache.log4j.Logger;

import cn.sunxyz.generator.build.information.EntityInformation;
/**
 * 
* 构建实体类
* @author 神盾局
* @date 2016年7月28日 下午5:27:40
*
 */
public class EntityTemplate extends AbstractBuildTemplate<EntityInformation> {
	
	private Logger log = Logger.getLogger(EntityTemplate.class);

	@Override
	public String build(EntityInformation t) {
		StringBuilder clazz = init(t);
		start(t, clazz);
		log.debug(clazz);
		return clazz.toString();
	}
	
	private void start(EntityInformation t , StringBuilder clazz){
		StringBuilder strGetSet = new StringBuilder();
		Map<String, String> propertyMap = t.getPropertyMap();
		for (Map.Entry<String, String> entry : propertyMap.entrySet()) {
			String filedType = entry.getValue();
			String filedName = entry.getKey();
			buildProperty(filedType,filedName,clazz);
			buildGetSet(filedType, filedName, strGetSet);
//			log.debug("===>>"+filedType+" "+filedName);
		}
		clazz.append(strGetSet);
		clazz.append("}");
	}
	
	/**
	 * 
	* 构建属性
	* @param filedType
	* @param filedName
	* @param clazz    设定文件
	 */
	private void buildProperty(String filedType, String filedName, StringBuilder clazz){
		clazz.append(reulst(1));
		clazz.append("private ");
		clazz.append(filedType);
		clazz.append(" ");
		clazz.append(filedName);
		clazz.append(";\n\n");
	}
	
	/**
	 * 
	* 构建get/set 
	* @param filedType
	* @param filedName
	* @param clazz    设定文件
	 */
	private void buildGetSet(String filedType, String filedName, StringBuilder clazz){
		//生成get方法
		clazz.append(reulst(1));
		clazz.append("public ");
		clazz.append(filedType);
		clazz.append(" get");
		clazz.append(filedName.substring(0,1).toUpperCase());
		clazz.append(filedName.substring(1));
		clazz.append("( ) ");
		clazz.append("{\n");
		clazz.append(reulst(2));
		clazz.append("return this." + filedName + ";\n");
		clazz.append(reulst(1));
		clazz.append("}\n\n");
		//生成set方法
		clazz.append(reulst(1));
		clazz.append("public ");
		clazz.append("void");
		clazz.append(" set");
		clazz.append(filedName.substring(0,1).toUpperCase());
		clazz.append(filedName.substring(1));
		clazz.append("(");
		clazz.append(filedType);
		clazz.append(" ");
		clazz.append(filedName);
		clazz.append(") {\n");
		clazz.append(reulst(2));
		clazz.append("this." + filedName + " = "+ filedName);
		clazz.append(";\n");
		clazz.append(reulst(1));
		clazz.append("}\n\n");
		
		
	}
	

}
