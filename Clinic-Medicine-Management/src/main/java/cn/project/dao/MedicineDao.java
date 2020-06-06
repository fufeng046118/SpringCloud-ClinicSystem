package cn.project.dao;

import cn.project.entity.Medicine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;

public class MedicineDao {
    private SessionFactory sessionFactory;

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Medicine> getAllMedicineByMap(Map<String,Object> map){
        int pageNo = 2;
        int pageSize = 3;
        String hql = "FROM Medicine m left outer join fetch m.prescriptionType left outer join fetch m.manufacturer";
        return getSession().createQuery(hql).setFirstResult(1).setMaxResults(2).list();
    }

}
