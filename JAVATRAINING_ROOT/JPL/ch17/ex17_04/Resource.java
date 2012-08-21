/*
 * 練習問題17.4 p.406
 * 刈り取りスレッドを修正して、すべての割り当てられたリソースが開放されるまで、シャットダウンの後も
 * 生き続けるようにしなさい。
 */


package ch17.ex17_04;

public interface Resource
{
    void use(Object key, Object... args);
    void release();
}
