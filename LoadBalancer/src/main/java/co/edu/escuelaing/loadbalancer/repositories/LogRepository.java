package co.edu.escuelaing.loadbalancer.repositories;

import co.edu.escuelaing.loadbalancer.entities.Log;
import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LogRepository {
    private final Logger logger = LoggerFactory.getLogger(LogRepository.class);
    private final Gson gson;
    private final OkHttpClient client;

    public LogRepository(Gson gson, OkHttpClient client) {
        this.gson = gson;
        this.client = client;
    }

    public Log[] saveAndGet(String text, String server) {
        HttpUrl.Builder httpBuilder = HttpUrl.parse(server + "/save").newBuilder();
        httpBuilder.addQueryParameter("text", text);

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .build();
        return executeRequest(server, request);
    }

    public Log[] getLatest(String server) {
        Request request = new Request.Builder()
                .url(server + "/latest")
                .build();
        return executeRequest(server, request);
    }

    private Log[] executeRequest(String server, Request request) {
        try {
            logger.info("forwarding request to {}", request.url());
            Response response = client.newCall(request).execute();
            return gson.fromJson(response.body().string(), Log[].class);
        } catch (IOException e) {
            logger.error("error on request to " + server, e);
            throw new RuntimeException("error on request forwarding");
        }
    }
}
