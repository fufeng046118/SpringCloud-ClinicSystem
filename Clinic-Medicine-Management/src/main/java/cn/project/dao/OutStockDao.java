package cn.project.dao;

import cn.project.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

@Repository
public class OutStockDao {
    @Resource
    SessionFactory sessionFactory;
    public List<Outstock> findOutstock(Map<String,Object> map){
        Session session = sessionFactory.getCurrentSession();
        StringBuffer sql = new StringBuffer("from Outstock o left outer join fetch o.outstocktype left outer join fetch o.employee left outer join fetch o.makeorder where 1=1");
        map.forEach((k,v) ->{
            if(map.get(k)!=null&&!"".equals(map.get(k).toString())){
                if(k.equals("statusid")){
                    sql.append(" and o."+k+"="+v);
                }
                if(k.equals("typeid")){
                    sql.append(" and o.outstocktype.id="+v);
                }
                if(k.equals("outstockno")){
                    sql.append(" and o."+k+" like "+"'%"+v+"%'");
                }
            }
        });
        sql.append(" order by o.id");
        Query query = session.createQuery(sql.toString());
        query.setFirstResult((Integer.parseInt(map.get("pageNo").toString())-1)*Integer.parseInt(map.get("pageSize").toString()));
        query.setMaxResults(Integer.parseInt(map.get("pageSize").toString()));
     return  query.list();
    }

    public int findCount(Map<String, Object> map) {
        Session session = sessionFactory.getCurrentSession();
        StringBuffer sql = new StringBuffer("select count(*) from Outstock where 1=1 ");
            map.forEach((k,v) ->{
                if(map.get(k)!=null&&!"".equals(map.get(k).toString())){
                    if(k.equals("statusid")){
                        sql.append(" and "+k+"="+v);
                    }
                    if(k.equals("typeid")){
                        sql.append(" and "+k+"="+v);
                    }
                    if(k.equals("outstockno")){
                        sql.append(" and "+k+" like "+"'%"+v+"%'");
                    }
                }
            });
        Query query = session.createQuery(sql.toString());
        return ((Long)query.uniqueResult()).intValue();
    }
    public List<Outstocktype> findType(){
        Session session = sessionFactory.getCurrentSession();
        String sql =  "from Outstocktype ";
        return session.createQuery(sql).list();
    }
    public List<Employee> findEmployee(){
        Session session = sessionFactory.getCurrentSession();
        String sql =  "from Employee";
        return session.createQuery(sql).list();
    }
    public Outstock findOutStockDetail(Integer id){
        Session session=sessionFactory.getCurrentSession();
        String sql="from Outstock o left outer join fetch o.outstocktype left outer join fetch o.employee left outer join fetch o.makeorder left outer join fetch o.manufacturer left outer join fetch o.audit where o.id="+id;
        return (Outstock)session.createQuery(sql).uniqueResult();

    }
    public void delOutstock(Integer id){
        Session session=sessionFactory.getCurrentSession();
        String sql = "delete from Outstock where id="+id;
        session.createQuery(sql).executeUpdate();
    }
    public void delOutstockMedicine(Integer id){
        Session session=sessionFactory.getCurrentSession();
        String sql = "delete from OutstockMedicine where outstockid="+id;
        session.createQuery(sql).executeUpdate();
    }

    public List<OutstockMedicine> findOutstockMedicine(Integer id){
        Session session = sessionFactory.getCurrentSession();
        String sql = "from OutstockMedicine om left outer join fetch om.medicine where om.outstockid="+id;
        return session.createQuery(sql).list();
    }
    public List<Medicine> findMedicine(){
        Session session = sessionFactory.getCurrentSession();
        String sql = "from Medicine m left outer join fetch m.prescriptionType left outer join fetch m.manufacturer order by m.id ";
        return session.createQuery(sql).list();
    }
    public List<Medicine> findMedicineByInfo(Map<String,Object> map){
        Session session = sessionFactory.getCurrentSession();
        StringBuffer sql = new StringBuffer("from Medicine m left outer join fetch m.prescriptionType left outer join fetch m.manufacturer where 1=1 ");
        map.forEach((k,v) ->{
            if(map.get(k)!=null&&!"".equals(map.get(k).toString())){
                if(k.equals("prescriptionid")){
                    sql.append(" and m.prescriptionType.id = "+v);
                }
                if(k.equals("mno")){
                    sql.append(" and (m.medicineName like "+"'%"+v+"%'"+" or m.medicineNo like "+"'%"+v+"%')");
                }
            }
        });
        sql.append(" order by m.id ");
        Query query = session.createQuery(sql.toString());
        return query.list();
    }
    public void addOutstockMedicine(OutstockMedicine outstockMedicine){
        Session session = sessionFactory.getCurrentSession();
        session.save(outstockMedicine);
    }
    public void addOutstock(Outstock outstock){
        Session session = sessionFactory.getCurrentSession();
        session.save(outstock);
    }
    public void updateStock(long count,long id){   //对药品库存进行修改  双用   1.审核时修改 2.再次入库时修改
        Session session = sessionFactory.getCurrentSession();
        String sql = "update Medicine set stock=stock-"+count+" where id="+id;
        session.createQuery(sql).executeUpdate();
    }
/*    public void updateOutStockMedicine(long id){   //再次出库对出库详情表修改库存
        Session session = sessionFactory.getCurrentSession();
        String sql = "update OutstockMedicine set count=count*2 where id="+id;
        session.createQuery(sql).executeUpdate();
    }*/
}
