package com.demo.kltn.utils;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class CustomIdGenerator implements IdentifierGenerator, Configurable {
    String prefix;
    String entity;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String suffix = "";
        try {
            Connection connection = session.connection();
            Statement statement = connection.createStatement();
            String query = "select count(id) from " + entity;
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                Integer id = resultSet.getInt(1) + 1;
                suffix = id.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prefix + suffix;
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        prefix = params.getProperty("prefix");
        entity = params.getProperty("entity");
        IdentifierGenerator.super.configure(type, params, serviceRegistry);
    }
}
