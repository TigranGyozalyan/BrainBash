package am.aca.quiz.software.service.implementations.score;

import java.io.Serializable;
import java.util.Objects;

public class ScorePair<K, V> implements Serializable {


    private K key;
    private V value;

    public ScorePair() {
    }

    public ScorePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" + key + "," + value + "}";
    }

    @Override
    public int hashCode() {
        int hash = 8;
        hash = 37 * hash + (key != null ? key.hashCode() : 0);
        hash = 37 * hash + (value != null ? value.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof ScorePair) {
            ScorePair pair = (ScorePair) o;
            if (!Objects.equals(key, pair.key)) return false;
            if (!Objects.equals(value, pair.value)) return false;
            return true;
        }
        return false;
    }
}
