package cn.project.config;

import cn.project.controller.MedicineFeign;
import cn.project.utils.Response;
import cn.project.utils.ResponseEnum;
import org.springframework.stereotype.Component;
@Component
public class MedicineFeignFallback implements MedicineFeign {
    @Override
    public Response getMedicines(Integer prescriptionTypeId, Integer medicineTypeId, String nameOrPinYin, Integer pageNo, Integer pageSize) {
        return new Response(ResponseEnum.SUCCESS).setResponseBody("服务未能正常响应");
    }

    @Override
    public Response getMedicineType(Integer prescriptionTypeId) {
        return  new Response(ResponseEnum.SUCCESS).setResponseBody("服务未能正常响应");
    }

    @Override
    public Response getMedicineUsage() {
        return  new Response(ResponseEnum.SUCCESS).setResponseBody("服务未能正常响应");
    }

    @Override
    public Response updateMedicineStock(int id, int stock) {
        return new Response(ResponseEnum.SUCCESS).setResponseBody("服务未能正常响应");
    }
}
