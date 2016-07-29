package cn.sunxyz.generator.build;

import cn.sunxyz.generator.build.information.EntityInformation;
import cn.sunxyz.generator.build.information.MapperInformation;
import cn.sunxyz.generator.build.information.ProviderInformation;
import cn.sunxyz.generator.build.template.EntityTemplate;
import cn.sunxyz.generator.build.template.ITemplate;
import cn.sunxyz.generator.build.template.MapperTemplate;
import cn.sunxyz.generator.build.template.ProviderTemplate;

public class BuildClazz {
	
	private static ITemplate<EntityInformation> entityTemplate = new EntityTemplate();
	
	private static ITemplate<MapperInformation> mapperTemplate = new MapperTemplate();
	
	private static ITemplate<ProviderInformation> providerTemplate = new ProviderTemplate();
	
	public static String todo(EntityInformation buildInformation){
		return entityTemplate.build(buildInformation);
	}
	
	public static String todo(MapperInformation buildInformation){
		return mapperTemplate.build(buildInformation);
	}
	
	public static String todo(ProviderInformation buildInformation){
		return providerTemplate.build(buildInformation);
	}

}
