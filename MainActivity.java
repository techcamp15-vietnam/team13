package com.example.test;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressLint("WrongCall")
public class MainActivity extends Activity {
	
	private	Button showButton;
	private ImageView imgView;
	private Button CapButton;
	private int count =0;
	private FaceDetector.Face[] faces;
	private Bitmap tmp;
	private PointF tmp_point = new PointF();
	private Paint tmp_paint = new Paint();


	
    @Override
    /* create and setOnClickListener 
     * @author Nguyen Tien Thanh */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showButton=(Button)findViewById(R.id.show_button);
        imgView = (ImageView)findViewById(R.id.imgView);
        CapButton = (Button)findViewById(R.id.cap_button);
        showButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//imgView.setVisibility(View.VISIBLE);
				CapButton.setVisibility(View.VISIBLE);
				showButton.setVisibility(View.GONE);
			}
		});
        CapButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imgView.setVisibility(View.VISIBLE);
				onCam();
			}
		});
    }
    /* turn on camera
     * @author Nguyen Tien Thanh */
    private void onCam()
    {
    	Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
    	intent.putExtra(MediaStore.EXTRA_OUTPUT, MainActivity.getPhoto());
    	startActivityForResult(intent, 0);
    }
    /*take and save a image
     * @author Nguyen Tien Thanh*/
    private static Uri  getPhoto()
    {
    	File root = Environment.getExternalStorageDirectory();
    	File tmp = new File(root.getAbsolutePath()+"/tmp.jpg");
    	try{
    		if(!tmp.exists())
    		{
    			tmp.createNewFile();
    		}
    		Uri temPU = Uri.fromFile(tmp);
    		return temPU;
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		return Uri.EMPTY;
    	}
    }
    
    /*open a image and detect face
     * @author Nguyen Tien Thanh*/
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode != RESULT_OK)
		{
			return;
		}
		String imgPath = Environment.getExternalStorageDirectory()+ File.separator +"tmp.jpg";
		BitmapFactory.Options bitmap_options = new BitmapFactory.Options();
		bitmap_options.inPreferredConfig = Bitmap.Config.RGB_565;
		tmp = BitmapFactory.decodeFile(imgPath, bitmap_options);
		if(tmp != null)
		{
			FaceDetector face_detector = new FaceDetector(tmp.getWidth(), tmp.getHeight(), 20);
			faces = new FaceDetector.Face[20];
			count = face_detector.findFaces(tmp, faces);
			Toast.makeText(getApplicationContext(), "Face Count: " + String.valueOf(count), Toast.LENGTH_SHORT).show();
			//setContentView(new Face_Detection_View(this));
			imgView.setImageBitmap(tmp);
			//imgView.setContentDescription(imgPath);
		}
	}
    
    private void DrawFace()
    {
    	
    }
    /*
    // draw a circle on face 
    class Face_Detection_View extends View{
    public Face_Detection_View(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			
		}
	public void onDraw(Canvas canvas)
    {
    	canvas.drawBitmap(tmp, 0, 0, null);
    	for(int i=0;i<count;i++)
    	{
    		FaceDetector.Face face = faces[i];
    		tmp_paint.setColor(Color.RED);
    		tmp_paint.setAlpha(100);
    		face.getMidPoint(tmp_point);
    		canvas.drawCircle(tmp_point.x, tmp_point.y, face.eyesDistance(), tmp_paint);
    		
    	}
    	
    }
    }
    */

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    
}
