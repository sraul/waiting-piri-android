package waitingpiri.waitingpiri.connection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class JSONResult {

	static final String URL_LOCATION = "http://190.211.240.30/add_location.php";

	OkHttpClient client = new OkHttpClient();

	/**
	 * Solicita y devuelve la respuesta del servidor http..
	 */
	public String run(String url, String cod_barra) throws IOException {
		RequestBody formBody = new FormBody.Builder()
				.add("barra", cod_barra)
				.build();

		Request request = new Request.Builder()
				.url(url)
				.post(formBody)
				.build();

		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	/**
	 * add location..
     */
	public String addLocation(String nroChasis, String latitud, String longitud, int flag) throws IOException {
		RequestBody formBody = new FormBody.Builder()
				.add("NROCHASIS", nroChasis)
				.add("LATITUD", latitud)
				.add("LONGITUD", longitud)
				.add("FLAG", flag + "")
				.build();

		Request request = new Request.Builder()
				.url(URL_LOCATION)
				.post(formBody)
				.build();

		Response response = client.newCall(request).execute();
		return response.body().string();
	}

}
