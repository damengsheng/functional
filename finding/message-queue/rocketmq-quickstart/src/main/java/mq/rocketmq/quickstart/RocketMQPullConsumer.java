package mq.rocketmq.quickstart;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * RocketMQPullConsumer
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/11 12:04.
 */
public class RocketMQPullConsumer {


    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        String consumeGroup = "";
        String nameSrvAddr  = "";

        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(consumeGroup);
        consumer.setNamesrvAddr(nameSrvAddr);
        String                 topic    = "";
        Set<MessageQueue>      queues   = consumer.fetchSubscribeMessageQueues(topic);
        Iterator<MessageQueue> iterator = queues.iterator();
        for (; iterator.hasNext(); ) {
            MessageQueue queue  = iterator.next();
            long         offset = consumer.fetchConsumeOffset(queue, true);
            PullResult   pullRt = consumer.pull(queue, "*", offset, 1);
            switch (pullRt.getPullStatus()) {
                case FOUND:
                    List<MessageExt> msgs = pullRt.getMsgFoundList();
                    Iterator<MessageExt> msgIterator = msgs.iterator();
                    for (; msgIterator.hasNext(); ) {
                        MessageExt msg = msgIterator.next();
                        msg.getProperties();
                        msg.getBody();
                    }
            }
        }
    }
}
