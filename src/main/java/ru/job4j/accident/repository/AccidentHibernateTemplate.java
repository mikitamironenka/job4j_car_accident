package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import java.util.List;

@Repository
public class AccidentHibernateTemplate {

    private final SessionFactory sf;

    public AccidentHibernateTemplate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident) {
        try (Session session = sf.openSession()) {
            session.save(accident);
            return accident;
        }
    }

    public List<Accident> getAll() {
        try (Session session = sf.openSession()) {
            return session
                .createQuery("from Accident", Accident.class)
                .list();
        }
    }

    public List<AccidentType> getTypes() {
        try (Session session = sf.openSession()) {
            return session
                .createQuery("from AccidentType", AccidentType.class)
                .list();
        }
    }

    public List<Rule> getRules() {
        try (Session session = sf.openSession()) {
            return session
                .createQuery("from Rule", Rule.class)
                .list();
        }
    }

    public void update(Accident accident) {
        try (Session session = sf.openSession())
        {
            session.beginTransaction();
            session.update(accident);
            session.getTransaction().commit();
        }
    }

    public Accident findAccidentById(int id) {
        Accident result;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.get(Accident.class, Integer.valueOf(id));
            session.getTransaction().commit();
        }
        return result;
    }

    public Rule getRuleById(int id) {
        Rule result;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.get(Rule.class, Integer.valueOf(id));
            session.getTransaction().commit();
        }
        return result;
    }
}
