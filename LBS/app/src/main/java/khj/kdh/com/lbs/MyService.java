package khj.kdh.com.lbs;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    MediaPlayer mp;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate(){
        Log.d("Myservice", "onCreate() call");
        mp = MediaPlayer.create(this,R.raw.testmp3);
        mp.setLooping(false);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d("Myservice", "onStartCommand() call");
        mp.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy(){
        Log.d("Myservice", "onDestroy() call");
        mp.stop();
        super.onDestroy();
    }
}
