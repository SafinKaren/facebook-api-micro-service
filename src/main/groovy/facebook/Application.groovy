package facebook

import facebook.props.AppProperties
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.ConfigurableApplicationContext

@EnableConfigurationProperties([AppProperties.class])
@SpringBootApplication
class Application {

    static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application, args)
        AppProperties properties = context.getBean('appProperties')
        System.out.println("AppProperties: " + properties.toString())
    }
}