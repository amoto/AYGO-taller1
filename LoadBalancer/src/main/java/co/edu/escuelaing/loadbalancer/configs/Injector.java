package co.edu.escuelaing.loadbalancer.configs;

import co.edu.escuelaing.loadbalancer.controllers.LogController;
import co.edu.escuelaing.loadbalancer.repositories.LogRepository;
import co.edu.escuelaing.loadbalancer.services.LogService;

public class Injector {

    public static final Injector instance = new Injector();

    private LogRepository logRepository;
    private LogService logService;
    private LogController logController;

    private Injector() {}

    public LogRepository getLogRepository() {
        if (logRepository == null) {
            logRepository = new LogRepository(Configs.getGson(), Configs.getHttpClient());
        }
        return logRepository;
    }

    public LogService getLogService() {
        if (logService == null) {
            logService = new LogService(getLogRepository(), Configs.getServersPool());
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
