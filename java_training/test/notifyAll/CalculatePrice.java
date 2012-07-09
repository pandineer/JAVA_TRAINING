package notifyAll;

class CalculatePrice {
    private int workArea;
    private boolean available = false;

    //synchronized指定。pricePutメソッドが終了するまで、
    //メソッドが属するオブジェクトはアクセスされない
    public synchronized void pricePut(int price) {
      while (available == true) {
        try {
          wait();  //availableがtrueの間、wait
        } catch (InterruptedException e) {
        }
      }
      //workAreaに値をセットする処理
      workArea = price;
      available = true;
      //availableにtrueを代入した後wait状態のスレッドを解除
      notifyAll();
    }

    //synchronized指定。priceGetメソッドが終了するまで、
    //メソッドが属するオブジェクトはアクセスされない
    public synchronized void priceGet() {
      while (available == false) {
        try {
          wait();  //availableがfalseの間、wait
        } catch (InterruptedException e) {
        }
      }
      //workAreaから値を抜出す処理
      System.out.println("課税後価格は" +
                          workArea * 1.05 + "円です。");
      available = false;
      //availableにfalseを代入した後wait状態のスレッドを解除
      notifyAll();
    }
  }