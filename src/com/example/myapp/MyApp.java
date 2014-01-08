package com.example.myapp;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import android.media.FaceDetector;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
/*
  */
public class MyApp extends Activity implements OnClickListener{
	    AnimationDrawable animation;
	    private Button editButton,effectButton,saveButton,autoButton,controlButton,edit2Button;
	    private Spinner editSpinner,gifSpinner;
	    private ImageView img1,img2; 
	    private Bitmap bmp1,bmp2;
	    private int count =0;
	    private Bitmap tmp;
	    Bitmap boder2=null;
	    private PointF tmp_point = new PointF();
		
	    int img_list[]={
	        	R.drawable.ic_launcher,
	        	R.drawable.bg_button
	    };
	    String arr[]={
	    		 "           Rain",
	    		 "           Light",
	    		 "           Wind",
	    		 "           Clouds",
	    		 "           Hammer"};
	    String arr1[]={
	    		"        Face1","        Face2","        Face3","        Face7",
	    	    "        Face4","        Face5","        Face6","        Face8"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_app);
		//bmp1 = BitmapFactory.decodeResource(getResources(), R.drawable.bg_button);
		init();
		ArrayAdapter<String> adapterFace=new ArrayAdapter<String>(
				 this,android.R.layout.simple_spinner_item,arr1);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(
	    this,android.R.layout.simple_spinner_item,arr);
		adapterFace.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		editSpinner.setAdapter(adapterFace);
		gifSpinner.setAdapter(adapter);
		editSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			/*select face
			 * @author Khong Minh tri*/
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if("        Face1".equals(arr1[arg2])){
					
				}
				if("        Face2".equals(arr1[arg2])){
					Toast.makeText(getBaseContext(),arr1[arg2], Toast.LENGTH_SHORT).show();
					 img2.setImageResource(img_list[arg2]);
				}
				if("        Face3".equals(arr1[arg2])){
					
				}
				if("        Face4".equals(arr1[arg2])){
					
				}
                if("        Face5".equals(arr1[arg2])){
					
				}
                if("        Face6".equals(arr1[arg2])){
					
				}
                if("        Face7".equals(arr1[arg2])){
					
				}
                if("        Face8".equals(arr1[arg2])){
					
				}
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		/*select image
		 * @author Dao Hong Thuan*/
		gifSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
               if("           Rain".equals(arr[arg2])){
            	            //Toast.makeText(getBaseContext(),arr[arg2], Toast.LENGTH_SHORT).show();
            	            
				}
               if("           Light".equals(arr[arg2])){
            	              startAnimation();
					
				}
               if("           Wind".equals(arr[arg2])){
					
				}
               if("           Clouds".equals(arr[arg2])){
					
				}
               if("           Hammer".equals(arr[arg2])){
					
				}
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		onCam();
	}
	/* turn on camera
	 * @author 13C Nguyen Tien Thanh*/
	 private void onCam()
	    {
	    	tmp= null;
	    	boder2= null;
	    	Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	    	intent.putExtra(MediaStore.EXTRA_OUTPUT, MyApp.getPhoto());
	    	startActivityForResult(intent, 0);
	    }
	 /* save image
	  * * @author 13C Nguyen Tien Thanh*/
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
	 @Override
	 /*show image
	  * @author Nguyen Tien Thanh*/
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
			img1.setImageBitmap(tmp);
		}
	 /*detect faces
	  * @author Nguyen Tien Thanh*/
	 private void edit()
	    {
	    	FaceDetector.Face[] faces;
			if(tmp != null)
			{
				FaceDetector face_detector = new FaceDetector(tmp.getWidth(), tmp.getHeight(), 50);
				faces = new FaceDetector.Face[50];
				count = face_detector.findFaces(tmp, faces);
				Toast.makeText(getApplicationContext(), "Face Count: " + String.valueOf(count), Toast.LENGTH_SHORT).show();
				boder2=overlay(tmp,faces);
				img1.setImageBitmap(boder2);
			}
	    }
	 /*insert troll faces into image
	  * @author 13C Nguyen Tien Thanh
	  * @author Dao Hong Thuan*/
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
	 /*save image after edit
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
	 class Starter implements Runnable {
         public void run() {
              animation.start();
          }
      }
	 /* start animation
	  * @author 13C Nguyen Tien Thanh 
	  * @author Dao Hong Thuan*/
	    private void startAnimation(){
	    	   Bitmap background;
	    	   Bitmap i1,i2,i3;
	    	   //Bitmap resize2 = Bitmap.createScaledBitmap(boder2,boder2.getWidth(),boder2.getHeight(), false);
	    	   background = BitmapFactory.decodeResource(getResources(), R.drawable.images0);
	    	   i1 = BitmapFactory.decodeResource(getResources(), R.drawable.snow2);
	    	   i2 = BitmapFactory.decodeResource(getResources(), R.drawable.snow3);
	    	   i3 = BitmapFactory.decodeResource(getResources(), R.drawable.snow4);
	           animation = new AnimationDrawable();
	           animation.addFrame( new BitmapDrawable(getResources(),overlay1(background, i1)), 100);
	           animation.addFrame( new BitmapDrawable(getResources(),overlay1(background, i2)), 100);
	           animation.addFrame( new BitmapDrawable(getResources(),overlay1(background, i3)), 100);
	           animation.setOneShot(false);
	           
	           ImageView imageView = (ImageView) findViewById(R.id.imageView1);
	           RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(800, 800);
	           params.alignWithParent = true;
	           params.addRule(RelativeLayout.CENTER_IN_PARENT);       
	 
	           imageView.setLayoutParams(params);
	           imageView.setImageDrawable(animation);
	           imageView.post(new Starter());
	       }
	    /*make frames of animation
	     * @author Nguyen Tien Thanh
	     * @author Dao Hong Thuan corp*/
	    private Bitmap overlay1(Bitmap bmp1,Bitmap bmp2) {
	       	
	       	Bitmap resizeBase = Bitmap.createScaledBitmap(bmp1,400,400, false);
	        Bitmap bmOverlay = Bitmap.createBitmap(resizeBase.getWidth(),resizeBase.getHeight(), resizeBase.getConfig());
	           Canvas canvas = new Canvas(bmOverlay);
	           canvas.drawBitmap(resizeBase, 0, 0, null);
	       		Bitmap resize = Bitmap.createScaledBitmap(bmp2,400,400, false);
	       		canvas.drawBitmap(resize ,0,0, null);
	           return bmOverlay;
	       }
	    /*find view by id
	     * @author Khong Minh Tri*/
	 public void init(){
		    img1=(ImageView)findViewById(R.id.imageView1);
		    img2=(ImageView)findViewById(R.id.imageView2);
		    editButton=(Button)findViewById(R.id.abutton);
			effectButton=(Button)findViewById(R.id.button2);
			saveButton=(Button)findViewById(R.id.button3);
			autoButton=(Button)findViewById(R.id.button4);
			controlButton=(Button)findViewById(R.id.button1);
			edit2Button=(Button)findViewById(R.id.button5);
			editSpinner=(Spinner)findViewById(R.id.edit);
			gifSpinner=(Spinner)findViewById(R.id.gif);
			editButton.setOnClickListener(this);
			effectButton.setOnClickListener(this);
			saveButton.setOnClickListener(this);
			autoButton.setOnClickListener(this);
			controlButton.setOnClickListener(this);
	  }
	@Override
	/* create option menu
	 * @author Khong Minh Tri*/
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.my_app, menu);
		return true;
		
	}
	/*get Key Back
	 * @author Khong Minh Tri*/
	public boolean onKeyDown(int keyCode,KeyEvent event){
		if((keyCode==KeyEvent.KEYCODE_BACK)){
			onCam();
		}
		return false;
		
	}
	@Override
	/*create option item selected
	 * @author Khong Minh Tri*/
	public boolean onOptionsItemSelected(MenuItem item) {
		Bitmap tmp2;
	switch (item.getItemId()) {
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
		img1.setImageBitmap(tmp2);
		break;
	}
	}
	return false;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		   case R.id.abutton:{
			 editButton.setVisibility(View.INVISIBLE);
			 effectButton.setVisibility(View.INVISIBLE);
			 autoButton.setVisibility(View.VISIBLE);
			 controlButton.setVisibility(View.VISIBLE);
			 saveButton.setVisibility(View.INVISIBLE);
			 //gifSpinner.setVisibility(View.INVISIBLE);
			 editSpinner.setVisibility(View.INVISIBLE);
			 break;
		    }
		   case R.id.button2:{
			   editButton.setVisibility(View.INVISIBLE);
			   editSpinner.setVisibility(View.INVISIBLE);
			   effectButton.setVisibility(View.INVISIBLE);
			   controlButton.setVisibility(View.INVISIBLE);
			   autoButton.setVisibility(View.INVISIBLE);
			   saveButton.setVisibility(View.VISIBLE);
			   gifSpinner.setVisibility(View.VISIBLE);
			   break;
		   }
		   case R.id.button1:{
			   autoButton.setVisibility(View.INVISIBLE);
			   controlButton.setVisibility(View.INVISIBLE);
			   edit2Button.setVisibility(View.VISIBLE);
			   editSpinner.setVisibility(View.VISIBLE);
			  break; 
		   }
		   case R.id.button4:{
			   edit();
			   
		   }
		   
		}
		
	}

}
