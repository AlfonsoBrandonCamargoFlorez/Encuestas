package com.abc.encuesta.infrastructure.models;

import java.util.Date;

public class ErrorCustom {
    private String message;
    private String error;
    private int status;
    private Date date;

    // Getter para message
    public String getMessage() {
        return message;
    }

    // Setter para message
    public void setMessage(String message) {
        this.message = message;
    }

    // Getter para error
    public String getError() {
        return error;
    }

    // Setter para error
    public void setError(String error) {
        this.error = error;
    }

    // Getter para status
    public int getStatus() {
        return status;
    }

    // Setter para status
    public void setStatus(int status) {
        this.status = status;
    }

    // Getter para date
    public Date getDate() {
        return date;
    }

    // Setter para date
    public void setDate(Date date) {
        this.date = date;
    }
}
