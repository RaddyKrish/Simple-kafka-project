package FirstProducer;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;


@Component
public class MyFirstProducer {


    @Autowired
    public MyFirstProducer () {

    }

    private static List<String> bootstrapServers = new ArrayList(Collections.singletonList("localhost:9092"));

    public static Properties getProducerProps() {

        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return producerProps;
    }

    public static void send()  {
        try {
            String topic = "secondtopic";
            String message = "anotherMessage";


            ProducerRecord<String, Object> record = new ProducerRecord(topic, message);
            Properties prop = getProducerProps();
            KafkaProducer producer = new KafkaProducer(prop);
            RecordMetadata metadata = (RecordMetadata) producer.send(record).get();
            System.out.printf("sent record(key=%s value=%s) " +
                            "meta(partition=%d, offset=%d) time=%d\n",
                    record.key(), record.value(), metadata.partition(),
                    metadata.offset());
        } catch(Exception e){

       }

    }

 }
