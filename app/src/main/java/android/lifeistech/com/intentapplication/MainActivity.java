package android.lifeistech.com.intentapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelUuid;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_CODE_MAP = 1;
    static final int REQUEST_CODE_MAIL = 2;
    static final int REQUEST_CODE_CAMERA = 3;
    static final int REQUEST_CODE_GALLERY = 4;

    ImageView imageView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
        editText = (EditText)findViewById(R.id.editText);
        editText.setText("東京都港区南麻布2-12-3");

    }

    public void map (View v) {
        String location = "geo:0.0?q=" + editText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(location));
        startActivityForResult(intent, REQUEST_CODE_MAP);
    }

    public void gallery (View v) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        android.provider..MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        startActivityForResult(intent, REQUEST_CODE_GALLERY);
    }

    public void mail (View v) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:life.is.tech@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "無題");
        intent.putExtra(Intent.EXTRA_PROCESS_TEXT_READONLY, "本文");
        startActivityForResult(intent, REQUEST_CODE_MAIL);
    }

    public void app (View v) {
        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage("com.android.vending");
        startActivity(intent);
    }

    public void camera (View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {
            if(requestCode == REQUEST_CODE_GALLERY) {

                try {

                    InputStream inputStream = getContentResolver().openInputStream(intent.getData());
                    Bitmap bmp = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                    imageView.setImageBitmap(bmp);

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "エラー", Toast.LENGTH_LONG).show();

                }


            } else if {

                Bitmap bmp = (Bitmap) intent.getExtras().get("data");
                imageView.setImageBitmap(bmp);


            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(MainActivity.this, "CANCEL", Toast.LENGTH_LONG).show();
        }
    }

}
