package com.whu.ybatis.springMapper.map;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 提供默认小写作为Key的Map
 * Created by hulichao on 2017/12/22
 */
@SuppressWarnings("rawtypes")
public class YBatisLinkedMap extends LinkedHashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    private final Locale locale;

    public YBatisLinkedMap() {
        this(((Locale) (null)));
    }

    public YBatisLinkedMap(int initialCapacity) {
        this(initialCapacity, null);
    }

    public YBatisLinkedMap(int initialCapacity, Locale locale) {
        super(initialCapacity);
        this.locale = locale == null ? Locale.getDefault() : locale;
    }

    public YBatisLinkedMap(Locale locale) {
        this.locale = locale == null ? Locale.getDefault() : locale;
    }

    public void clear() {
        super.clear();
    }

    public boolean containsKey(Object key) {
        return (key instanceof String)
                && super.containsKey(convertKey((String) key));
    }

    protected String convertKey(String key) {
        return key.toLowerCase(locale);
    }

    public Object get(Object key) {
        if (key instanceof String)
            return super.get(convertKey((String) key));
        else
            return null;
    }

    public Object put(String key, Object value) {
        return super.put(convertKey(key), value);
    }

    public void putAll(Map map) {
        if (map.isEmpty())
            return;
        java.util.Map.Entry entry;
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); put(
                convertKey((String) entry.getKey()), entry.getValue()))
            entry = (java.util.Map.Entry) iterator.next();

    }

    public Object remove(Object key) {
        if (key instanceof String)
            return super.remove(convertKey((String) key));
        else
            return null;
    }
}
