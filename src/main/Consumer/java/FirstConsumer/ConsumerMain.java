package FirstConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication
@ConfigurationPropertiesScan("main.Consumer.java")

public class ConsumerMain {

    public static void main(String []args){
      SpringApplication.run(ConsumerMain.class, args);
        MyFirstConsumer.startConsumer();
    }
}
