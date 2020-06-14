package cn.project.util;


import lombok.Data;

/**
 * 接口响应
 *
 */
@Data
public class Response {

  private String code;

  private String message;

  private long responseTime = System.currentTimeMillis();

  private Object responseBody;
  
  public Response() {
  }
  
  public Response(ResponseEnum responseEnum) {
    this.code = responseEnum.getCode();
    this.message = responseEnum.getMessage();
  }

  public Response setResponseBody(Object responseBody) {
    this.responseBody = responseBody;
    return this;
  }

  public Object getResponseBody() {
    return responseBody;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
