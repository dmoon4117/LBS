package khj.kdh.com.lbs;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.button1); //서비스 종료
        Button b2 = (Button) findViewById(R.id.button2); //서비스 시작

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "액티비티 전환", Toast.LENGTH_LONG).show();

                // 액티비티 전환 코드
                Intent intent = new Intent(getApplicationContext(), map.class);
                startActivity(intent);
            }
        });

        b1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                // 서비스 시작하기
                Log.d("test", "액티비티-서비스 시작버튼클릭");
                Intent intent = new Intent(
                        getApplicationContext(),//현재제어권자
                        MyService.class); // 이동할 컴포넌트
                startService(intent); // 서비스 시작
            }
        });

        b2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                // 서비스 종료하기
                Log.d("test", "액티비티-서비스 종료버튼클릭");
                Intent intent = new Intent(
                        getApplicationContext(),//현재제어권자
                        MyService.class); // 이동할 컴포넌트
                stopService(intent); // 서비스 종료
            }
        });

        myThread thread = new myThread();
        //getServiceList();
        thread.setDaemon(true);
        thread.start();
    }

    void getServiceList()	{

        ActivityManager activity_manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningServiceInfo> service_info = activity_manager.getRunningServices(100);

        for(int i=0; i<service_info.size(); i++) {
            Log.v("service", "Service: " + service_info.get(i).service.getPackageName() + ", className: " + service_info.get(i).service.getClassName());

            Log.v("pid,uid", "PID/UID: " + service_info.get(i).pid + "/" + service_info.get(i).uid + "  process: " + service_info.get(i).process);
        }

    }

    class myThread extends Thread{
        public void run() {
            while (true) {
                getServiceList();
            }
        }
    }

}