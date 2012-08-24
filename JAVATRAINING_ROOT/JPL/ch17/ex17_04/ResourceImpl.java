/*
 * 練習問題17.4 p.406
 * 刈り取りスレッドを修正して、すべての割り当てられたリソースが開放されるまで、シャットダウンの後も
 * 生き続けるようにしなさい。
 */

package ch17.ex17_04;

public class ResourceImpl implements Resource
{
    int keyHash;
    boolean needsRelease = false;

    ResourceImpl(Object key)
    {
        keyHash = System.identityHashCode(key);

        // .. 外部リソースの設定

        needsRelease = true;
    }

    public void use(Object key, Object... args)
    {
        if (System.identityHashCode(key) != keyHash)
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
