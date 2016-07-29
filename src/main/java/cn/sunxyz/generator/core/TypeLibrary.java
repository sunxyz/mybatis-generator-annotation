package cn.sunxyz.generator.core;

/**
 * 
* 数据库字段类型和java类型映射
* @author 神盾局
* @date 2016年7月28日 下午12:02:24
*
 */
public enum TypeLibrary {
	
	VARCHAR("VARCHAR","java.lang.String"),
	CHAR("CHAR","java.lang.String"),
	TEXT("TEXT","java.lang.String"),
	BLOB("BLOB","java.lang.byte[]"),
	INTEGER("INTEGER","java.lang.Long"),
	INT("INT","java.lang.Integer"),
	TINYINT("TINYINT","java.lang.Integer"),
	SMALLINT("SMALLINT","java.lang.Integer"),
	MEDIUMINT("MEDIUMINT","java.lang.Integer"),
	BIT("BIT","java.lang.Boolean"),
	BIGINT("BIGINT","java.math.BigInteger"),
	FLOAT("FLOAT","java.lang.Float"),
	DOUBLE("DOUBLE","java.lang.Double"),
	DECIMAL("DECIMAL","java.math.BigDecimal"),
	ID("ID","java.lang.Long"),
	DATE("DATE","java.sql.Date"),
	TIME("TIME","java.sql.Time"),
	DATETIME("DATETIME","java.sql.Timestamp"),
	TIMESTAMP("TIMESTAMP","java.sql.Timestamp"),
	YEAR("YEAR","java.sql.Date");
	
	private String type;
	
	private String path;
	
	private TypeLibrary(String type, String path){
		this.type=type;
		this.path=path;
	}

	@Override
	public String toString() {
		return this.path;
	}

	/**
	 * 得到路径
	* @param type
	* @return    设定文件
	 */
	public static String getPath(String type){
		 for (TypeLibrary c : TypeLibrary.values()) {
             if (c.getType().equals(type)) {
                 return c.getPath();
             }
         }
         return null;
	}
	
	/**
	 * 
	* 获取java类型
	* @param type
	* @return    设定文件
	 */
	public static String getJavaType(String type){
		 for (TypeLibrary c : TypeLibrary.values()) {
            if (c.getType().equals(type)) {
            	String [] strs = c.getPath().split("\\.");
            	String javaType = strs[strs.length-1];
                return javaType;
            }
        }
        return null;
	}
	
	/**
	 * 
	* 获取java类型全名称(java.lang返回null)
	* @param type
	* @return    设定文件
	 */
	public static String getJavaTypePath(String type){
		 for (TypeLibrary c : TypeLibrary.values()) {
            if (c.getType().equals(type)) {
            	/**TODO 判断是否是java.lang包中的对象**/
            	String javaTypePath = c.path;
            	
            	if(javaTypePath.indexOf("java.lang")<0){
            		return javaTypePath;
            	}
            }
        }
        return null;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	

}
