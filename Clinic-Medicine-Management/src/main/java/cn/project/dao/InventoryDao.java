package cn.project.dao;

import cn.project.entity.Inventory;
import cn.project.entity.InventoryMedicine;
import cn.project.entity.vo.InventoryMedicineVo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Repository
public class InventoryDao {
    @Resource
    SessionFactory sessionFactory;
    public List<Inventory> findInventory(Map<String,Object> map){
        Session session = sessionFactory.getCurrentSession();
        StringBuffer sql = new StringBuffer("from Inventory i left outer join fetch i.employee where 1=1 ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        map.forEach((k,v) ->{
            if(map.get(k)!=null&&!"".equals(map.get(k).toString())){
                if(k.equals("inventorydate1")){
                    String date = sdf.format(v);
                    sql.append(" and i.inventorydate>=to_date("+"'"+date+"'"+",'yyyy-MM-dd')");
                }
                if(k.equals("inventorydate2")){
                    String date = sdf.format(v);
                    sql.append(" and i.inventorydate<=to_date("+"'"+date+"'"+",'yyyy-MM-dd')");
                }

                if(k.equals("inventoryno")){
                    sql.append(" and i.inventoryno like "+"'%"+v+"%'");
                }
            }
        });
        sql.append(" order by i.id ");
        Query query = session.createQuery(sql.toString());
        query.setFirstResult((Integer.parseInt(map.get("pageNo").toString())-1)*Integer.parseInt(map.get("pageSize").toString()));
        query.setMaxResults(Integer.parseInt(map.get("pageSize").toString()));
        return query.list();
    }
    public int findCount(Map<String,Object> map){
        Session session = sessionFactory.getCurrentSession();
        StringBuffer sql = new StringBuffer("select count(*) from Inventory i where 1=1 ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        map.forEach((k,v) ->{
            if(map.get(k)!=null&&!"".equals(map.get(k).toString())){
                if(k.equals("inventorydate1")){
                    String date = sdf.format(v);
                    sql.append(" and i.inventorydate>=to_date("+"'"+date+"'"+",'yyyy-MM-dd')");
                }
                if(k.equals("inventorydate2")){
                    String date = sdf.format(v);
                    sql.append(" and i.inventorydate<=to_date("+"'"+date+"'"+",'yyyy-MM-dd')");
                }
                if(k.equals("inventoryno")){
                    sql.append(" and i.inventoryno like "+"'%"+v+"%'");
                }
            }
        });
        Query query = session.createQuery(sql.toString());
        return ((Long)query.uniqueResult()).intValue();

    }
    public Inventory findInventoryDetail(Integer id){  //点击查看时查询出头部盘点信息
        Session session=sessionFactory.getCurrentSession();
        String sql="from Inventory i  left outer join fetch i.employee where i.id="+id;
        return (Inventory)session.createQuery(sql).uniqueResult();
    }
    public List<InventoryMedicine> findInventoryMedicineByInfo(Map<String,Object> map){
        Session session = sessionFactory.getCurrentSession();
        StringBuffer sql = new StringBuffer("from InventoryMedicine im left outer join fetch im.medicine where im.inventoryid="+map.get("id"));
        map.forEach((k,v) ->{
            if(map.get(k)!=null&&!"".equals(map.get(k).toString())){
                if(k.equals("prescriptionid")){
                    sql.append(" and im.medicine.prescriptionType.id="+v);
                }
                if(k.equals("mno")){
                    sql.append(" and (im.medicine.medicineName like "+"'%"+v+"%'"+" or im.medicine.medicineNo like "+"'%"+v+"%')");
                }
            }
        });
        sql.append(" order by im.id");
        Query query = session.createQuery(sql.toString());
        query.setFirstResult((Integer.parseInt(map.get("pageNo").toString())-1)*Integer.parseInt(map.get("pageSize").toString()));
        query.setMaxResults(Integer.parseInt(map.get("pageSize").toString()));
        return query.list();
    }
    public int findInventoryMedicineCount(Map<String,Object> map){
        Session session = sessionFactory.getCurrentSession();
        StringBuffer sql = new StringBuffer("select count(*) from InventoryMedicine im left outer join im.medicine where im.inventoryid="+map.get("id"));
        map.forEach((k,v) ->{
            if(map.get(k)!=null&&!"".equals(map.get(k).toString())){
                if(k.equals("prescriptionid")){
                    sql.append(" and im.medicine.prescriptionType.id="+v);
                }
                if(k.equals("mno")){
                    sql.append(" and (im.medicine.medicineName like "+"'%"+v+"%'"+" or im.medicine.medicineNo like "+"'%"+v+"%')");
                }
            }
        });
        Query query = session.createQuery(sql.toString());
        return ((Long)query.uniqueResult()).intValue();
    }
    public int updateInventoryDetail(InventoryMedicineVo inventoryMedicineVo){
        Session session = sessionFactory.getCurrentSession();
        String sql = "update InventoryMedicine set inventorystock="+inventoryMedicineVo.getInventorystock()+",difference="+inventoryMedicineVo.getDifference()+",mark="+inventoryMedicineVo.getMark()+" where id="+inventoryMedicineVo.getGeo();
        return session.createQuery(sql).executeUpdate();
    }
    public void delInventory(Integer id){
        Session session = sessionFactory.getCurrentSession();
        String sql = "delete from Inventory where id="+id;
        session.createQuery(sql).executeUpdate();
    }
    public void delInventoryMedicine(Integer id){
        Session session = sessionFactory.getCurrentSession();
        String sql = "delete from InventoryMedicine where inventoryid="+id;
        session.createQuery(sql).executeUpdate();
    }
    public void updateStatus(Integer id){  //修改状态
        Session session = sessionFactory.getCurrentSession();
        String sql = "update Inventory set status=1 where id="+id;
        session.createQuery(sql).executeUpdate();
    }
}
