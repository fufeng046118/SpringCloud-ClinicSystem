package cn.project.dao;

import cn.project.entity.Medicine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MedicineDaoImpl implements MedicineDao{
    private SessionFactory sessionFactory;

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Medicine> getAllMedicineByMap(Map<String,Object> map){
        StringBuffer hql = new StringBuffer("FROM Medicine m left outer join fetch m.prescriptionType left outer join fetch m.manufacturer  where 1=1 ");
        List<Object> str = new ArrayList<>();
        if(map.get("prescriptionTypeId") != null && !"".equals(map.get("prescriptionTypeId"))){
            hql.append(" and m.prescriptionType.id = ?");
            str.add(Integer.parseInt(map.get("prescriptionTypeId").toString()));
        }
        if(map.get("name") != null && !"".equals(map.get("name"))){
            hql.append(" and (m.medicineName like ? or m.medicineNo like ? ) ");
            str.add("%"+map.get("name")+"%");
            str.add("%"+map.get("name")+"%");
        }
        hql.append(" order by m.id ");
        Query query = getSession().createQuery(hql.toString());
        for (int i = 0;i<str.size();i++){
            query.setParameter(i,str.get(i));
        }
        return  query.list();
    }

}
