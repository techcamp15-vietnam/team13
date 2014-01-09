package com.example.myapp;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.media.FaceDetector;
import android.media.MediaPlayer;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
/*
  */
public class MyApp extends Activity implements OnClickListener{
	    private MediaPlayer mp3,mp4,mp5; 
	    AnimationDrawable animation;
	    private Bitmap star1,star2,star3,star4,star5,star6,rain1,rain2,rain3,rain4,rain5,rain6,snow1,snow2,snow3,snow4,snow5,snow6;
	    private Button editButton,effectButton,saveButton,autoButton,controlButton;
	    private Spinner editSpinner,gifSpinner;
	    private ImageView img1,img2; 
	    private Bitmap bmp1,bmp2;
	    private int count =0;
	    private Bitmap tmp;
	    private Uri temPU;
	    int x=0;
	    private Bitmap bode3;
	    Bitmap boder2=null;
	    private PointF tmp_point = new PointF();
		
	    int img_list[]={
	        	R.drawable.ic_launcher,
	        	R.drawable.bg_button
	    };
	    String arr[]={
	    		 "           None",
	    		 "           Rain",
	    		 "           Snow",
	    		 "           Stars"};
	    String arr1[]={
	    		"        Face1","        Face2","        Face3","        Face7",
	    	    "        Face4","        Face5","        Face6","        Face8"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_app);
		mp3=MediaPlayer.create(MyApp.this,R.raw.rain); 
		mp4=MediaPlayer.create(MyApp.this,R.raw.wind); 
		star1=BitmapFactory.decodeResource(getResources(), R.drawable.s1);
		star2=BitmapFactory.decodeResource(getResources(), R.drawable.s2);
		star3=BitmapFactory.decodeResource(getResources(), R.drawable.s3);
		star4=BitmapFactory.decodeResource(getResources(), R.drawable.s4);
		star5=BitmapFactory.decodeResource(getResources(), R.drawable.s5);
		star6=BitmapFactory.decodeResource(getResources(), R.drawable.s6);
		rain1=BitmapFactory.decodeResource(getResources(), R.drawable.rain1);
		rain2=BitmapFactory.decodeResource(getResources(), R.drawable.rain2);
		rain3=BitmapFactory.decodeResource(getResources(), R.drawable.rain3);
		rain4=BitmapFactory.decodeResource(getResources(), R.drawable.rain4);
		rain5=BitmapFactory.decodeResource(getResources(), R.drawable.rain5);
		rain6=BitmapFactory.decodeResource(getResources(), R.drawable.rain6);
		snow1=BitmapFactory.decodeResource(getResources(), R.drawable.snow1);
		snow2=BitmapFactory.decodeResource(getResources(), R.drawable.snow2);
		snow3=BitmapFactory.decodeResource(getResources(), R.drawable.snow3);
		snow4=BitmapFactory.decodeResource(getResources(), R.drawable.snow4);
		snow5=BitmapFactory.decodeResource(getResources(), R.drawable.snow5);
		snow6=BitmapFactory.decodeResource(getResources(), R.drawable.snow6);
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
		gifSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
               if("           Rain".equals(arr[arg2])){
            	              startAnimation1(boder2,rain1,rain2,rain3,rain4);
            	              //onPause(mp4);
            	              //mp3.start();
            	              saveButton.setOnClickListener(new OnClickListener(){
      							@Override
      							public void onClick(View arg0) {
      								// TODO Auto-generated method stub
      								Bitmap resize2 = Bitmap.createScaledBitmap(boder2,boder2.getWidth(),boder2.getHeight(), false);
      							    save_gif(Make_gif(overlay1(resize2,rain1), overlay1(resize2,rain2), overlay1(resize2,rain3), overlay1(resize2,rain4),overlay1(resize2,rain5),overlay1(resize2,rain6)));			
      										
      							}
                  	        	  
                  	          });
            	            
            	            
				}
               if("           Snow".equals(arr[arg2])){
            	              startAnimation1(boder2,snow1,snow2,snow3,snow4);
            	              //onPause(mp3);
            	              //mp4.start();
            	              
            	              saveButton.setOnClickListener(new OnClickListener(){

								@Override
								public void onClick(View arg0) {
								   Bitmap resize2 = Bitmap.createScaledBitmap(boder2,boder2.getWidth(),boder2.getHeight(), false);
								   save_gif(Make_gif(overlay1(resize2,snow1), overlay1(resize2,snow2), overlay1(resize2,snow3), overlay1(resize2,snow4),overlay1(resize2,snow5),overlay1(resize2,snow6)));			
								}
            	            	  
            	              });
            	              
					
				}
               if("           Stars".equals(arr[arg2])){
            	          startAnimation1(boder2,star1,star2,star3,star4);
            	          saveButton.setOnClickListener(new OnClickListener(){

							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								Bitmap resize2 = Bitmap.createScaledBitmap(boder2,boder2.getWidth(),boder2.getHeight(), false);
							    save_gif(Make_gif(overlay1(resize2, star1), overlay1(resize2,star2), overlay1(resize2,star3), overlay1(resize2,star4),overlay1(resize2,star5),overlay1(resize2,star6)));						
							} 
            	          });
				}
               if("           None".equals(arr[arg2])){
            	   img1.setImageBitmap(boder2);
               }
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		onCam();
	}
	 private void onCam()
	    {
	    	tmp= null;
	    	boder2= null;
	    	Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	    	intent.putExtra(MediaStore.EXTRA_OUTPUT, MyApp.getPhoto());
	    	startActivityForResult(intent, 0);
	    }
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
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			if(resultCode != RESULT_OK)
			{
				return;
			}
			String imgPath = Environment.getExternalStorageDirectory()+ File.separator +"tmp.jpg";
			BitmapFactory.Options bitmap_options = new BitmapFactory.Options();
			bitmap_options.inPreferredConfig = Bitmap.Config.RGB_565;
			//rotateBitmap(imgPath);
			tmp = rotateBitmap(imgPath);//BitmapFactory.decodeFile(imgPath, bitmap_options);
			boder2=tmp;
			
			img1.setImageBitmap(tmp);
		}
		public Bitmap rotateBitmap (String fileName) {
			Bitmap bitmap = null;
			Bitmap rotatedBitmap = null;
			try {
				BitmapFactory.Options bitmap_options = new BitmapFactory.Options();
				bitmap_options.inPreferredConfig = Bitmap.Config.RGB_565;
				bitmap = BitmapFactory.decodeFile (fileName,bitmap_options);
				if (bitmap == null) {
					return null;
				}
				ExifInterface ex = new ExifInterface (fileName);
				int orientation = ex.getAttributeInt (ExifInterface.TAG_ORIENTATION,
						ExifInterface.ORIENTATION_UNDEFINED);

				if (orientation == ExifInterface.ORIENTATION_UNDEFINED) {
					 Cursor cursor=getContentResolver ().query (temPU,new String [] {MediaStore.Images.ImageColumns.ORIENTATION},null, null, null);
					 try {
						 if (cursor.moveToFirst ()) {
							 int deg = cursor. getInt (cursor.getColumnIndexOrThrow (MediaStore.Images.ImageColumns.ORIENTATION));
							 if (deg == 90) {
								orientation = ExifInterface.ORIENTATION_ROTATE_90;
							} else if (deg == 180) {
								orientation = ExifInterface.ORIENTATION_ROTATE_180;
							} else if (deg == 270) {
								orientation = ExifInterface.ORIENTATION_ROTATE_270;
							}
						}

						cursor.close ();
					} catch(Exception e) {

					}
				}

				int degree = 0;
				switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_270:
					degree += 270;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					degree += 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree += 90;
					break;
				case ExifInterface.ORIENTATION_TRANSPOSE:
					degree += 45;
					break;
				case ExifInterface.ORIENTATION_UNDEFINED:
					degree += 360;
					break;
				default:
					break;
				}

				if (degree> 0) {
					Matrix matrix = new Matrix ();
					matrix.postRotate (degree);
					rotatedBitmap = Bitmap.createBitmap (bitmap, 0, 0,
							bitmap.getWidth (), bitmap.getHeight (), matrix, true);
				}else{
					rotatedBitmap = bitmap;
				}

			}catch (Exception e) {
			}

			return rotatedBitmap;
		}
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
	    private void startAnimation1(Bitmap boder2,Bitmap b1,Bitmap b2,Bitmap b3,Bitmap b4){
	    	   Bitmap i1,i2,i3;
	    	   if(boder2 == null){
	    		   return;
	    	   }
	    	   Bitmap resize2 = Bitmap.createScaledBitmap(boder2,boder2.getWidth(),boder2.getHeight(), false);
	           animation = new AnimationDrawable();
	           animation.addFrame( new BitmapDrawable(getResources(),overlay1(resize2, b1)), 100);
	           animation.addFrame( new BitmapDrawable(getResources(),overlay1(resize2, b2)), 100);
	           animation.addFrame( new BitmapDrawable(getResources(),overlay1(resize2, b3)), 100);
	           animation.addFrame( new BitmapDrawable(getResources(),overlay1(resize2, b4)), 100);
	           animation.setOneShot(false);
	           ImageView imageView = (ImageView) findViewById(R.id.imageView1);
	           RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(img1.getWidth(),img1.getHeight());
	           params.alignWithParent = true;
	           params.addRule(RelativeLayout.CENTER_IN_PARENT);       
	 
	           imageView.setLayoutParams(params);
	           imageView.setImageDrawable(animation);
	           imageView.post(new Starter());
	       }
	    private Bitmap overlay1(Bitmap bmp1,Bitmap bmp2) {
	    	Bitmap resizeBase;
	    	Bitmap resize;
	    	if(bmp1.getWidth()<bmp1.getHeight())
	    	{
	    		resizeBase = Bitmap.createScaledBitmap(bmp1,300,533, true);
	    		resize = Bitmap.createScaledBitmap(bmp2,300,533, false);
	    	}
	    	else
	    	{
	    		resizeBase = Bitmap.createScaledBitmap(bmp1,533,300, true);
	    		resize = Bitmap.createScaledBitmap(bmp2,533,300, false);
	    	}
	       	
	        Bitmap bmOverlay = Bitmap.createBitmap(resizeBase.getWidth(),resizeBase.getHeight(), resizeBase.getConfig());
	           Canvas canvas = new Canvas(bmOverlay);
	           canvas.drawBitmap(resizeBase, 0, 0, null);
	       		canvas.drawBitmap(resize ,0,0, null);
	           return bmOverlay;
	       }
	    /*save gif file
	     * @param byte[] a: byte array is saved
	     * */
	    private void save_gif(byte[] array)
	    {
	    	String iconsStoragePath = Environment.getExternalStorageDirectory() + "/myAppDir/myimages1";
			File sdIconStorageDir = new File(iconsStoragePath);
			String filePath = sdIconStorageDir.toString() + "/"+SystemClock.currentThreadTimeMillis()+".gif";
			FileOutputStream fileOutputStream;
			try {
				fileOutputStream = new FileOutputStream(filePath);
				BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
				bos.write(array);
				bos.flush();
				bos.close();
				Toast.makeText(getBaseContext(), "SAVED", Toast.LENGTH_SHORT).show();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    }
	    /*make a gif flie
	     * @param Bitmap b1,Bitmap b2,Bitmap b3,Bitmap b4: 4 frames of gif file
	     * */
	    private byte[] Make_gif(Bitmap b1,Bitmap b2,Bitmap b3,Bitmap b4,Bitmap b5,Bitmap b6)
	    {
	    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    	 
	    	encoder encoder = new encoder();
	    	encoder.start(bos);
	    	encoder.addFrame(b1);
	    	encoder.addFrame(b2);
	    	encoder.addFrame(b3);
	    	encoder.addFrame(b4);
	    	encoder.addFrame(b5);
	    	encoder.addFrame(b6);
	    	encoder.finish();
	    	return bos.toByteArray();
	    }
	    
	 public void init(){
		    img1=(ImageView)findViewById(R.id.imageView1);
		    img2=(ImageView)findViewById(R.id.imageView2);
		    editButton=(Button)findViewById(R.id.abutton);
			effectButton=(Button)findViewById(R.id.button2);
			saveButton=(Button)findViewById(R.id.button3);
			autoButton=(Button)findViewById(R.id.button4);
			controlButton=(Button)findViewById(R.id.button1);
			editSpinner=(Spinner)findViewById(R.id.edit);
			gifSpinner=(Spinner)findViewById(R.id.gif);
			editButton.setOnClickListener(this);
			effectButton.setOnClickListener(this);
			saveButton.setOnClickListener(this);
			autoButton.setOnClickListener(this);
			controlButton.setOnClickListener(this);
	  }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.my_app, menu);
		return true;
	}
	public boolean onKeyDown(int keyCode,KeyEvent event){
		if((keyCode==KeyEvent.KEYCODE_BACK)){
			switch (x) {
			case 0:
				onCam();
				break;
			case 1:
				effectButton.setVisibility(View.VISIBLE);
				editButton.setVisibility(View.VISIBLE);
				saveButton.setVisibility(View.INVISIBLE);
				autoButton.setVisibility(View.INVISIBLE);
				controlButton.setVisibility(View.INVISIBLE);
				editSpinner.setVisibility(View.INVISIBLE);
				gifSpinner.setVisibility(View.INVISIBLE);
				x=0;
				break;
			}
			return true;
		}
		return false;
	}  
	@Override
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
		 tmp2=rotateBitmap(imgPath);
		//BitmapFactory.Options bitmap_options = new BitmapFactory.Options();
		//bitmap_options.inPreferredConfig = Bitmap.Config.RGB_565;
		//tmp2 = BitmapFactory.decodeFile(imgPath, bitmap_options);
		img1.setImageBitmap(tmp2);
		boder2=tmp2;
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
			 x=1;
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
			   x=1;
			   break;
		   }
		   case R.id.button1:{
			   autoButton.setVisibility(View.INVISIBLE);
			   controlButton.setVisibility(View.INVISIBLE);
			   editSpinner.setVisibility(View.VISIBLE);
			  break; 
		   }
		   case R.id.button4:{
			   
			   edit();
			   break;
		   }
		}
		
	}

}
