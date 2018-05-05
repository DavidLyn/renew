package com.vivi.renew.backend.accessory;

public class CommonResult {

    private boolean result = true;
    private String message = "ok";
    private Object data = null;

    public CommonResult() {
    }

    public CommonResult(Object data) {
        this.data = data;
        if (data == null) {
            this.result = false;
            this.message = "Invalid data!";
        }
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
