package io.zgc.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceSample {
    public static void main(String[] args) {
        Object obj = new Object();

        SoftReference<Object> objectSoftReference = new SoftReference<Object>(new Object());

        WeakReference<Object> objectWeakReference = new WeakReference<Object>(new Object());

        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> objectPhantomReference = new PhantomReference<>(new Object(),referenceQueue);
    }
}
