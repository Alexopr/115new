package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getMySQLConnection_HIBERNATE();
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS table_test" +
                "(id INT UNSIGNED NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(45) NOT NULL," +
                "lastname VARCHAR(45)," +
                "age TINYINT," +
                "PRIMARY KEY (id))";

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS table_test";

        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
        }catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        }catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {

        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
        }catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public  List<User> getAllUsers() {

        List<User> userList = null;
        Transaction transaction = null;

        String hql = "from User";

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            userList = session.createQuery(hql, User.class).getResultList();
            transaction.commit();
            return userList;
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {

        String sql = "TRUNCATE TABLE table_test";
        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
        }catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }
}
