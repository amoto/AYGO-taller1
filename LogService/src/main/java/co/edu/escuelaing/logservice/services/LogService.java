package co.edu.escuelaing.logservice.services;

import co.edu.escuelaing.logservice.entities.Log;
import co.edu.escuelaing.logservice.repositories.LogRepository;

import java.util.List;

public class LogService {
    private final LogRepository logRepository;
    private static final int DOCS_TO_READ = 10;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<Log> saveAndGet(String text) {
        logRepository.save(text);
        return logRepository.getLast(DOCS_TO_READ);
    }

    public List<Log> getLatest() {
        return logRepository.getLast(DOCS_TO_READ);
    }
}
