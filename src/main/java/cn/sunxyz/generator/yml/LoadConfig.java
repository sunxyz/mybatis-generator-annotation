package cn.sunxyz.generator.yml;

import java.io.FileNotFoundException;

import org.yaml.snakeyaml.Yaml;

public class LoadConfig {
	
//	@SuppressWarnings("unchecked")
//	public static Map<String, Object> load(String path){
//		InputStreamReader input =  FileUtil.in(path);
//		Yaml yaml = new Yaml();
//		Map<String, Object> object = (Map<String, Object>) yaml.load(input);
//		for(Map.Entry<String, Object> entry:object.entrySet()){
//			System.out.println(entry.getKey()+":=====>"+entry.getValue()+"\n");
//		}
//		return object;
//	}
	
	/**
	 * 
	* 自动装置配置
	* @param path
	* @return
	* @throws FileNotFoundException    设定文件
	 */
	public static YmlConfig loadConfig(String path) throws FileNotFoundException{
		  Yaml yaml = new Yaml();
//		  YmlConfig config = yaml.loadAs(new FileInputStream(new File(path)), YmlConfig.class);
		  YmlConfig config = yaml.loadAs(LoadConfig.class.getClassLoader().getResourceAsStream(path), YmlConfig.class);
		  return config;
	}
	
//	@SuppressWarnings("rawtypes")
//	public static void load2(String path){
//		 try {
//	            File file = new File(path);
//	            YamlStream stream = Yaml.loadStream(file);
//	            for (Iterator iter = stream.iterator(); iter.hasNext();) {
//	                HashMap hashMap = (HashMap) iter.next();
//	                // System.out.println(hashMap);
//	                for (Iterator iter2 = hashMap.entrySet().iterator(); iter2.hasNext();) {
//	                    Map.Entry entry = (Map.Entry) iter2.next();
//	                    Object key = entry.getKey();
//	                    Object value = entry.getValue();
//	                    System.out.println("key's type = " + key.getClass());
//	                    System.out.println("value's type = " + value.getClass());
//	                    System.out.println("key = " + key + ", value = " + value);
//	                    System.out.println("=================================");
//	                }
//	            }
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	}

}
