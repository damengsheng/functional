package standard.tools;

import java.io.Serializable;

/**
 * Pair
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 03/26/2019 23:30.
 */
public class Pair<K, V> implements Serializable {

    private static final long serialVersionUID = -8225921511754267149L;

    private K key;
    private V value;

    public K key() {
        return key;
    }

    public Pair<K, V> setKey(K key) {
        this.key = key;
        return this;
    }

    public V value() {
        return value;
    }

    public Pair<K, V> setValue(V value) {
        this.value = value;
        return this;
    }

    public Pair() {
    }

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public static <K, V> Pair<K, V> of(K key, V value) {
        return new Pair<>(key, value);
    }

    @Override
    public String toString() {
        return "{ "
                .concat(null == key ? "null" : key.toString())
                .concat(": ")
                .concat(null == value ? "null" : value.toString())
                .concat(" }");
    }

    @Override
    public int hashCode() {
        final int prime  = 31;
        int       result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Pair) {
            Pair pair = (Pair) o;
            if (key != null ? !key.equals(pair.key) : pair.key != null) return false;
            return value != null ? value.equals(pair.value) : pair.value == null;
        }
        return false;
    }
}