package cn.sunxyz.generator.jdbc;

/**
 * 
* 数据库表信息
* @author 神盾局
* @date 2016年7月28日 上午9:42:23
*
 */
public class TableInformation {
	
	private String field;//字段名称
	
	private String type;//数据类型
	
	private String key;//主键

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
	
	

}
