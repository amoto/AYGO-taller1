package co.edu.escuelaing.logservice.controllers;

import co.edu.escuelaing.logservice.entities.Log;
import co.edu.escuelaing.logservice.services.LogService;
import spark.Request;
import spark.Response;

import java.util.List;

import static spark.Spark.halt;

public class LogController {
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    public List<Log> logText(Request req, Response res) {
        String text = req.queryParams("text");
        if (text == null) {
            halt(400, "empty text param");
        }

        res.type("application/json");
        return logService.saveAndGet(text);
    }

    public List<Log> getLatest(Request req, Response res) {
        res.type("application/json");
        return logService.getLatest();
    }
}
