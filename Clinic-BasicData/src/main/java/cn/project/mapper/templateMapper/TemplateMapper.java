package cn.project.mapper.templateMapper;


import cn.project.entity.PrescriptionMedicineC;
import cn.project.entity.PrescriptionMedicineX;
import cn.project.entity.PrescriptionMedicineZ;
import cn.project.entity.Template;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TemplateMapper {
    List<Template> getAllTemplate(Map<String, Object> map);
    Template getTemplateById(Integer id);
    List<PrescriptionMedicineX> getPrescriptionMedicineX(@Param("templateId") Integer templateId);
    List<PrescriptionMedicineZ> getPrescriptionMedicineZ(@Param("templateId") Integer templateId);
    List<PrescriptionMedicineC> getPrescriptionMedicineC(@Param("templateId") Integer templateId);


}
