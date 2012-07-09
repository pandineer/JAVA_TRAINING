package notifyAll;

public class ExThread5 {
    public static void main(String[] args) {
      CalculatePrice cp = new CalculatePrice();
      //オブジェクトcpを引数にスレッドオブジェクトppの生成
      PutPrice pp = new PutPrice(cp);
      //オブジェクトcpを引数にスレッドオブジェクトgpの生成
      GetPrice gp = new GetPrice(cp);

      pp.start();  //スレッドを実行可能状態にする
      gp.start();  //スレッドを実行可能状態にする
    }
  }