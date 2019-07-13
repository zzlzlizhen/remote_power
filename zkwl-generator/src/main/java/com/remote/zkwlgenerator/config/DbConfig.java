package com.remote.zkwlgenerator.config;

import com.remote.zkwlgenerator.dao.*;
import com.remote.zkwlgenerator.utils.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 数据库配置
 *
 *
 */
@Configuration
public class DbConfig {
    @Value("${zkwl.database: mysql}")
    private String database;
    private final MySQLGeneratorDao mySQLGeneratorDao;
    private final OracleGeneratorDao oracleGeneratorDao;
    private final SQLServerGeneratorDao sqlServerGeneratorDao;
    private final PostgreSQLGeneratorDao postgreSQLGeneratorDao;

    @Autowired
    public DbConfig(MySQLGeneratorDao mySQLGeneratorDao, OracleGeneratorDao oracleGeneratorDao, SQLServerGeneratorDao sqlServerGeneratorDao, PostgreSQLGeneratorDao postgreSQLGeneratorDao) {
        this.mySQLGeneratorDao = mySQLGeneratorDao;
        this.oracleGeneratorDao = oracleGeneratorDao;
        this.sqlServerGeneratorDao = sqlServerGeneratorDao;
        this.postgreSQLGeneratorDao = postgreSQLGeneratorDao;
    }

    @Bean
    @Primary
    public GeneratorDao getGeneratorDao(){
        if("mysql".equalsIgnoreCase(database)){
            return mySQLGeneratorDao;
        }else if("oracle".equalsIgnoreCase(database)){
            return oracleGeneratorDao;
        }else if("sqlserver".equalsIgnoreCase(database)){
            return sqlServerGeneratorDao;
        }else if("postgresql".equalsIgnoreCase(database)){
            return postgreSQLGeneratorDao;
        }else {
            throw new RRException("不支持当前数据库：" + database);
        }
    }
}
