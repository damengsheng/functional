package mq.rocketmq.quickstart;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;


/**
 * RocketMQProducer
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/11 12:03.
 */
public class RocketMQProducer {

    private static DefaultMQProducer producer;

    public static DefaultMQProducer producer() {
        if (null != producer)
            return RocketMQProducer.producer;
        RocketMQProducer.producer = new DefaultMQProducer();
        RocketMQProducer.producer.setNamesrvAddr("192.168.1.115:9876");
        RocketMQProducer.producer.setMaxMessageSize(1024 * 1024 * 16); // 16MB
        RocketMQProducer.producer.setCreateTopicKey(MixAll.AUTO_CREATE_TOPIC_KEY_TOPIC);
        RocketMQProducer.producer.setProducerGroup("ProducerGroup");
        RocketMQProducer.producer.setDefaultTopicQueueNums(2);
        RocketMQProducer.producer.setLatencyMax(new long[]{50L, 100L, 550L, 1000L, 2000L, 3000L, 15000L});
        RocketMQProducer.producer.setRetryTimesWhenSendFailed(2);
        RocketMQProducer.producer.setSendMsgTimeout(3000);
        return RocketMQProducer.producer;
    }

    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message msg = new Message();
        msg.setTopic("TTT");
        msg.setBody("ttt".getBytes());
        RocketMQProducer.producer().send(msg);
    }
}
