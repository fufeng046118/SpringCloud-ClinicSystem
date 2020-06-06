package cn.his2.server.uaa.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@ApiModel("token对象,在前端和后端之间传递")
@NoArgsConstructor
@Accessors(chain = true)
public class TokenVO {
    @ApiModelProperty(value = "token值", example = "aaa.bbbbbb.ccc", required = true)
    private String token;
}
