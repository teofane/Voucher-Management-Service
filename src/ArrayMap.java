import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

public class ArrayMap<K, V> extends AbstractMap<K, V> {
    private K[] keys;
    private V[] values;
    int size;

    public ArrayMap() {
        keys = (K[]) new Object[100000];
        values = (V[]) new Object[100000];
        size = 0;
    }

    private int keyIndex(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public boolean containsKey(Object key) {
        int index = keyIndex((K)key);

        return index > -1;
    }

    public V put(K key, V value) {
        int index = keyIndex(key);

        if (index == -1) { // the key doesn't exist, creates new element
            keys[size] = key;
            values[size] = value;
            size ++;
        }
        else
        values[index] = value;//the key exists, so it replaces the value

        return value;
    }

    public V get(Object key) {
        int index = keyIndex((K)key);
        return values[index];
    }

    public int size() {
        return size;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    class ArrayMapEntry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        public ArrayMapEntry(K key, V value) {

            this.key = key;
            this.value = value;
        }

        public K getKey() {
        return key;
        }

        public V getValue(){
        return value;
        }

        public V setValue(V value) {
        this.value = value;
        return value;
        }

        public String toString() {
        return this.value.toString();
        }

        public boolean equals(Object o) {
        ArrayMapEntry<K, V> aux = (ArrayMapEntry<K, V>) o;

        if(this.value.equals(aux.value) && this.key.equals(aux.key))
            return true;
        else
            return false;
        }

        public int hashCode() {
            return this.key.hashCode();
        }

    }
}
