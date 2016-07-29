package cn.sunxyz.generator.build.template;

import cn.sunxyz.generator.build.information.AbstractClazzInformation;

/**
 * 
* 模板接口
* @author 神盾局
* @date 2016年7月28日 下午4:00:16
* 
* @param <T>
 */
public interface ITemplate<T extends AbstractClazzInformation> {
	
	/**
	 * 
	* 构建代码
	* @param t
	* @return    设定文件
	 */
	String build(T t);

}
