package com.bookshop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceReponse<T> {

	private ServiceResponseHeader header;
	private T                     data;

}