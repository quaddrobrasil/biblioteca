package br.com.quaddro.clinica;

import java.util.HashMap;

import com.google.gson.Gson;

import spark.Response;
import spark.ResponseTransformer;

public class ConversorJSON implements ResponseTransformer {

	private Gson gson = new Gson();

	public String render(Object arg0) throws Exception {
		
		if(arg0 instanceof Response) {
			return gson.toJson(new HashMap<>());
		}
		
		return gson.toJson(arg0);
	}
	
}
