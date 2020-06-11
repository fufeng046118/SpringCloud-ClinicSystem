package cn.project.dao;

import cn.project.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class InStockDaoImpl implements InStockDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public List<InStock> getAll(Map<String,Object> map) {
        StringBuffer hql = new StringBuffer("from InStock i left outer join fetch i.instocktype left outer join fetch i.employee left outer join fetch i.makeOrder left outer join fetch i.audit left outer join fetch i.manufacturer where 1=1 ");
        List<Object> str = new ArrayList<>();
        hql.append(common(map,str));
        hql.append(" order by i.id ");
        Query query = getSession().createQuery(hql.toString());
        for (int i = 0;i<str.size();i++){
            query.setParameter(i,str.get(i));
        }
        return  query.setFirstResult((int)map.get("pageNo"))
                .setMaxResults((int)map.get("pageSize"))
                .list();
    }

    @Override
    public int count(Map<String, Object> map) {
        StringBuffer hql = new StringBuffer("select count(*) from InStock i left outer join i.instocktype left outer join i.employee left outer join i.makeOrder left outer join i.audit left outer join i.manufacturer where 1=1 ");
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

    @Override
    public List<InStockType> getInStockType() {
        String hql = "from InStockType";
        return getSession().createQuery(hql).list();
    }

    @Override
    public InStock getInStockById(int id) {
        System.out.println(id);
        String hql = "from InStock i left outer join fetch i.instocktype left outer join fetch i.employee left outer join fetch i.makeOrder left outer join fetch i.audit left outer join fetch i.manufacturer where i.id = ?";
        List list = getSession().createQuery(hql).setParameter(0, (long)id).list();
        return (InStock)list.get(0);
    }

    @Override
    public List<InStockMedicine> getAllInStockMedicineById(int id) {
        String hql = "from InStockMedicine ism left outer join fetch ism.medicine left outer join fetch ism.medicine where ism.inStockId = ?";
        return getSession().createQuery(hql).setParameter(0,id).list();
    }

    @Override
    public int deleteInStockById(int id) {
        String hql = "delete from InStock i where i.id="+id;
        return getSession().createQuery(hql).executeUpdate();
    }

    @Override
    public int deleteInStockMedicineById(int id) {
        String hql = "delete from InStockMedicine ism where ism.inStockId = "+id;
        return getSession().createQuery(hql).executeUpdate();
    }

    @Override
    public List<Manufacturer> getAllManufacturer() {
        return getSession().createQuery("from Manufacturer").list();
    }

    @Override
    public List<Employee> getAllEmployee() {
        return getSession().createQuery("from Employee").list();
    }

    @Override
    public int addInStock(InStock inStock) {
        getSession().save(inStock);
        return 1;
    }

    @Override
    public int addInStockMedicine(InStockMedicine inStockMedicine) {
        getSession().save(inStockMedicine);
        return 1;
    }

    @Override
    public int updateStatus(int inStockId, int statusId, Date date,int auditId) {
        Long id = new Long(inStockId);
        InStock inStock = getSession().get(InStock.class, id);
        inStock.setStatusId(statusId);
        inStock.setAuditId(auditId);
        inStock.setAuditDate(date);
        getSession().update(inStock);
        return 1;
    }

    private StringBuffer common(Map<String,Object> map,List<Object> list){
        StringBuffer hql = new StringBuffer("");
        if(map.get("statusId") != null && !"".equals(map.get("statusId"))){
            hql.append(" and i.statusId = ?");
            list.add(Integer.parseInt(map.get("statusId").toString()));
        }
        if(map.get("type") != null && !"".equals(map.get("type"))){
            hql.append(" and i.type = ?");
            list.add(Integer.parseInt(map.get("type").toString()));
        }
        if(map.get("name") != null && !"".equals(map.get("name"))){
            hql.append(" and ( i.inStockNo like ? or i.manufacturer.name like ? ) ");
            list.add("%"+map.get("name")+"%");
            list.add("%"+map.get("name")+"%");
        }
        return hql;
    }

    @Override
    public int updateMedicineStock(int id, Long count) {
        String hql = "update Medicine set stock = stock+"+count+" where id = "+id;
        return getSession().createQuery(hql).executeUpdate();
    }
}
