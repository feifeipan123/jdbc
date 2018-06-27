package com._520it.smis.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//��ʾ��������еĶ������ݣ���װ��һ������ļ��ϣ���Խ�������ж�������
public class BeanListHandler<T> implements IResultSetHandler<List<T>> {
	private Class<T> classType;

	public BeanListHandler(Class<T> classType) {
		this.classType = classType;
	}
	public List<T> handle(ResultSet rs) throws Exception {
		List<T> list = new ArrayList<T>();
		BeanInfo beaninfo = Introspector.getBeanInfo(classType,Object.class);
		PropertyDescriptor[] pds = beaninfo.getPropertyDescriptors();
		while(rs.next()) {
			T obj = classType.newInstance();
			for (PropertyDescriptor pd : pds) {
				String columnName = pd.getName();
				Object val = rs.getObject(columnName);
				pd.getWriteMethod().invoke(obj, val);
			}
			list.add(obj);
		}
		return list;
	}

}
