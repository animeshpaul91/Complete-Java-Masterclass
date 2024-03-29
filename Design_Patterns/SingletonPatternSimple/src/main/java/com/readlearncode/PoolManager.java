package com.readlearncode;

import javax.ejb.Singleton;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@Singleton
public class PoolManager {

    private final Queue<Object> pooledObjects = new LinkedBlockingQueue<>(1_000);

    private PoolManager() {
        for (int i = 0; i <= 1_000; i++) {
            pooledObjects.offer(new Object());
        }
    }

    public void returnObject(Object obj) {
        pooledObjects.offer(obj);
    }

    public Object borrowObject() {
        return pooledObjects.poll();
    }
}
