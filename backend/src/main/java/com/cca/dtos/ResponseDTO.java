package com.cca.dtos;

public class ResponseDTO {
    private String msg;


    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    // public String getStatusCode() {
    //     return this.statusCode;
    // }

    // public void setStatusCode(String statusCode) {
    //     this.statusCode = statusCode;
    // }

    public ResponseDTO(String msg) {
        this.msg = msg;
        // this.statusCode = statusCode;
    }


}
