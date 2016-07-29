package cn.sunxyz.generator.core;

/**
 * 
* 属性（字段）工具
* @author 神盾局
* @date 2016年7月28日 下午1:44:34
*
 */
public class PropertyTool {
	
	/**
	 * 
	* 根据数据库查询的字段使用两种匹配规则将下划线等转化为驼峰命名法
	* @param name
	* @return    设定文件
	 */
	public static String getPropertyName(String column){
		String propertyName = null;
		String [] str = {"-","_"};
		for (String string : str) {
			propertyName = propertyNameSplit(column,string);
			if(propertyName!=null){
				return propertyName;
			}
		}
		
		propertyName= column.substring(0, 1).toLowerCase()+column.substring(1);
		return propertyName;
		
	}
	
	/**
	 * 
	* 根据数据库查询的类型结果返回java数据类型
	* @param type
	* @return    设定文件
	 */
	public static String getPropertyType(String type){
		type = columnType(type);
		return TypeLibrary.getJavaType(type);
	}
	
	/**
	 * 
	* 根据数据库查询的类型结果返回java数据类型全类名（java.lang包下为null）
	* @param type
	* @return    设定文件
	 */
	public static String getPropertyTypePath(String type){
		type = columnType(type);
		return TypeLibrary.getJavaTypePath(type);
	}
	
	/**
	 * 
	* 根据数据库查询的结果返回数据库字段类型
	* @param type
	* @return    设定文件
	 */
	private static String columnType(String type){
		type = type.toUpperCase();
		int index = type.indexOf("(");
		if(index>0){
			return type.substring(0, index).trim();
		}
		return type;
	}
	
	private static String propertyNameSplit(String column, String split){
		int num = column.indexOf(split);
		if(num>0){
			StringBuilder strb = new StringBuilder();
			String[] propertyFiled = column.split(split);
			strb.append(propertyFiled[0].substring(0, 1).toLowerCase());
			strb.append(propertyFiled[0].substring(1).toLowerCase());
			for(int i=1;i<propertyFiled.length;i++){
				strb.append(propertyFiled[i].substring(0, 1).toUpperCase());
				strb.append(propertyFiled[i].substring(1).toLowerCase());
			}
			return strb.toString();
		}
		return null;
	}

}
