package ru.aminovniaz.project.util;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyEntityManagerImpl implements MyEntityManager {

    @Override
    public <T> List<T> find(Class<T> clazz) {
        List<T> resultList = new ArrayList<>();

        Connection connection = DbWrapper.getConnection();

        String sqlQuery = "SELECT * FROM " + clazz.getSimpleName() + ";";

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sqlQuery);

            Field[] attributes = clazz.getDeclaredFields();

            while (resultSet.next()) {
                T t = clazz.newInstance();

                for (Field field : attributes) {
                    String fieldName = field.getName().substring(0, 1).toUpperCase()
                            + field.getName().substring(1);

                    Method setter = t.getClass().getMethod("set" + fieldName, field.getType());
                    setter.invoke(t, resultSet.getObject(field.getName()));
                }

                resultList.add(t);
            }

            return resultList;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T, ID> T findById(Class<T> clazz, ID id) {

        Connection connection = DbWrapper.getConnection();

        String sql = "SELECT * FROM " + clazz.getSimpleName() + " WHERE id = ?;";

        try {
            T t = clazz.newInstance();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {
                    String fieldName = field.getName().substring(0, 1).toUpperCase()
                            + field.getName().substring(1);

                    Method setter = t.getClass().getMethod("set" + fieldName, field.getType());
                    setter.invoke(t, result.getObject(field.getName()));
                }
            }

            return t;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <T> void save(T objectToSave) {
        Class<T> clazz = (Class<T>) objectToSave.getClass();

        String sql = "INSERT INTO " + clazz.getSimpleName() + " VALUES (";

        Field[] fields = clazz.getDeclaredFields();

        try {
            for (Field ignored : fields) {
                sql += "?,";
            }

            sql = sql.substring(0, sql.length() - 1) + ");";

            Connection connection = DbWrapper.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            for (int i = 0; i < fields.length; i++) {

                fields[i].setAccessible(true);
                Object fieldValue = fields[i].get(objectToSave);
                fields[i].setAccessible(false);

                if (fieldValue instanceof Integer) {
                    statement.setInt(i + 1, (Integer) fieldValue);
                } else if (fieldValue instanceof Long) {
                    statement.setLong(i + 1, (Long) fieldValue);
                } else {
                    statement.setObject(i + 1, fieldValue);
                }
            }

            System.out.println(statement.toString());
            boolean success = statement.execute();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public <T> List<T> find(Class<T> clazz, List<String> conditions, String operator) {

        List<T> resultList = new ArrayList<>();

        //Connection connection = DbWrapper.getConnection();

        String sqlQuery = "SELECT * FROM " + clazz.getSimpleName() + " WHERE ";

        for (String condition : conditions) {
            if (condition.equals(conditions.get(conditions.size() - 1))) {
                sqlQuery += condition + ";";
            } else {
                sqlQuery += condition + " " + operator + " ";
            }
        }

        System.out.println(sqlQuery);

        return null;
    }


}
