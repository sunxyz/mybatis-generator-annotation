package cn.sunxyz.generator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import cn.sunxyz.common.util.FileUtil;
import cn.sunxyz.generator.build.BuildClazz;
import cn.sunxyz.generator.build.Config;
import cn.sunxyz.generator.build.information.EntityInformation;
import cn.sunxyz.generator.build.information.MapperInformation;
import cn.sunxyz.generator.build.information.ProviderInformation;
import cn.sunxyz.generator.core.PropertyTool;
import cn.sunxyz.generator.jdbc.TableInformation;
import cn.sunxyz.generator.mapper.TableMapper;
import cn.sunxyz.generator.yml.LoadConfig;
import cn.sunxyz.generator.yml.YmlConfig;

/**
 * Hello world!
 *
 */
public class App {
	
	//配置文档
	private static YmlConfig config = null;
	
	@SuppressWarnings("unused")
	private String filePath = "d:/generator/src/";//System.getProperty("user.dir") + "/src/";//"d:/src/"; ;
	
	@SuppressWarnings("unused")
	private static String configPath = "d:/generator/config/";//System.getProperty("user.dir")+"/config/";

	private Logger log = Logger.getLogger(App.class);

	private SqlSession session;

	public void before() {
		// mybatis的配置文件
		String resource = "conf.xml";
		// 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
		InputStream is = App.class.getClassLoader().getResourceAsStream(resource);
		//偷梁换柱
		is = replace(is);
		// 构建sqlSession的工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		session = sessionFactory.openSession();
	}

	public void clazzs(String tableName, String ClazzName, String entityPackage, String mapperPackage) {
		TableMapper tableMapper = session.getMapper(TableMapper.class);
		List<TableInformation> ts = tableMapper.selectByTableName(tableName);
		log.info(JSON.toJSONString(ts));
		// 屬性
		// 主键
		Map<Config, String> key = new HashMap<>();
		// 导入路径
		Set<String> importPath = new HashSet<>();
		// 属性名称和对应类型
		Map<String, String> propertyMap = new HashMap<>();
		// 实体类字段与数据库字段映射
		Map<String, String> property2ColumnMap = new HashMap<>();
		for (TableInformation tableInformation : ts) {
			String columnName = tableInformation.getField();
			String columnType = tableInformation.getType();
			// 属性字段名称
			String fieldName = PropertyTool.getPropertyName(columnName);
			// 属性字段类型
			String fieldType = PropertyTool.getPropertyType(columnType);
			// 属性字段类型全类名（java.lang包下默认为null)
			String typePath = PropertyTool.getPropertyTypePath(columnType);
			// 主键
			if (!tableInformation.getKey().equals("")) {
				key.put(Config.KEY_NAME, fieldName);
				key.put(Config.KEY_TYPE, fieldType);
			}
			if (typePath != null) {
				importPath.add(typePath);
			}
			propertyMap.put(fieldName, fieldType);
			property2ColumnMap.put(fieldName, columnName);
		}
		// 实体类
		EntityInformation be = new EntityInformation(entityPackage, importPath, ClazzName, key, propertyMap);
		testBuildEntity(be);
		Set<String> importPathBackup = new HashSet<>();
		importPathBackup.addAll(importPath);
		// mapper
		importPath.add(entityPackage + "." + ClazzName);
		importPath.add(mapperPackage + ".provider" + "." + ClazzName + "MapperProvider");
		importPath.add("org.apache.ibatis.annotations.InsertProvider");
		importPath.add("org.apache.ibatis.annotations.DeleteProvider");
		importPath.add("org.apache.ibatis.annotations.UpdateProvider");
		importPath.add("org.apache.ibatis.annotations.SelectProvider");
		MapperInformation me = new MapperInformation(mapperPackage, importPath, ClazzName, key);
		testBuildMapper(me);
		// Provider
		importPath = importPathBackup;
		importPath.add(entityPackage + "." + ClazzName);
		importPath.add("org.apache.ibatis.jdbc.SQL");
		ProviderInformation pi = new ProviderInformation(mapperPackage + ".provider", importPath, ClazzName, key,
				propertyMap, property2ColumnMap, tableName);
		testBuildProvider(pi);
		log.debug(JSON.toJSONString(be));
	}
	/************************保存构建后的对象*********************************/

	private void testBuildEntity(EntityInformation t) {
		String str = BuildClazz.todo(t);
		String path = t.getPackageName().replace(".", "/");
		log.debug("=================>>" + path);
		FileUtil.out( config.getFilePath() + path, t.getClazzName(), str);
		log.debug(str);
	}

	private void testBuildMapper(MapperInformation t) {
		String str = BuildClazz.todo(t);
		String path = t.getPackageName().replace(".", "/");
		log.debug("=================>>" + path);
		FileUtil.out(config.getFilePath() + path, t.getClazzName() + "Mapper", str);
		log.debug(str);
	}

	private void testBuildProvider(ProviderInformation t) {
		String str = BuildClazz.todo(t);
		String path = t.getPackageName().replace(".", "/");
		log.debug("=================>>" + path);
		FileUtil.out(config.getFilePath() + path, t.getClazzName() + "MapperProvider", str);
		log.debug(str);
	}

	public void after() {
		session.close();
	}

	/**
	 * 
	* 偷梁换柱
	* @param is
	* @return    设定文件
	 */
	private InputStream replace(InputStream is ) {
		// 偷梁换柱
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i;
		try {
			while ((i = is.read()) != -1) {
				baos.write(i);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = baos.toString();
		str = str.replace("param1", config.getUrl());
		str = str.replace("param2", config.getUserName());
		str = str.replace("param3", config.getPassword());
		System.out.println(str);
		is = new ByteArrayInputStream(str.getBytes());
		return is;
	}

	public static void main(String[] args) {	
		try {
			config = LoadConfig.loadConfig("config.yml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String entityPackage = config.getEntityPackage();
		String mapperPackage = config.getMapperPackage();
		App app = new App();
		app.before();
		
		Map<String,String> table2Clazz = config.getTable2ClazzMap();
		for(Map.Entry<String, String> entry:table2Clazz.entrySet()){
			String tableName = entry.getKey();
			String ClazzName = entry.getValue();
			app.clazzs(tableName, ClazzName, entityPackage, mapperPackage);
		}
		app.after();
	}
}
