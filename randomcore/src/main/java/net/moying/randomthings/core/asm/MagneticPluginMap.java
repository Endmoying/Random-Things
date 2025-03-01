package net.moying.randomthings.core.asm;

import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class MagneticPluginMap<K, V> extends TreeMap<K, V> {

    private void check() {
        if (!super.containsKey("MagneticPlugin") || !super.containsValue(MagneticPlugin.INSTANCE)) {
            super.put((K) "MagneticPlugin", (V) MagneticPlugin.INSTANCE);
        }
    }

    @Override
    public int size() {
        check();
        return super.size();
    }

    public V get(Object key) {
        check();
        return super.get(key);
    }

    public boolean containsKey(Object key) {
        check();
        return super.containsKey(key);
    }

    @Override
    public V remove(Object key) {
        check();
        if (key.equals("MagneticPlugin")) {
            return null;
        }
        return super.remove(key);
    }

    @Override
    public void clear() {
        super.clear();
        check();
    }

    @Override
    public boolean containsValue(Object value) {
        check();
        return super.containsValue(value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        check();
        return super.remove(key, value);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        check();
        return super.replace(key, oldValue, newValue);
    }

    @Override
    public V replace(K key, V value) {
        check();
        return super.replace(key, value);
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        check();
        super.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        super.replaceAll(function);
        check();
    }
}
