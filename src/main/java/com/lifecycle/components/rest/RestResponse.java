package com.lifecycle.components.rest;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
public class RestResponse {	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
	private Date timestamp;

    private int status;
    private String message;
    private String path;

	public static RestResponse build(int status, String message, String path, Date timestamp) throws IOException {
		return RestResponse.builder()
						   .status(status)
						   .message(message)
						   .path(path)
						   .timestamp(timestamp)
						   .build();
	}
}
