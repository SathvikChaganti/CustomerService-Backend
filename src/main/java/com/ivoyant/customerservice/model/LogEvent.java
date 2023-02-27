package com.ivoyant.customerservice.model;
import java.time.LocalDateTime;

public class LogEvent {

    private String className;
    private String methodName;
    private String message;
    private LocalDateTime dateTime;

    public LogEvent(String className, String methodName, String message, LocalDateTime dateTime) {
        this.className = className;
        this.methodName = methodName;
        this.message = message;
        this.dateTime = dateTime;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
}
