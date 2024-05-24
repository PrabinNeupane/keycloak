package org.example;
//public class simpleThread{
//
//    public static void main(String[] args) {
//        Runnable runnable = () -> {
//            String ThreadName = Thread.currentThread().getName();
//            System.out.println(ThreadName + "running");
//          try{
//              Thread.sleep(2000);
//            } catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            System.out.println(ThreadName + "completed");
//        };
//
//        Thread thread = new Thread(runnable, "The Thread");
//        thread.start();
//
//    }
//
//}


import java.util.Comparator;

public class simpleThread {
//    private boolean stopRequested = false;
//
//    public synchronized void requestStop(){
//        this.stopRequested = true;
//    }
//
//    private void sleep(long millis){
//        try{
//            Thread.sleep(millis);
//        }catch(InterruptedException e){
//            e.printStackTrace();
//        }
//    }

//    @Override
//    public void run(){
//        System.out.println("StopabbleRunnable Running");
//        while(!stopRequested){
//            System.out.println(".................");
//        }
//        System.out.println("StopabbleRunnable Stopped");
//    }

    public static void main(String[] args) {
//        simpleThread stoppable = new simpleThread();
//        Thread thread = new Thread(stoppable);
//        thread.start();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Requesting Stop");
//        stoppable.requestStop();
//        System.out.println("Stop Requested");
//
////
//        Thread vThread = Thread.ofVirtual().start(runnable);
//

        Comparator<String> stringCOmparator = new Comparator<String>() {
            @Override
            public int compare(String t2, String t1) {
                return t2.compareTo(t1);
            }
        };

       int compare = stringCOmparator.compare("Hello", "Wow");
        System.out.println(compare);


        Comparator<String> stringCOmparator2 = (String t2, String t1) -> t2.compareTo(t1);
        int compare2 = stringCOmparator2.compare("Hello", "Wow");
        System.out.println(compare2);
    }
}