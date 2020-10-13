package com.example.minesweeper;

import com.example.minesweeper.exception.MinesweeperException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Main Spring-Boot configuration
 */
@Configuration
public class MainConfig {
    private static final Log LOGGER = LogFactory.getLog(MainConfig.class);

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        // This is set at the server to load the database config.
        String database_url = System.getenv("DATABASE_URL");

        if (StringUtils.isEmpty(database_url)) {
            // if its not set, can be passed as a parameter (for local testing)
            database_url = System.getProperty("DATABASE_URL");

            if (StringUtils.isEmpty(database_url))
                throw new MinesweeperException("DATABASE_URL variable not set. Exiting with error.",
                        HttpStatus.INTERNAL_SERVER_ERROR);
        }

        URI dbUri = new URI(database_url);

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(dbUrl);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);

        return dataSourceBuilder.build();
    }

    /**
     * This is a simple logger for all incoming requests
     */
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(10000);
        return loggingFilter;
    }
}