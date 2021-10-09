package gerimedica.code.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Java Source CodeAPIServiceSelfHostApp.java created on Oct 8, 2021
 *
 * @author : Sanjeev Saxena
 * @email : sanrocks123@gmail.com
 * @version : 1.0
 */

@ComponentScan(basePackages = { "gerimedica" })
@EntityScan(basePackages = { "gerimedica" })
@EnableJpaRepositories(basePackages = { "gerimedica" })
@EnableSwagger2
@SpringBootApplication(scanBasePackages = "gerimedica")
public class CodeAPIServiceSelfHostApp {

    private static final Logger LOG = LoggerFactory.getLogger(CodeAPIServiceSelfHostApp.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(CodeAPIServiceSelfHostApp.class);
        app.run(args);

        LOG.info("Swagger API Docs - http://localhost:8080/swagger-ui.html");
        LOG.info("H2 Console - http://localhost:8080/h2-console");

    }

}
