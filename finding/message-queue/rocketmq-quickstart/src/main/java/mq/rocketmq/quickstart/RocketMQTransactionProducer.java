package mq.rocketmq.quickstart;

import org.apache.rocketmq.client.producer.TransactionMQProducer;

/**
 * TransactionMQProducer
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/11 12:03.
 */
public class RocketMQTransactionProducer {

    private TransactionMQProducer transactionMQProducer;

    public RocketMQTransactionProducer buildProducer() {
        if (null != transactionMQProducer)
            return this;
        this.transactionMQProducer = new TransactionMQProducer();
        return this;
    }
}
