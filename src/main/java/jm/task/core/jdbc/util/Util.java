package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
//     реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:MySQL://localhost:3306/db_test";
    private static final String userNameJDBC = "root";
    private static final String passwordJDBC = "root";

    private static final String DRIVER_HIBERNATE = "com.mysql.cj.jdbc.Driver";
    private static final String HOST_HIBERNATE = "jdbc:mysql://localhost:3306/db_test";
    private static final String LOGIN_HIBERNATE = "root";
    private static final String PASSWORD_HIBERNATE = "root";
    private static SessionFactory sessionFactory = null;

    public static Connection getMySQLConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, userNameJDBC, passwordJDBC);
            connection.setAutoCommit(false);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getMySQLConnection_HIBERNATE() {

        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.driver_class", DRIVER_HIBERNATE)
                    .setProperty("hibernate.connection.url", HOST_HIBERNATE)
                    .setProperty("hibernate.connection.username", LOGIN_HIBERNATE)
                    .setProperty("hibernate.connection.password", PASSWORD_HIBERNATE)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

}
