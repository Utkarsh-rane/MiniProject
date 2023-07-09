package com.app.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor

public class ApiResponce {
private String mesg;
private LocalDateTime timeStamp;
public ApiResponce(String mesg) {
	super();
	this.mesg = mesg;
	this.timeStamp=LocalDateTime.now();
}


}
