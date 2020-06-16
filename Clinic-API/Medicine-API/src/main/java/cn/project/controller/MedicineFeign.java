package cn.project.controller;

import cn.project.config.FeignConfig;
import cn.project.config.MedicineFeignFallback;
import cn.project.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "Clinic-Medicine", fallback = MedicineFeignFallback.class,configuration = {FeignConfig.class})
public interface MedicineFeign {
    @PostMapping("medicine/getMedicines")
    public Response getMedicines (@RequestParam Integer prescriptionTypeId, @RequestParam Integer medicineTypeId, @RequestParam String nameOrPinYin, @RequestParam Integer pageNo, @RequestParam Integer pageSize);

    @GetMapping(value = "medicine/getMedicineType/{prescriptionTypeId}")
    public Response getMedicineType(@PathVariable("prescriptionTypeId") Integer prescriptionTypeId);

    @GetMapping("medicine/getMedicineUsage")
    public Response getMedicineUsage();

    @PostMapping("medicine/updateMedicineStock/{id}")
    public Response updateMedicineStock(@PathVariable int id,@RequestParam int stock);
}
