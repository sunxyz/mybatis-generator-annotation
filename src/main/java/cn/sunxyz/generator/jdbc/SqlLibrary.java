package cn.sunxyz.generator.jdbc;

public enum SqlLibrary {
	
	SHOW_TABLE("SHOW FULL COLUMNS FROM");
	
	 // 成员变量
    private String sql;

    // 构造方法
    private SqlLibrary(String sql) {
        this.sql = sql;
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.sql);
    }

}
