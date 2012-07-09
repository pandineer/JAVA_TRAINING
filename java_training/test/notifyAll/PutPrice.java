package notifyAll;

class PutPrice extends Thread {
    private CalculatePrice cp;
    private int[ ] price = {100, 200, 300, 400, 500};

    //コンストラクタ内で引数に指定されたオブジェクトcpを代入
    public PutPrice(CalculatePrice cp) {
      this.cp = cp;
    }

    public void run() {
      for (int i = 0; i < 5; i++) {
        //オブジェクトcpのpricePutメソッドを実行
        cp.pricePut(price[i]);
        try {
          sleep((int)(Math.random() * 100));
        } catch (InterruptedException e) {
        }
      }
    }
  }