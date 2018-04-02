package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var08Service extends Service {

    ProcessingThread processingThread;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String correct = intent.getStringExtra("answer");

        processingThread = new ProcessingThread(this, correct);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
