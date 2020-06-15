package FirstConsumer;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.time.Duration;
import java.util.*;

@Component
public class MyFirstConsumer {

    @Autowired
    public MyFirstConsumer () {

    }

    private static List<String> bootstrapServers = new ArrayList(Collections.singletonList("localhost:9092"));

    public static Properties getConsumerProps() {
        Properties props = new Properties();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return props;
    }


    public static void startConsumer() {
        Properties consumerProps = getConsumerProps();
        KafkaConsumer consumer = new KafkaConsumer(consumerProps);

        ArrayList <String> myTopic = new ArrayList();
        myTopic.add("secondtopic");
        consumer.subscribe(myTopic);

        consumer.seekToBeginning(consumer.assignment());
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
            System.out.println("Count of records: " +records.count());

            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("consumed: key = %s, value = %s, partition id= %s, offset = %s%n",
                        record.key(), record.value(), record.partition(), record.offset());
            }
            if (records.isEmpty()) {
                System.out.println("-- terminating consumer --");
            }

            consumer.commitSync();


    }

}
