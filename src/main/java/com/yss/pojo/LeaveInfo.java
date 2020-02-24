package com.yss.pojo;

import java.io.Serializable;

public class LeaveInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1090900L;
	private String id;
    private String message;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
