package com._520it.smis.handler;

import java.sql.ResultSet;
import java.util.List;

//��������������淶����������ķ�������
public interface IResultSetHandler {
    public List handle(ResultSet rs);
}