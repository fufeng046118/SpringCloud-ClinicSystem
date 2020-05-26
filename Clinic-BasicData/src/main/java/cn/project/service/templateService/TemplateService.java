package cn.project.service.templateService;


import cn.project.entity.PrescriptionMedicineC;
import cn.project.entity.PrescriptionMedicineX;
import cn.project.entity.PrescriptionMedicineZ;
import cn.project.entity.Template;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface TemplateService {
    PageInfo<Template> getAllTemplate(Map<String, Object> map);
    Template getTemplateById(Integer id);
    List<PrescriptionMedicineX> getPrescriptionMedicineX(Integer templateId);
    List<PrescriptionMedicineZ> getPrescriptionMedicineZ(Integer templateId);
    List<PrescriptionMedicineC> getPrescriptionMedicineC(Integer templateId);
}
