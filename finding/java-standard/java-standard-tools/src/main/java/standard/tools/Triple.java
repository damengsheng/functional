package standard.tools;

import java.io.Serializable;

/**
 * Triple
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/05/20 09:41.
 */
public class Triple<T, K, V> implements Serializable {

    private static final long serialVersionUID = 2144327472662100517L;

    private T topic;
    private K key;
    private V value;

    public Triple(T topic) {
        this.topic = topic;
    }

    public Triple(T topic, K key, V value) {
        this.topic = topic;
        this.key   = key;
        this.value = value;
    }

    public static <T, K, V> Triple<T, K, V> of(T t, K key, V value) {
        return new Triple<>(t, key, value);
    }

    public T topic() {
        return topic;
    }

    public void setTopic(T topic) {
        this.topic = topic;
    }

    public K key() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V value() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{ "
                .concat(null == topic ? "null" : topic.toString())
                .concat(", ")
                .concat(null == key ? "null" : key.toString())
                .concat(", ")
                .concat(null == value ? "null" : value.toString())
                .concat(" }");
    }

//    @Override
//    public int hashCode() {
//        return 31 + (null != topic ? topic.hashCode() : 0) +
//               13 + (null != key ? key.hashCode() : 0) +
//               31 + (null != value ? value.hashCode() : 0);
//    }

    @Override
    public int hashCode() {
        final int prime  = 31;
        int       result = 1;
        result = prime * result + ((topic == null) ? 0 : topic.hashCode());
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Triple) {
            Triple triple = (Triple) o;
            if (topic != null ? !topic.equals(triple.topic) : triple.topic != null) return false;
            if (key != null ? !key.equals(triple.key) : triple.key != null) return false;
            return value != null ? value.equals(triple.value) : triple.value == null;
        }
        return false;
    }
}
