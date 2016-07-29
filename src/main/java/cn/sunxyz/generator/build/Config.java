package cn.sunxyz.generator.build;

public enum Config {
	
	KEY_NAME("keyName"),KEY_TYPE("keyType");
	
	private String name;
	
	private Config(String name){
		this.name = name;
	}

	@Override
    public String toString() {
        return String.valueOf(this.name);
    }

	
	

}
