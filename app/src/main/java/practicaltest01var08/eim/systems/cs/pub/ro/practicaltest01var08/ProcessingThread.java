package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

/**
 * Created by Denis on 02/04/2018.
 */

class ProcessingThread extends Thread {

    private Context context = null;
    private String message = null;
    private boolean isRunning = true;

    Random random = new Random();

    public ProcessingThread(Context context, String correct) {
        this.context = context;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int index = random.nextInt() % (correct.length() + 1);
        while (i < correct.length()) {
            if (i != index) {
                sb.append("*");
            } else {
                sb.append(correct.charAt(i));
//                sb.append(correct.toCharArray()[index]);
            }
           i++;
        }
        message = sb.toString();

    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("stars");
        intent.putExtra("message", message);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
