package com.akhter.CollegeLibraryServer.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Transactional
public abstract class BaseDBIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private DataSource dataSource;

    @BeforeTransaction
    public void init() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("sql/init-db.sql"));
        populator.addScripts(sqlScripts());
        populator.execute(dataSource);
    }


    public abstract ClassPathResource[] sqlScripts();
}


