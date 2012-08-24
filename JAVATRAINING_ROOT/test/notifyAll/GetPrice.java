package notifyAll;

class GetPrice extends Thread {
    private CalculatePrice cp;
    //コンストラクタ内で引数に指定されたオブジェクトcpを代入
    public GetPrice(CalculatePrice cp) {
      this.cp = cp;
    }

    public void run() {
      for (int i = 0; i < 5; i++) {
        //オブジェクトcpのpriceGetメソッドを実行
        cp.priceGet();
      }
    }
  }