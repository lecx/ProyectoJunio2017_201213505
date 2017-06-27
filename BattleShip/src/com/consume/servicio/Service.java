package com.consume.servicio;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class Service {

	public static OkHttpClient webClient = new OkHttpClient();

	public static void main(String[] args) {
		Service temp = new Service();
		//
		// String nombre = "administrador";
		// String clave = "123";
		// RequestBody formBody = new FormEncodingBuilder().add("usuario",
		// nombre)
		// .add("clave", clave).build();
		// String r = temp.getResponse("login", formBody);
		// System.out.println(r + "---");

		List<String> name = new ArrayList<String>();
		List<String> value = new ArrayList<String>();

		name.add("usuario");
		value.add("lecx");
//		name.add("clave");
//		value.add("123");
//		name.add("tipo");
//		value.add("usuario");
//		name.add("conectado");
//		value.add(Integer.toString(0));

		Map<String, String> result = temp
				.formRequest("getListUser", name, value);

		if (result.size() > 0) {
			System.out.println("usuario:" + result.get("usuario") + " tipo:"
					+ result.get("tipo"));
		}
	}

	public String getResponse(String metodo, RequestBody formBody) {

		try {
			URL url = new URL("https://lecx.pythonanywhere.com/" + metodo);
			Request request = new Request.Builder().url(url).post(formBody)
					.build();
			Response response = webClient.newCall(request).execute();
			String response_string = response.body().string();
			return response_string;
		} catch (MalformedURLException ex) {
			java.util.logging.Logger.getLogger(Service.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(Service.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return null;
	}

	public Map<String, String> formRequest(String metodo, List<String> name,
			List<String> value) {
		Map<String, String> response = new HashMap<String, String>();

		FormEncodingBuilder formBody = new FormEncodingBuilder();

		for (int i = 0; i < name.size(); i++) {
			formBody.add(name.get(i), value.get(i));
		}
		RequestBody body = formBody.build();

		String result = getResponse(metodo, body);
		System.out.println("resultado servicio: " + result);
		if (result != null) {
			response = parserResponse(result);
		}
		return response;
	}

	public Map<String, String> parserResponse(String response) {
		Map<String, String> result = new HashMap<String, String>();

		if (response != null && !response.equals("")) {
			if (!response.contains("Error")) {
				String[] separaResponse = response.split("\\|");
				for (String temp : separaResponse) {
					String[] separaTemp = temp.split("=");
					if (separaTemp.length > 0) {
						result.put(separaTemp[0], separaTemp[1]);
					}
				}
			}
		}
		return result;
	}

}