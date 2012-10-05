/*
 * 練習問題17.3 p.406
 * ハッシュコードを使用する代わりに、キーを管理することで参照オブジェクトを使用するように、
 * リソース実装クラスを書き直しなさい。
 */

package ch17.ex17_03;

interface Resource
{
    void use(Object key, Object... args);
    void release();
}
