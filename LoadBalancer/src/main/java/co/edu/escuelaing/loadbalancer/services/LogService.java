package co.edu.escuelaing.loadbalancer.services;

import co.edu.escuelaing.loadbalancer.entities.Log;
import co.edu.escuelaing.loadbalancer.repositories.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class LogService {
    private final Logger logger = LoggerFactory.getLogger(LogService.class);
    private final LogRepository logRepository;
    private final String[] serversPool;
    private final int poolSize;
    private final AtomicInteger reqCount;

    public LogService(LogRepository logRepository, String[] servers) {
        this.logRepository = logRepository;
        this.serversPool = servers;
        this.poolSize = servers.length;
        this.reqCount = new AtomicInteger(0);
        logger.info("LogService initialized with {} servers: {}", poolSize, Arrays.toString(servers));
    }

    public Log[] saveAndGet(String text) {
        int server = reqCount.incrementAndGet() % poolSize;
        return logRepository.saveAndGet(text, serversPool[server]);
    }

    public Log[] getLatest() {
        int server = reqCount.incrementAndGet() % poolSize;
        return logRepository.getLatest(serversPool[server]);
    }
}
