package cn.sunxyz.generator.mapper;

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
import org.junit.After;
import org.junit.Before;

import com.alibaba.fastjson.JSON;

import cn.sunxyz.generator.build.BuildClazz;
import cn.sunxyz.generator.build.Config;
import cn.sunxyz.generator.build.information.EntityInformation;
import cn.sunxyz.generator.build.information.MapperInformation;
import cn.sunxyz.generator.build.information.ProviderInformation;
import cn.sunxyz.generator.core.PropertyTool;
import cn.sunxyz.generator.jdbc.TableInformation;

public class MapperTest {

	private Logger log = Logger.getLogger(MapperTest.class);

	private SqlSession session;

	@Before
	public void before() {
		// mybatis的配置文件
		String resource = "conf.xml";
		// 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
		InputStream is = MapperTest.class.getClassLoader().getResourceAsStream(resource);
		// 构建sqlSession的工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		session = sessionFactory.openSession();
	}

//	@Test
	public void test() {
		TableMapper tableMapper = session.getMapper(TableMapper.class);
		List<TableInformation> ts = tableMapper.selectByTableName("user");
		log.info(JSON.toJSONString(ts));
	}

//	@Test
	@SuppressWarnings("unused")
	public void clazz() {
		String tableName = "user";
		TableMapper tableMapper = session.getMapper(TableMapper.class);
		List<TableInformation> ts = tableMapper.selectByTableName("user");
		log.info(JSON.toJSONString(ts));
		for (TableInformation tableInformation : ts) {
			String key = tableInformation.getKey();// 主键
			String column = tableInformation.getField();
			// 属性字段名称
			String fieldName = PropertyTool.getPropertyName(column);
			// 属性字段类型
			String type = PropertyTool.getPropertyType(tableInformation.getType());
			log.info(type + " " + fieldName + " " + key);
		}
	}

	// 构建实体类
//	@Test
	public void clazzs() {

		String tableName = "user";
		TableMapper tableMapper = session.getMapper(TableMapper.class);
		List<TableInformation> ts = tableMapper.selectByTableName("user");
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
		EntityInformation be = new EntityInformation("cn.sunxyz.entity", importPath, "User", key, propertyMap);
		testBuildEntity(be);
		// mapper
		importPath.add("cn.sunxyz.entity");
		importPath.add("org.apache.ibatis.annotations.InsertProvider");
		importPath.add("org.apache.ibatis.annotations.DeleteProvider");
		importPath.add("org.apache.ibatis.annotations.UpdateProvider");
		importPath.add("org.apache.ibatis.annotations.SelectProvider");
		MapperInformation me = new MapperInformation("cn.sunxyz.mapper", importPath, "User", key);
		testBuildMapper(me);
		//Provider
		ProviderInformation pi = new ProviderInformation("cn.sunxyz.mapper.provider", importPath, "User", key,
				propertyMap, property2ColumnMap, tableName);
		testBuildProvider(pi);
		log.info(JSON.toJSONString(be));
	}

	private void testBuildEntity(EntityInformation t) {
		log.info(BuildClazz.todo(t));
	}

	private void testBuildMapper(MapperInformation t) {
		log.info(BuildClazz.todo(t));
	}

	private void testBuildProvider(ProviderInformation t) {
		log.info(BuildClazz.todo(t));
	}

	@After
	public void after() {
		session.close();
	}
}
