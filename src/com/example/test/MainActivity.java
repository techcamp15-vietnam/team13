package com.example.test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

//@SuppressLint("WrongCall")
public class MainActivity extends Activity implements OnClickListener{
	
	//private	Button showButton;
	private ImageView imgView;
	//private Button CapButton;
	private int count =0;
	
	private Bitmap tmp;
	private PointF tmp_point = new PointF();
	//private Paint tmp_paint = new Paint();
	private Button editButton,effectButton,rainButton,snowButton,antButton,lightButton;
	Bitmap boder2=null;
	
	
	
    @Override
    /* create and setOnClickListener 
     * @author Nguyen Tien Thanh */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //showButton=(Button)findViewById(R.id.show_button);
        imgView = (ImageView)findViewById(R.id.imgView);
        //CapButton = (Button)findViewById(R.id.cap_button);
        editButton=(Button)findViewById(R.id.button1);
    	effectButton=(Button)findViewById(R.id.button2);
    	rainButton=(Button)findViewById(R.id.Rain);
    	snowButton=(Button)findViewById(R.id.snow);
    	antButton=(Button)findViewById(R.id.ant);
    	lightButton=(Button)findViewById(R.id.light);
    	editButton.setOnClickListener(this);
    	effectButton.setOnClickListener(this);
    	onCam();
        /*
    	showButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//imgView.setVisibility(View.VISIBLE);
				CapButton.setVisibility(View.VISIBLE);
				showButton.setVisibility(View.GONE);
			}
		});
        */
    	
        /*CapButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imgView.setVisibility(View.VISIBLE);
				onCam();
			}
		});
		*/
    }
    
    /* turn on camera
     * @author Nguyen Tien Thanh */
    private void onCam()
    {
    	tmp= null;
    	boder2= null;
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
    
    /*take a image
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
		//edit();
		imgView.setImageBitmap(tmp);
	}
    /*detect faces
     * @author Nguyen Tien Thanh*/
    private void edit()
    {
    	FaceDetector.Face[] faces;
    	//String imgPath = Environment.getExternalStorageDirectory()+ File.separator +"tmp.jpg";
		//BitmapFactory.Options bitmap_options = new BitmapFactory.Options();
		//bitmap_options.inPreferredConfig = Bitmap.Config.RGB_565;
		//tmp = BitmapFactory.decodeFile(imgPath, bitmap_options);
		if(tmp != null)
		{
			FaceDetector face_detector = new FaceDetector(tmp.getWidth(), tmp.getHeight(), 50);
			faces = new FaceDetector.Face[50];
			count = face_detector.findFaces(tmp, faces);
			Toast.makeText(getApplicationContext(), "Face Count: " + String.valueOf(count), Toast.LENGTH_SHORT).show();
			//setContentView(new Face_Detection_View(this));
			//scaledBorder=BitmapFactory.decodeResource(getResources(), R.drawable.images0);
			boder2=overlay(tmp,faces);
			imgView.setImageBitmap(boder2);
			
			//Toast.makeText(getApplicationContext(), (int) (tmp_point.x + tmp_point.y), Toast.LENGTH_SHORT).show();
			//imgView.setContentDescription(imgPath);
		}
    }
    
    /*insert face into mage
     * @author Dao Hong Thuan
     * @author Nguyen Tien Thanh*/
    private Bitmap overlay(Bitmap bmp1,FaceDetector.Face[] faces) {
    	int a=0;
    	Bitmap[] scaledBorder = new Bitmap[10];
    	scaledBorder[0]=BitmapFactory.decodeResource(getResources(), R.drawable.images0);
    	scaledBorder[1]=BitmapFactory.decodeResource(getResources(), R.drawable.images1);
    	scaledBorder[2]=BitmapFactory.decodeResource(getResources(), R.drawable.images2);
    	scaledBorder[3]=BitmapFactory.decodeResource(getResources(), R.drawable.images3);
    	scaledBorder[4]=BitmapFactory.decodeResource(getResources(), R.drawable.images4);
    	scaledBorder[5]=BitmapFactory.decodeResource(getResources(), R.drawable.images5);
    	scaledBorder[6]=BitmapFactory.decodeResource(getResources(), R.drawable.images6);
    	scaledBorder[7]=BitmapFactory.decodeResource(getResources(), R.drawable.images7);
    	scaledBorder[8]=BitmapFactory.decodeResource(getResources(), R.drawable.images8);
    	Random randomno = new Random();
    	//scaledBorder[9]=BitmapFactory.decodeResource(getResources(), R.drawable.images9);
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(),bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, 0, 0, null);
        for(int i=0;i<count;i++)
    	{
    		FaceDetector.Face face = faces[i];
    		face.getMidPoint(tmp_point);
    		a = (int) face.eyesDistance();
    		
    		Bitmap resize = Bitmap.createScaledBitmap(scaledBorder[randomno.nextInt(9)],(int)(4*a), (int)(4.5*a), false);
    		canvas.drawBitmap(resize ,(int)(tmp_point.x -2*a) ,(int)(tmp_point.y -2.6 *a), null);
    	}
        return bmOverlay;
    }
    /*save image
     * @author Dao Hong Thuan*/
    private boolean storeImage(Bitmap imageData, String filename) {
		//get path to external storage (SD card)
		String iconsStoragePath = Environment.getExternalStorageDirectory() + "/myAppDir/myimages1";
		File sdIconStorageDir = new File(iconsStoragePath);
		//create storage directories, if they don't exist
		sdIconStorageDir.mkdirs();
		
		try {
			String filePath = sdIconStorageDir.toString() + filename;
			FileOutputStream fileOutputStream = new FileOutputStream(filePath);

			BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);

			//choose another format if PNG doesn't suit you
			imageData.compress(CompressFormat.PNG, 100, bos);
			bos.flush();
			bos.close();
            
		} catch (FileNotFoundException e) {
			Log.w("TAG", "Error saving image file: " + e.getMessage());
			Toast.makeText(getBaseContext(), "THE END", Toast.LENGTH_SHORT).show();

			return false;
		} catch (IOException e) {
			Log.w("TAG", "Error saving image file: " + e.getMessage());
			Toast.makeText(getBaseContext(), "THE END", Toast.LENGTH_SHORT).show();

			return false;
		}
		 
		return true;
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
    /*
     * create menu
     * @author Dao Hong Thuan
     * @author Khong Minh Tri*/
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.main, menu);
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	/*
     * option select item
     * @author Dao Hong Thuan*/
	public boolean onOptionsItemSelected(MenuItem item){
		Bitmap tmp2;
		switch(item.getItemId()){
		   case R.id.save:{
			   Toast.makeText(getBaseContext(), "saved",Toast.LENGTH_SHORT).show();
			   storeImage(boder2, SystemClock.currentThreadTimeMillis()+".png");
			   break;
		   }
		   case R.id.reset:{
			   String imgPath = Environment.getExternalStorageDirectory()+ File.separator +"tmp.jpg";
				BitmapFactory.Options bitmap_options = new BitmapFactory.Options();
				bitmap_options.inPreferredConfig = Bitmap.Config.RGB_565;
				tmp2 = BitmapFactory.decodeFile(imgPath, bitmap_options);
				imgView.setImageBitmap(tmp2);
			   break;
		   }
		}
		return false;
	}
	@Override
	/*get key 
	 * @author Dao Hong Thuan
	 * @author Khong Minh Tri*/
public boolean onKeyDown(int keyCode,KeyEvent event){
	if((keyCode==KeyEvent.KEYCODE_BACK)){
		onCam();
	}
	return false;
	
}
	public void onClick(View v) {
		// TODO Auto-generated method stub
     switch(v.getId()){
     case R.id.button1:{
    	 edit();
    	 break;
     }
	  case R.id.button2:{
		    editButton.setVisibility(View.INVISIBLE);
		    effectButton.setVisibility(View.INVISIBLE);
		    rainButton.setVisibility(View.VISIBLE);
		    snowButton.setVisibility(View.VISIBLE);
		    antButton.setVisibility(View.VISIBLE);
		    lightButton.setVisibility(View.VISIBLE);
		    break;
	   }
	  
	}
  }

}
