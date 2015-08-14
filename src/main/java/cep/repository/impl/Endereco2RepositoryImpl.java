package cep.repository.impl;

import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.stereotype.Repository;

import cep.model.Endereco2;
import cep.repository.Endereco2Repository;

@Repository
public class Endereco2RepositoryImpl implements Endereco2Repository {

    private static SessionFactory sessionFactory = null;

    private static ServiceRegistry serviceRegistry = null;

    public Endereco2 salvar(Endereco2 endereco) {
        configureSessao();
        Session s = null;
        Transaction t = null;

        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();

            s.saveOrUpdate(endereco);
            s.flush();
            t.commit();

        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return endereco;
    }

    public List<Endereco2> consultarCepBD(String cep) {
        configureSessao();

        Session s = null;
        Transaction t = null;
        List<Endereco2> enderecoList = null;
        try {
            s = sessionFactory.openSession();
            if (StringUtils.isNotEmpty(cep)) {
                Query q = s.createQuery("from Endereco2 where cep = :cep ");
                q.setParameter("cep", cep);
                
                enderecoList = q.list();
            } else {
                enderecoList = s.createQuery("from Endereco2 ").list();
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (s != null) {
                s.close();
            }
        }

        return enderecoList;
    }

    private static SessionFactory configureSessao() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();

        Properties properties = configuration.getProperties();

        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }

    @Override
    public void excluir(Integer id) {
        configureSessao();
        Session s = null;
        Transaction t = null;

        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
           Endereco2 result =  (Endereco2) s.get(Endereco2.class, id);
            s.delete(result);
            s.flush();
            t.commit();

        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

}
