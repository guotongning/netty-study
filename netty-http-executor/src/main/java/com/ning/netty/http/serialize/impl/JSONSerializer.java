package com.ning.netty.http.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.ning.netty.http.serialize.Serializer;

/**
 * @author nicholas
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
