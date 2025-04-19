package com.votify.monitoring;

import com.votify.shared.event.Event;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AuditLog {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditLog.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final String ADD_AUDIT_LOG_ENTRY_QUERY = """
            INSERT INTO auditlog(payload, timestamp) VALUES (:payload, :timestamp)
            """;

    @Transactional
    @EventListener
    public void handleEvent(Event event) {
        LOGGER.info(event.payload());
        try {
            jdbcTemplate.update(ADD_AUDIT_LOG_ENTRY_QUERY,
                    Map.of(
                            "payload", event.payload(),
                            "timestamp", Timestamp.from(event.timestamp())
                    )
            );
        } catch (Exception e) {
            LOGGER.error("Failed to add audit log entry", e);
        }
    }
}
