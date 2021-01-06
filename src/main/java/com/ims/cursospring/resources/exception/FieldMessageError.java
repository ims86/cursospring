package com.ims.cursospring.resources.exception;

import java.io.Serializable;

public class FieldMessageError implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fieldname;
    private String message;

    public FieldMessageError(){}

    public FieldMessageError(String fieldname, String message) {
        this.fieldname = fieldname;
        this.message = message;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
