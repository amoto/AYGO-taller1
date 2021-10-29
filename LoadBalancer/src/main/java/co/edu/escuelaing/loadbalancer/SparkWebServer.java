package co.edu.escuelaing.loadbalancer;

import co.edu.escuelaing.loadbalancer.configs.Configs;
import co.edu.escuelaing.loadbalancer.configs.Injector;
import co.edu.escuelaing.loadbalancer.controllers.LogController;
import com.google.gson.Gson;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.port;

public class SparkWebServer {

    private static final Injector injector = Injector.instance;

    public static void main(String... args) {
        Gson gson = new Gson();
        LogController controller = injector.getLogController();

        port(Configs.getServerPort());
        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        get("hello", (req,res) -> "it works!");
        get("save", controller::logText, gson::toJson);
        get("latest", controller::getLatest, gson::toJson);
    }

}