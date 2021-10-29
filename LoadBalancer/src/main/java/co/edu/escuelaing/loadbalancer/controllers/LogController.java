package co.edu.escuelaing.loadbalancer.controllers;

import co.edu.escuelaing.loadbalancer.entities.Log;
import co.edu.escuelaing.loadbalancer.services.LogService;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    public Log[] logText(Request req, Response res) {
        Log[] result = null;
        String text = req.queryParams("text");
        if (text == null) {
            halt(400, "empty text param");
        }

        try {
            result = logService.saveAndGet(text);
            res.type("application/json");
        } catch (RuntimeException e) {
            halt(500, e.getLocalizedMessage());
        }
        return result;
    }

    public Log[] getLatest(Request req, Response res) {
        Log[] result = null;

        try {
            result = logService.getLatest();
            res.type("application/json");
        } catch (RuntimeException e) {
            halt(500, e.getLocalizedMessage());
        }
        return result;
    }
}
