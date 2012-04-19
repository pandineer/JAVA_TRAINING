package ex01_15;

public interface Lookup {
	/** name と関連付けされた値を返す。
	 * そのような値がなければnullを返す
	 */
	Object find(String name);

}
