package net.usrman.dao;


import net.usrman.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger= LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(User user) {
        Session session=this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User successfully saved. User information: "+user);
    }

    public void updateUser(User user) {
        Session session=this.sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User successfully update. User information: "+user);
    }

    public void removeUser(int id) {
        Session session=this.sessionFactory.getCurrentSession();
        User user=(User)session.load(User.class,new Integer(id));

        if(user!=null){
            session.delete(user);
        }
        logger.info("User successfully delete. User information: "+user);
    }

    public User getUserById(int id) {
        Session session=this.sessionFactory.getCurrentSession();
        User user=(User)session.load(User.class,new Integer(id));
        logger.info("User successfully load. User information: "+user);
        return user;
    }
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Session session=this.sessionFactory.getCurrentSession();
        List<User> userlist=session.createQuery("from User ").list();

        for (User user:userlist) {
            logger.info("User list: "+user);
        }
        return userlist;
    }

    @SuppressWarnings("unchecked")
    public List<User> userList(Integer begin, Integer step, String searchName) {

        Session session = this.sessionFactory.getCurrentSession();
        String query;
        if (searchName == null || searchName.isEmpty()) query = "SELECT * FROM user";
        else query = "select * from user where name like '%" + searchName + "%'";

        List<User> result = session
                .createSQLQuery(query)
                .addEntity(User.class)
                .setFirstResult(begin)
                .setMaxResults(step)
                .list();


        return result;
    }
}
