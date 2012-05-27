/*
 * 練習問題5.2 p.119
 * 口座に対する最後の10個の処理を記録するBankAccountを作成しなさい。
 * historyメソッドを追加して、Historyオブジェクトを返すようにしなさい。Historyオブジェクトは、nextメソッドで
 * Actionオブジェクトを１つ返して、リストの最後ではnullを返すようにしなさい。
 * Historyはネストしたクラスにすべきですか。ネストしたクラスにすべきなら、staticにすべきですか。
 */

package ch05.ex05_02;

public class BankAccount
{
    private long number;        // 口座番号
    private long balance;       // 現在の残高（単位は、セント）
    private Action lastAct;     // 最後に行われた処理
    private History lastHistory;

    private class History
    {
        private Action historyAction = null;
        private Action nextAction = null;
        private History nextHistory = null;

        public Action next()
        {
            return nextAction;
        }

        public History nextH()
        {
            return nextHistory;
        }

        History (Action preAction, Action currentAction, History preHistory)
        {
            nextAction = preAction;
            historyAction = currentAction;
            nextHistory = preHistory;
            if (lastHistory != null)
            {
                checkHistoryNumber(0, lastHistory);
            }
        }

        void checkHistoryNumber(int count, History hst)
        {
            if (count == 8)
            {
                hst.setNextNull();
            }
            else if (hst.next() != null)
            {
                checkHistoryNumber(count + 1, hst.nextH());
            }
        }

        void setNextNull()
        {
            this.nextAction = null;
            this.nextHistory = null;
        }

        Action getCurrentAction()
        {
            return historyAction;
        }
    }

    public History history()
    {
        return lastHistory;
    }

    public class Action
    {
        private String act;
        private long amount;
        Action(String act, long amount)
        {
            this.act = act;
            this.amount = amount;
        }
        public String toString()
        {
            // identify our enclosing account
            return number + ": " + act + " " + amount;
        }
    }

    public void deposit(long amount)
    {
        balance += amount;
        Action tmp = lastAct;
        lastAct = new Action("deposit", amount);
        lastHistory = new History(tmp, lastAct, lastHistory);
    }

    public void withdraw(long amount)
    {
        balance -= amount;
        Action tmp = lastAct;
        lastAct = new Action("withdraw", amount);
        lastHistory = new History(tmp, lastAct, lastHistory);
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        BankAccount test = new BankAccount();
        for (int i = 0; i < 6; i++)
        {
            test.deposit(1000 * (i + 1));
        }
        for (int i = 0; i < 6; i++)
        {
            test.withdraw(1000 * (i + 1));
        }
        System.out.println("Lastest Action          : " + test.history().getCurrentAction());
        System.out.println("Lastest Action - 1      : " + test.history().next());
        System.out.println("Lastest Action - 9.next : " + test.history().nextH().nextH().nextH().nextH().nextH().nextH().nextH().nextH().next());
        System.out.println("Lastest ACtion - 10.next: " + test.history().nextH().nextH().nextH().nextH().nextH().nextH().nextH().nextH().nextH().next());
    }

}
