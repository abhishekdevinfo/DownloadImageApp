package com.example.root.downloadimageapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView downloadedImage;

    public void downloadImage(View view) {
        // http://images4.fanpop.com/image/photos/20900000/Tron-Legacy-tron-legacy-20911253-1920-1080.jpg

        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;

        try {
            myImage = task.execute("http://images4.fanpop.com/image/photos/20900000/Tron-Legacy-tron-legacy-20911253-1920-1080.jpg").get();
            downloadedImage.setImageBitmap(myImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("Button Pressed", "Hi there");
        
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadedImage = findViewById(R.id.imageView);

    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {

            Bitmap myBitmap;
            URL url;
            HttpURLConnection urlConnection;

            try {

                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream(); // Download full image
                myBitmap = BitmapFactory.decodeStream(inputStream); // convert the data that we downloaded in an image
                return myBitmap;

            }
             catch (MalformedURLException e) {
                e.printStackTrace();
             } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }
    }
}
