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

    @Override
    public int updateStatus(int id) {
        //String hql = "update Medicine set medicineStatus = 0 where id = ?";
        Session session = getSession();
        Medicine medicine = session.get(Medicine.class,id);
        medicine.setMedicineStatus(1);
        session.update(medicine);
        //session.beginTransaction();
        /*int i = getSession().createQuery(hql).setParameter(0, id).executeUpdate();
        session.getTransaction().commit();*/
        return 1;
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
        if(map.get("prescriptionTypeId") != null && !"".equals(map.get("prescriptionTypeId"))){
            hql.append(" and m.prescriptionType.id = ?");
            System.out.println("in:"+map.get("prescriptionTypeId"));
            list.add(Integer.parseInt(map.get("prescriptionTypeId").toString()));
        }
        if(map.get("medicineStatus") != null && !"".equals(map.get("medicineStatus"))){
            hql.append(" and m.medicineStatus = ?");
            list.add(Integer.parseInt(map.get("medicineStatus").toString()));
        }
        if(map.get("name") != null && !"".equals(map.get("name"))){
            hql.append(" and (m.medicineName like ? or m.medicineNo like ? or m.manufacturer.name = ?) ");
            list.add("%"+map.get("name")+"%");
            list.add("%"+map.get("name")+"%");
            list.add(map.get("name"));
        }
        if(map.get("startCreateTime") != null){
            hql.append(" and m.createTime >= ?");
            list.add(map.get("startCreateTime"));
        }
        if(map.get("endCreateTime") != null){
            hql.append(" and m.endCreateTime <= ?");
            list.add(map.get("endCreateTime"));
        }
        return hql;
    }

}
