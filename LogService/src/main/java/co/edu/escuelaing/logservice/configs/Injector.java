package co.edu.escuelaing.logservice.configs;

import co.edu.escuelaing.logservice.controllers.LogController;
import co.edu.escuelaing.logservice.repositories.LogRepository;
import co.edu.escuelaing.logservice.services.LogService;

public class Injector {

    public static final Injector instance = new Injector();

    private LogRepository logRepository;
    private LogService logService;
    private LogController logController;

    private Injector() {}

    public LogRepository getLogRepository() {
        if (logRepository == null) {
            logRepository = new LogRepository(LogConfigs.getMongoCollection());
        }
        return logRepository;
    }

    public LogService getLogService() {
        if (logService == null) {
            logService = new LogService(getLogRepository());
        }
        return logService;
    }

    public LogController getLogController() {
        if (logController == null) {
            logController = new LogController(getLogService());
        }
        return logController;
    }
}
