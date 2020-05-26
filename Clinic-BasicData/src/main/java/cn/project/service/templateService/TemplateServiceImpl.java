package cn.project.service.templateService;

import cn.project.entity.PrescriptionMedicineC;
import cn.project.entity.PrescriptionMedicineX;
import cn.project.entity.PrescriptionMedicineZ;
import cn.project.entity.Template;
import cn.project.mapper.templateMapper.TemplateMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TemplateServiceImpl implements TemplateService {
    @Resource
    TemplateMapper templateMapper;
    @Override
    public PageInfo<Template> getAllTemplate(Map<String, Object> map) {
        PageHelper.startPage((int)map.get("pageNo"),(int)map.get("pageSize"));
        List<Template> list = templateMapper.getAllTemplate(map);
        PageInfo<Template> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Template getTemplateById(Integer id) {
        return templateMapper.getTemplateById(id);
    }

    @Override
    public List<PrescriptionMedicineX> getPrescriptionMedicineX(Integer templateId) {
        return templateMapper.getPrescriptionMedicineX(templateId);
    }

    @Override
    public List<PrescriptionMedicineZ> getPrescriptionMedicineZ(Integer templateId) {
        return templateMapper.getPrescriptionMedicineZ(templateId);
    }

    @Override
    public List<PrescriptionMedicineC> getPrescriptionMedicineC(Integer templateId) {
        return templateMapper.getPrescriptionMedicineC(templateId);
    }
}
