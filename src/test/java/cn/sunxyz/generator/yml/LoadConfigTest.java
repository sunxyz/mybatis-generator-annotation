package cn.sunxyz.generator.yml;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

public class LoadConfigTest {
	
	private Logger log = Logger.getLogger(LoadConfigTest.class);
	
//	@Test
	public void test(){
		try {
			YmlConfig config = LoadConfig.loadConfig("d:/applications1.yml");
			log.debug(JSON.toJSONString(config));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
