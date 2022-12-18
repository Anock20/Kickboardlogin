package kr.ac.cnu.computer.kickboardlogin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Camera  extends AppCompatActivity {

    Bitmap bitmap;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        imageView = findViewById(R.id.imageView);

        Button picBtn = findViewById(R.id.pic_btn);
        picBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int permissionCheck = ContextCompat.checkSelfPermission(Camera.this, Manifest.permission.CAMERA);
                if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                    // 권한 없음
                    ActivityCompat.requestPermissions(Camera.this, new String[]{Manifest.permission.CAMERA}, 0);
                    Toast.makeText(getApplicationContext(),"권한없음",Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    activityResultPicture.launch(intent);
                }
            }
        });
        Button next = findViewById(R.id.next_btn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Camera.this, "헬멧을 착용해주세요", Toast.LENGTH_SHORT).show();


            }
        });

    }//onCreate

    ActivityResultLauncher<Intent> activityResultPicture = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    //결과 OK , 데이터 null 아니면
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                        Bundle extras = result.getData().getExtras();

                        bitmap = (Bitmap) extras.get("data");

                        imageView.setImageBitmap(bitmap);
                        Button next = findViewById(R.id.next_btn);
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Camera.this, APP.class);
                                startActivity(intent);

                            }
                        });
                    }
                }
            });

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults[0] == 0) {
                Toast.makeText(this, "카메라 권한이 승인됨", Toast.LENGTH_SHORT).show();
            } else {
                //권한 거절된 경우
                Toast.makeText(this, "카메라 권한이 거절 되었습니다.카메라를 이용하려면 권한을 승낙하여야 합니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }//MainActivity
}