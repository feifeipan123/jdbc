package com._520it.smis.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

//��ʾ�ѽ������һ�����ݷ�װ��һ������ר����Խ������ֻ��һ�����ݵ����
public class BeanHandler<T> implements IResultSetHandler<T> {
   //�ѽ������һ�����ݷ�װ��ʲô���͵Ķ���
	private Class<T> classType;

	public BeanHandler(Class<T> classType) {
		this.classType = classType;
	}


	public T handle(ResultSet rs) throws Exception {
		//������Ӧ���һ������
		T obj = classType.newInstance();
		//ȡ��������е�ǰ��������е�ĳһ�е�����
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
