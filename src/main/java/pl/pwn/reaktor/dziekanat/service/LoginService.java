package pl.pwn.reaktor.dziekanat.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.utils.HibernateUtils;

public class LoginService {

    public User login(String login, String password) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        String queryString = "select u from User u where u.login=:login and u.password=:pass and u.active=1";
        Query query = session.createQuery(queryString);
        query.setParameter("login", login);
        query.setParameter("pass", password);
        query.setMaxResults(1);
        User loggedUser = (User) query.uniqueResult();

        transaction.commit();
        session.close();
        return loggedUser;
    }
}

