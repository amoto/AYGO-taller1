package co.edu.escuelaing.loadbalancer.configs;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;

public class Configs {

    public static int getServerPort() {
        String port = System.getenv("PORT");
        if (port != null) {
            return Integer.parseInt(port);
        }
        return 4567;
    }

    public static String[] getServersPool() {
        String pool = System.getenv("SERVER_POOL");
        if (pool != null) {
            return pool.split(";");
        }
        return new String[]{"http://localhost:4568"};
    }

    public static Gson getGson() {
        return new Gson();
    }

    public static OkHttpClient getHttpClient() {
        return new OkHttpClient();
    }
}
