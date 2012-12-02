/*
 * 練習問題17.4 p.406
 * 刈り取りスレッドを修正して、すべての割り当てられたリソースが解放されるまで、シャットダウンの後も
 * 生き続けるようにしなさい。
 */

/*
 * 練習問題17.3 p.406
 * ハッシュコードを使用する代わりに、キーを管理することで参照オブジェクトを使用するように、
 * リソース実装クラスを書き直しなさい。
 */

package ch17.ex17_05;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager
{
    final ReferenceQueue<Object> queue;
    final Map<Reference<?>, Resource> refs;
    // final Thread reaper;
    boolean shutdown = false;

    public ResourceManager()
    {
        queue = new ReferenceQueue<Object>();
        refs = new HashMap<Reference<?>, Resource>();
        // reaper = new ReaperThread();
        // reaper.start();

        // ... リソースの初期化 ...
    }

    // shutdown実行時に、すべてのリソースを刈り取る
    public synchronized void shutdown()
    {
        if (!shutdown)
        {
            shutdown = true;
            // reaper.interrupt();

            while(refs.size() != 0)
            {
                try
                {
                    Reference<?> ref = queue.remove();
                    Resource res = null;
                    synchronized(ResourceManager.this)
                    {
                        res = refs.get(ref);
                        refs.remove(ref);
                    }
                    res.release();
                    ref.clear();
                }
                catch (InterruptedException ex)
                {
                    System.out.println(ex);
                }
            }
        }
    }

    public synchronized Resource getResource(Object key)
    {
        if (shutdown)
        {
            throw new IllegalStateException();
        }
        Resource res = new ResourceImpl(key);
        Reference<?> ref = new PhantomReference<Object>(key, queue);
        refs.put(ref, res);
        return res;
    }

    private static class ResourceImpl implements Resource
    {
        // int keyHash;
        SoftReference<Object> implKey;

        boolean needsRelease = false;

        ResourceImpl(Object key)
        {
            // keyHash = System.identityHashCode(key);
            implKey = new SoftReference<Object>(key);

            // .. 外部リソースの設定

            needsRelease = true;
        }

        public void use(Object key, Object... args)
        {
            // if (System.identityHashCode(key) != keyHash)
            if (!implKey.get().equals(key))
            {
                throw new IllegalArgumentException("wrong key");
            }
            // ... リソースの使用 ...
            // System.out.println(key.toString() + " is used. ");
        }

        public synchronized void release()
        {
            if (needsRelease)
            {
                needsRelease = false;

                // ... リソースの解放 ...
                implKey.clear();
            }
        }
    }


    class ReaperThread extends Thread
    {
        public void run()
        {
            boolean threadShutdown = false;
            // 割り込まれるまで実行
            while(true)
            {
                try
                {
                    Reference<?> ref = queue.remove();
                    Resource res = null;
                    synchronized(ResourceManager.this)
                    {
                        res = refs.get(ref);
                        refs.remove(ref);
                    }
                    res.release();
                    ref.clear();
                }
                catch (InterruptedException ex)
                {
                    // break; // すべて終了
                    threadShutdown = true;
                    if (refs.size() == 0)
                    {
                        System.out.println("all resouces are released. ");
                        Runtime.getRuntime().gc();
                        System.out.println("Shutdown finished: " + Runtime.getRuntime().freeMemory());
                        return;
                    }
                    else
                    {
                        System.out.println("waiting all resources are released. ");
                    }
                }
                if (refs.size() == 0 && threadShutdown)
                {
                    System.out.println("all resouces are released. ");
                    Runtime.getRuntime().gc();
                    System.out.println("Shutdown finished: " + Runtime.getRuntime().freeMemory());
                    return;
                }
            }
        }
    }

    public static void main(String[] args)
    {
        Runtime.getRuntime().gc();
        System.out.println("First memory state: " + Runtime.getRuntime().freeMemory());


        ResourceManager test = new ResourceManager();
        Resource[] resources = new Resource[10000];

        for (int i = 0; i < resources.length; i++)
        {
            String tmp = new String("Sample resouce " + i);

            resources[i] = test.getResource(tmp);
            resources[i].use(tmp, (Object[])null);
        }

        System.out.println("10000 resources are used: " + Runtime.getRuntime().freeMemory());

        for (int i = 0; i < resources.length; i++)
        {
            resources[i].release();
        }

        Runtime.getRuntime().gc();
        System.out.println("Half resources are released: " + Runtime.getRuntime().freeMemory());

        test.shutdown();

        System.out.println("Shutdown ResourceManager");


        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        System.out.println("Rest resources are released: " + Runtime.getRuntime().freeMemory());
    }
}
