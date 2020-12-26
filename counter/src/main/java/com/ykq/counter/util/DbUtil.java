package com.ykq.counter.util;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class DbUtil {
    private static DbUtil dbUtil = null;

    private DbUtil() {
    }

    //第三部
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    //第四部
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    //第二步
    @PostConstruct
    private void init() {
        dbUtil = new DbUtil();
        //第四部
        dbUtil.setSqlSessionTemplate(this.sqlSessionTemplate);
    }



}
