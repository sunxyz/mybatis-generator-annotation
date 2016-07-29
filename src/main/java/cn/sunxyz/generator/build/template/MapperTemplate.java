package cn.sunxyz.generator.build.template;

import org.apache.log4j.Logger;

import cn.sunxyz.generator.build.Config;
import cn.sunxyz.generator.build.information.MapperInformation;

/**
 * 
 * 构建实体类
 * 
 * @author 神盾局
 * @date 2016年7月28日 下午5:27:40
 *
 */
public class MapperTemplate extends AbstractBuildTemplate<MapperInformation> {

	private Logger log = Logger.getLogger(MapperTemplate.class);

	@Override
	public String build(MapperInformation t) {
		StringBuilder clazz = init(t,"interface","Mapper");
		start(t, clazz);
		log.debug(clazz);
		return clazz.toString();
	}

	private void start(MapperInformation t, StringBuilder clazz) {
		buildInsert(t, clazz);
		buildDelete(t, clazz);
		buildUpdate(t, clazz);
		buildSelect(t, clazz);
		clazz.append("}");
	}

	private void buildInsert(MapperInformation t, StringBuilder clazz) {
		buildInsertAnnotation(t, clazz);
		String clazzName = t.getClazzName();
		clazz.append(reulst(1));
		clazz.append("int ");
		clazz.append("insert(");
		clazz.append(clazzName);
		clazz.append(" ");
		clazz.append(clazzName.substring(0, 1).toLowerCase());
		clazz.append(clazzName.substring(1));
		clazz.append(");\n\n");
	}

	private void buildDelete(MapperInformation t, StringBuilder clazz) {
		if(t.getKeyNameMap().isEmpty()){
			return;
		}
		buildDeleteAnnotation(t, clazz);
		clazz.append(reulst(1));
		clazz.append("int ");
		clazz.append("deleteByKey(");
		clazz.append(t.getKeyNameMap().get(Config.KEY_TYPE));
		clazz.append(" ");
		clazz.append(t.getKeyNameMap().get(Config.KEY_NAME));
		clazz.append(");\n\n");
	}

	private void buildUpdate(MapperInformation t, StringBuilder clazz) {
		if(t.getKeyNameMap().isEmpty()){
			return;
		}
		buildUpdateAnnotation(t, clazz);
		String clazzName = t.getClazzName();
		clazz.append(reulst(1));
		clazz.append("int ");
		clazz.append("update(");
		clazz.append(clazzName);
		clazz.append(" ");
		clazz.append(clazzName.substring(0, 1).toLowerCase());
		clazz.append(clazzName.substring(1));
		clazz.append(");\n\n");
	}

	private void buildSelect(MapperInformation t, StringBuilder clazz) {
		if(t.getKeyNameMap().isEmpty()){
			return;
		}
		String clazzName = t.getClazzName();
		buildSelectAnnotation(t, clazz);
		clazz.append(reulst(1));
		clazz.append(clazzName+" ");
		clazz.append("selectByKey(");
		clazz.append(t.getKeyNameMap().get(Config.KEY_TYPE));
		clazz.append(" ");
		clazz.append(t.getKeyNameMap().get(Config.KEY_NAME));
		clazz.append(");\n\n");
	}
	
	
	private void buildInsertAnnotation(MapperInformation t, StringBuilder clazz) {
		String clazzName = t.getClazzName();
		clazz.append(reulst(1));
		clazz.append("@InsertProvider(");
		clazz.append("type = ");
		clazz.append(clazzName);
		clazz.append("MapperProvider.class, ");
		clazz.append("method = \"insert\")\n");
	}
	
	private void buildDeleteAnnotation(MapperInformation t, StringBuilder clazz) {
		String clazzName = t.getClazzName();
		clazz.append(reulst(1));
		clazz.append("@DeleteProvider(");
		clazz.append("type = ");
		clazz.append(clazzName);
		clazz.append("MapperProvider.class, ");
		clazz.append("method = \"deleteByKey\")\n");
	}
	
	private void buildUpdateAnnotation(MapperInformation t, StringBuilder clazz) {
		String clazzName = t.getClazzName();
		clazz.append(reulst(1));
		clazz.append("@UpdateProvider(");
		clazz.append("type = ");
		clazz.append(clazzName);
		clazz.append("MapperProvider.class, ");
		clazz.append("method = \"update\")\n");
	}
	
	private void buildSelectAnnotation(MapperInformation t, StringBuilder clazz) {
		String clazzName = t.getClazzName();
		clazz.append(reulst(1));
		clazz.append("@SelectProvider(");
		clazz.append("type = ");
		clazz.append(clazzName);
		clazz.append("MapperProvider.class, ");
		clazz.append("method = \"selectByKey\")\n");
	}


}
