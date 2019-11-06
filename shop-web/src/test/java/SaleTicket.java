public class SaleTicket implements Runnable {

    int total = 100;

    @Override
    public void run() {

        while (total > 0) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {

            }
            sale();
        }
    }

    public synchronized void sale() {
        if (total > 0) {
            System.out.println(Thread.currentThread().getName() + ",出售 第" + (100 - total + 1) + "张票.");
            total--;
        }
    }
}