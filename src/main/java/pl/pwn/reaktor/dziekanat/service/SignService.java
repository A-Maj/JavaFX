package pl.pwn.reaktor.dziekanat.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.model.dto.StudentDTO;
import pl.pwn.reaktor.dziekanat.utils.HibernateUtils;

import java.util.List;

import static pl.pwn.reaktor.dziekanat.model.RoleEnum.ROLE_STUDENT;

public class SignService {

    public void saveUser(String login, String password) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setRole(ROLE_STUDENT);
        newUser.setActive(true);

        session.save(newUser);


        transaction.commit();
        session.close();
//
//        String queryString = "insert into User values(null, 1, ':login', ':pass', 'ROLE_STUDENT')";
//        Query query = session.createQuery(queryString);
//        query.setParameter("login", login);
//        query.setParameter("pass", password);
//        User registeredUser = (User) query;
    }

    public void update(User user) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        session.update(user);

        transaction.commit();
        session.close();
    }

    public List<User> getAllAdmins() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("select u from User u where u.role= 'ROLE_ADMIN'");

        List<User> admins = query.list();

        transaction.commit();
        session.close();
        return admins;

    }

    public List<StudentDTO> getAllStudents() {

        Session session = HibernateUtils.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        String hql = "select new pl.pwn.reaktor.dziekanat.model.dto.StudentDTO " +
                "(s.id, u.login, s.firstName, s.lastName, s.address.street, s.address.city) " +
                "from User u inner join u.student s where u.role = 'ROLE_STUDENT'";

        Query query = session.createQuery(hql);

        List<StudentDTO> students = query.list();

        transaction.commit();
        session.close();
        return students;
    }
}
