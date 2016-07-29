package cn.sunxyz.generator.core;

import org.apache.log4j.Logger;

public class TypeLibraryTest {
	
	private Logger log = Logger.getLogger(TypeLibraryTest.class);
	
//	@Test
	public void test(){
		log.info(TypeLibrary.YEAR);
		log.info(TypeLibrary.getPath("YEAR"));
		log.info(TypeLibrary.getJavaType("YEAR"));
		log.info(TypeLibrary.getJavaTypePath("VARCHAR"));
	}

}
