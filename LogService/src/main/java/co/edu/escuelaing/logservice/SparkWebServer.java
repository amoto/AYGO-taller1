package co.edu.escuelaing.logservice;

import co.edu.escuelaing.logservice.configs.Injector;
import co.edu.escuelaing.logservice.configs.LogConfigs;
import co.edu.escuelaing.logservice.controllers.LogController;
import com.google.gson.Gson;

import static spark.Spark.get;
import static spark.Spark.port;

public class SparkWebServer {

    private static final Injector injector = Injector.instance;

    public static void main(String... args){
        LogController controller = injector.getLogController();

        Gson gson = new Gson();
        port(LogConfigs.getServerPort());
        get("hello", (req,res) -> "it works!");
        get("save", controller::logText, gson::toJson);
        get("latest", controller::getLatest, gson::toJson);
    }

}