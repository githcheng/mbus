package cn.cjam.analye;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jam on 2016/6/17.
 */
public class ThreadTest {

    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args){

        for (int count=0; count<10;count++){

            long start = System.currentTimeMillis();
            for (int i=0;i<100000;i++){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
            long end = System.currentTimeMillis();
            String format = String.format("new cost time:%d ms", end - start);
            System.out.println(format);
        }


        for (int count=0; count<10;count++){

            long start = System.currentTimeMillis();
            for (int i=0;i<100000;i++){
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            long end = System.currentTimeMillis();
            String format = String.format("pol cost time:%d ms", end - start);
            System.out.println(format);
        }
    }
}
