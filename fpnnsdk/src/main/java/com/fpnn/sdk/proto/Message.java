package com.fpnn.sdk.proto;

import android.util.Log;

import com.fpnn.sdk.ErrorRecorder;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * Created by shiwangxing on 2017/11/29.
 */

public class Message {

    protected Map payload;

    //-----------------[ Constructor Functions ]-------------------
    public Message() {
        payload = new TreeMap<String, Object>();
    }

    public Message(Map body) {
        payload = body;
    }

    //-----------------[ Properties Functions ]-------------------

    public Map getPayload() {
        return payload;
    }

    public void setPayload(Map p) {
        payload = p;
    }

    //-----------------[ Data Accessing Functions ]-------------------

    public void param(String key, Object value) {
        payload.put(key, value);
    }

    public Object get(String key) {
        return payload.get(key);
    }

    public Object get(String key, Object def) {
        Object o = payload.get(key);
        return (o != null) ? o : def;
    }

    public Object want(String key) throws NoSuchElementException {
        Object o = payload.get(key);
        if (o == null)
            throw new NoSuchElementException("Cannot found object for key: " + key);

        return o;
    }


    public String getString(String key) {
        Object o = get(key);
        return (o == null)? "" :String.valueOf(o);
    }


    public boolean getBoolean(String key) {
        Object obj = get(key);
        return (obj == null)? false :(Boolean)obj;
    }

    //-----------------[ To Bytes Array Functions ]-------------------
    public byte[] toByteArray() throws IOException {
        MessagePayloadPacker packer = new MessagePayloadPacker();
        packer.pack(payload);
        return packer.toByteArray();
    }

    public byte[] raw() throws IOException {
        return toByteArray();
    }
}
