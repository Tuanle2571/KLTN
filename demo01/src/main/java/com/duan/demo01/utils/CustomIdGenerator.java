package com.duan.demo01.utils;

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
        String sub = "00000";
        try {
            Connection connection = session.connection();
            Statement statement = connection.createStatement();
            String query = "SELECT MAX(id) FROM " + entity;
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String data = resultSet.getString(1).substring(prefix.length());
                if (data.equalsIgnoreCase("") | data == null) {
                    suffix = sub;
                } else {
                    Integer id = Integer.valueOf(data) + 1;
                    String num = id.toString();
                    suffix = sub.substring(num.length()) + num;
                }
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
