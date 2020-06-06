package cn.his2.server.uaa.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * VO用来在视图层和控制层之间传递数据
 * 可以使用swagger生成文档
 */
@Data
@ApiModel("登录用户对象")
public class UserVO {
    @ApiModelProperty(value = "ID", example = "1000")
    private Integer id;

    @ApiModelProperty(value = "用户名", example = "admin1", required = true)
    @Size(min=6, max=20, message = "用户名必须在{min}到{max}之间")
    private String username;

    @ApiModelProperty(value = "密码", example = "123456", required = true)
    @Size(min=6, max=20, message = "密码必须在{min}到{max}之间")
    private String password;
}
