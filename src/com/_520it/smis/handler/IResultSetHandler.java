package com._520it.smis.handler;

import java.sql.ResultSet;
import java.util.List;

//结果集处理器，规范处理结果集的方法名称
public interface IResultSetHandler<T> {
     T handle(ResultSet rs) throws Exception;
}
