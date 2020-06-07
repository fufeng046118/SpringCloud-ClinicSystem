package cn.project.dao;

import cn.project.entity.Medicine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
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
        StringBuffer hql = new StringBuffer("FROM Medicine m left outer join fetch m.prescriptionType left outer join fetch m.manufacturer  where 1=1 ");
        List<Object> str = new ArrayList<>();
        hql.append(common(map,str));
        hql.append(" order by m.id ");
        Query query = getSession().createQuery(hql.toString());
        for (int i = 0;i<str.size();i++){
            query.setParameter(i,str.get(i));
        }
        return  query.setFirstResult((int)map.get("pageNo"))
                .setMaxResults((int)map.get("pageSize"))
                .list();
    }

    public int totalCount(Map<String,Object> map){
        StringBuffer hql = new StringBuffer("select count(*) from Medicine m left outer join m.prescriptionType left outer join m.manufacturer where 1=1 ");
        List<Object> str = new ArrayList<>();
        hql.append(common(map,str));
        Query query = getSession().createQuery(hql.toString());
        for (int i = 0;i<str.size();i++){
            query.setParameter(i,str.get(i));
        }
        int totalCount = ((Long)query.uniqueResult()).intValue();
        System.out.println(totalCount);
        return totalCount;
    }

    private StringBuffer common(Map<String,Object> map,List<Object> list){
        StringBuffer hql = new StringBuffer("");
        if(map.get("prescriptionTypeId") != null){
            hql.append(" and m.prescriptionType.id = ?");
            list.add(map.get("prescriptionTypeId"));
        }
        if(map.get("medicineStatus") != null){
            hql.append(" and m.medicineStatus = ?");
            list.add(map.get("medicineStatus"));
        }
        if(map.get("name") != null){
            hql.append(" and (m.medicineName like ? or m.medicineNo like ? or m.manufacturer.name = ?)");
            list.add("%"+map.get("name")+"%");
            list.add("%"+map.get("name")+"%");
            list.add(map.get("name"));
        }
        return hql;
    }

}
