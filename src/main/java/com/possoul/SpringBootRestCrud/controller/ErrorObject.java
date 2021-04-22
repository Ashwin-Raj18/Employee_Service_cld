package com.possoul.SpringBootRestCrud.controller;

public class ErrorObject  {

 private String message;

public ErrorObject(String message) {
   this.message=message;  
 }

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

@Override
public String toString() {
	return "ErrorObject [message=" + message + "]";
}


}
