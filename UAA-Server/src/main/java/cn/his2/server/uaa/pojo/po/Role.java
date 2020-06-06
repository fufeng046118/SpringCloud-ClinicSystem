package cn.his2.server.uaa.pojo.po;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Role {
    private Integer roleId;
    private String name;
    private String nameCn;
}
