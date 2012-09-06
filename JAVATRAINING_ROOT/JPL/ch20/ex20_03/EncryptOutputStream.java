/*
 * 練習問題20.3 p.454
 * バイトを何らかの値とXORするなど、どのようなアルゴリズムでも良いので、
 * バイトを暗号化する一組のFilterストリームクラスであるDecryptInputStreamとEncryptOutputStreamを
 * 作成しなさい。
 * DecryptInputStreamは、EncryptOutputStreamクラスが生成したバイトを復号化します。
 */

package ch20.ex20_03;

import java.io.FilterWriter;
import java.io.Writer;

public class EncryptOutputStream extends FilterWriter
{
    protected EncryptOutputStream(Writer out)
    {
        super(out);
    }

    public write
}
