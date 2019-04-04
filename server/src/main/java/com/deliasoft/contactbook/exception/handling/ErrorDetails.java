package com.deliasoft.contactbook.exception.handling;

import java.util.Date;

public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String path;
    private int status;
    private String exception;

    public ErrorDetails(Date timestamp, String message, String path, int status, String exception) {
        this.timestamp = timestamp;
        this.message = message;
        this.path = path;
        this.status = status;
        this.exception = exception;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
