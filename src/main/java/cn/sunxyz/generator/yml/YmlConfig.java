package cn.sunxyz.generator.yml;

import java.util.Map;

public class YmlConfig {

	private String url;

	private String password;

	private String userName;
	
	private Map<String,String> table2ClazzMap;
	
	private String entityPackage;
	
	private String mapperPackage;
	
	private String filePath;//生成文件的保存路径

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Map<String, String> getTable2ClazzMap() {
		return table2ClazzMap;
	}

	public void setTable2ClazzMap(Map<String, String> table2ClazzMap) {
		this.table2ClazzMap = table2ClazzMap;
	}

	public String getEntityPackage() {
		return entityPackage;
	}

	public void setEntityPackage(String entityPackage) {
		this.entityPackage = entityPackage;
	}

	public String getMapperPackage() {
		return mapperPackage;
	}

	public void setMapperPackage(String mapperPackage) {
		this.mapperPackage = mapperPackage;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	

	
}
