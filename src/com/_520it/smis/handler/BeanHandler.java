package com._520it.smis.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

//表示把结果集的一行数据封装成一个对象，专门针对结果集中只有一行数据的情况
public class BeanHandler<T> implements IResultSetHandler<T> {
   //把结果集的一行数据封装成什么类型的对象
	private Class<T> classType;

	public BeanHandler(Class<T> classType) {
		this.classType = classType;
	}


	public T handle(ResultSet rs) throws Exception {
		//创建对应类的一个对象
		T obj = classType.newInstance();
		//取出结果集中当前光标所在行的某一列的数据
		BeanInfo beaninfo = Introspector.getBeanInfo(classType,Object.class);
		PropertyDescriptor[] pds = beaninfo.getPropertyDescriptors();
		if(rs.next()) {
			for (PropertyDescriptor pd : pds) {
				String columnName = pd.getName();
				Object val = rs.getObject(columnName);
				pd.getWriteMethod().invoke(obj, val);
			}
		}
		return obj;
	}
}
