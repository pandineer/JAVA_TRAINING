/*
 * 練習問題17.3 p.406
 * ハッシュコードを使用する代わりに、キーを管理することで参照オブジェクトを使用するように、
 * リソース実装クラスを書き直しなさい。
 */

package ch17.ex17_03;

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
    final Thread reaper;
    boolean shutdown = false;

    public ResourceManager()
    {
        queue = new ReferenceQueue<Object>();
        refs = new HashMap<Reference<?>, Resource>();
        reaper = new ReaperThread();
        reaper.start();

        // ... リソースの初期化 ...
    }

    public synchronized void shutdown()
    {
        if (!shutdown)
        {
            shutdown = true;
            reaper.interrupt();
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
        SoftReference<Object> implKey; // Referenceオブジェクトがキーへの強い参照を保持していないことが重要
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
            if (implKey.equals(key))
            {
                throw new IllegalArgumentException("wrong key");
            }
            // ... リソースの使用 ...
        }

        public synchronized void release()
        {
            if (needsRelease)
            {
                needsRelease = false;

                // ... リソースの解放 ...
            }
        }
    }


    class ReaperThread extends Thread
    {
        public void run()
        {
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
                    break; // すべて終了
                }
            }
        }
    }

    public static void main(String[] args)
    {
        // テスト…？
    }
}
