package com.example.myapp;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
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
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
/*
  */
@SuppressLint("NewApi")
public class MyApp extends Activity implements OnClickListener{
		private MediaPlayer mp3,mp4,mp5; 
	    AnimationDrawable animation;
	    private Bitmap star1,star2,star3,star4,rain1,rain2,rain3,rain4,snow1,snow2,snow3,snow4;
	    private Button editButton,effectButton,saveButton,autoButton,controlButton,selectButton;
	    private Spinner editSpinner,gifSpinner;
	    private ImageView img1,img2; 
	    private Bitmap bmp1,bmp2;
	    private int count =0;
	    private Bitmap tmp;
	    private Uri temPU;
	    int a=0,zoom=0,b=0;
	    private float x0,y0,x1,x2,x3,y1,y2,y3,x=0,y=0;
	    private Button b1,b2,b3,b4;
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
	    		"        Face1","        Face2","        Face3","        Face4",
	    	    "        Face5","        Face6","        Face7","        Face8","        Face9"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_app);
		MediaPlayer.create(MyApp.this,R.raw.rain); 
		mp4=MediaPlayer.create(MyApp.this,R.raw.wind); 
		star1=BitmapFactory.decodeResource(getResources(), R.drawable.s1);
		star2=BitmapFactory.decodeResource(getResources(), R.drawable.s2);
		star3=BitmapFactory.decodeResource(getResources(), R.drawable.s3);
		star4=BitmapFactory.decodeResource(getResources(), R.drawable.s4);
		rain1=BitmapFactory.decodeResource(getResources(), R.drawable.rain1);
		rain2=BitmapFactory.decodeResource(getResources(), R.drawable.rain2);
		rain3=BitmapFactory.decodeResource(getResources(), R.drawable.rain3);
		rain4=BitmapFactory.decodeResource(getResources(), R.drawable.rain4);
		snow1=BitmapFactory.decodeResource(getResources(), R.drawable.snow2);
		snow2=BitmapFactory.decodeResource(getResources(), R.drawable.snow3);
		snow3=BitmapFactory.decodeResource(getResources(), R.drawable.snow4);
		snow4=BitmapFactory.decodeResource(getResources(), R.drawable.snow5);
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
		
		/*set on click
		 * @author 13B Khong Minh tri*/
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				rotateImage(img2);
			}
		});
		
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (zoom>-10){
				zoom--;
				int a,b;
				a=img2.getWidth();
				b=img2.getHeight();
				RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(a-a/6,b-b/6);
			    img2.setLayoutParams(layoutParams);
			    img2.setX(x);
				img2.setY(y);
			    layoutParams=null;
				}
			}
		});
		
		b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (zoom<10){
				zoom++;
				int a,b;
				a=img2.getWidth();
				b=img2.getHeight();
				RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(a+a/6,b+b/6);
			    img2.setLayoutParams(layoutParams);
			    img2.setX(x);
				img2.setY(y);
			    layoutParams=null;
				}
			}
		});
		
		b4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*ImageView img11 = null;
				img11.setImageBitmap(boder2);*/
				if (b==0){
				img1.setImageBitmap(imgOverlay(img1, img2));
				img2.setVisibility(View.GONE);
				boder2=imgOverlay(img1, img2);
	            b1.setVisibility(View.GONE);
	            b2.setVisibility(View.GONE);
	            b3.setVisibility(View.GONE);
	            b4.setVisibility(View.GONE);
	            x=0;y=0;
	            img2.setX(x);
				img2.setY(y);}
			}
		});
		
		img2.setOnTouchListener(new ChoiceTouchListener());
		img1.setOnDragListener(new ChoiceDragListener());
		/*select face
		 * @author 13B Khong Minh tri*/
		
		editSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if("        Face1".equals(arr1[arg2])){
					img2.setImageResource(R.drawable.images0);
				}
				if("        Face2".equals(arr1[arg2])){
					img2.setImageResource(R.drawable.images1);
				}
				if("        Face3".equals(arr1[arg2])){
					img2.setImageResource(R.drawable.images2);
				}
				if("        Face4".equals(arr1[arg2])){
					img2.setImageResource(R.drawable.images3);
				}
                if("        Face5".equals(arr1[arg2])){
                	img2.setImageResource(R.drawable.images4);
				}
                if("        Face6".equals(arr1[arg2])){
                	img2.setImageResource(R.drawable.images5);
				}
                if("        Face7".equals(arr1[arg2])){
                	img2.setImageResource(R.drawable.images6);
				}
                if("        Face8".equals(arr1[arg2])){
                	img2.setImageResource(R.drawable.images7);
				}
                if("        Face9".equals(arr1[arg2])){
                	img2.setImageResource(R.drawable.images8);
				}
                img2.setVisibility(View.VISIBLE);
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                b4.setVisibility(View.VISIBLE);
			}
			private ImageView getResource(int images0) {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		gifSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			/*select image
			 * @author 13A Dao Hong Thuan*/
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				b=1;
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
      							    save_gif(Make_gif(overlay1(resize2,rain1), overlay1(resize2,rain2), overlay1(resize2,rain3), overlay1(resize2,rain4)));			
      										
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
								   save_gif(Make_gif(overlay1(resize2,snow1), overlay1(resize2,snow2), overlay1(resize2,snow3), overlay1(resize2,snow4)));			
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
							    save_gif(Make_gif(overlay1(resize2, star1), overlay1(resize2,star2), overlay1(resize2,star3), overlay1(resize2,star4)));						
							} 
            	          });
				}
               if("           None".equals(arr[arg2])){
            	   img1.setImageBitmap(boder2);
            	   b=0;
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
	  * @author 13C Nguyen Tien Thanh*/
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
	 /*rotate image
	  * @author 13A Dao Hong Thuan
	  * @param String filename: directory of file*/
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
/* set on touch
 * @author 13B Khong Minh Tri*/	
		
		
		@SuppressLint("NewApi")
		final class ChoiceTouchListener implements OnTouchListener {
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
					/*
					 * Drag details: we only need default behavior - clip data could
					 * be set to pass data as part of drag - shadow can be tailored
					 */
					ClipData data = ClipData.newPlainText("", "");
					DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
					// start dragging the item touched
					view.startDrag(data, shadowBuilder, view, 0);
					return true;
				} else {
					return false;
				}
			}
		}
		
		/* 
		 * @author 13B Khong Minh Tri*/	
		@SuppressLint("NewApi")
		class ChoiceDragListener implements OnDragListener {
			 
			@Override
			public boolean onDrag(View v, DragEvent event) {
				switch (event.getAction()) {
				case DragEvent.ACTION_DRAG_STARTED:
					/*bt1.setVisibility(View.GONE);
					bt2.setVisibility(View.GONE);
					bt3.setVisibility(View.GONE);
					bt4.setVisibility(View.GONE);*/
					// no action necessary
					break;
				case DragEvent.ACTION_DRAG_ENTERED:
					// no action necessary
					break;
				case DragEvent.ACTION_DRAG_EXITED:
					// no action necessary
					break;
				case DragEvent.ACTION_DROP:
					//drop imageview
					x0=event.getX();
					y0=event.getY();
					x=x0-img2.getWidth()/2;
					y=y0-img2.getHeight()/2;
					x1=v.getX();
					y1=v.getY();
					x=x+x1;
					y=y+y1;
					img2.setX(x);
					img2.setY(y);
					break;
				case DragEvent.ACTION_DRAG_ENDED:
					/*bt1.setVisibility(View.VISIBLE);
					bt2.setVisibility(View.VISIBLE);
					bt3.setVisibility(View.VISIBLE);
					bt4.setVisibility(View.VISIBLE);*/
					break;
				default:
					break;
				}
				return true;
			}
		}
		/* 
		 * @author 13B Khong Minh Tri*/	
		public void rotateImage(ImageView imgv2) {
			Bitmap bitmap = ((BitmapDrawable)imgv2.getDrawable()).getBitmap();
			Bitmap targetBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		    Canvas canvas = new Canvas(targetBitmap);
		    Matrix matrix = new Matrix();
		    matrix.setRotate(30, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
		    canvas.drawBitmap(bitmap, matrix, new Paint());
		    imgv2.setImageBitmap(targetBitmap);
		}
		
		@SuppressLint("NewApi")
		/* 
		 * @author 13B Khong Minh Tri*/	
		private	Bitmap imgOverlay(ImageView imgv1,ImageView imgv2){
			Bitmap bmp1,bmp2,bmp11,bmp21;
			float m=0,n=0;
			bmp1 = ((BitmapDrawable)imgv1.getDrawable()).getBitmap();
			bmp2 = ((BitmapDrawable)imgv2.getDrawable()).getBitmap();
			//Log.i("Bitmap 1", "width: " + bmp2.getWidth() + " height: " + bmp2.getHeight());
			x1=bmp1.getWidth();y1=bmp1.getHeight();
	    	x2=imgv1.getWidth();y2=imgv1.getHeight();
	    	if (x1/x2>y1/y2) {
	    		x3=x2;
	    		y3=x2*y1/x1;
	    		n=y2/2-y3/2;
	    	}
	    	else{
	    		y3=y2;
	    		x3=y2*x1/y1;
	    		m=x2/2-x3/2;
	    	}
	    	
	    	
	    	
			//a=(int)(bmp1.getWidth()*param2.width/param1.width);
	        //b=(int)(a*param2.height/param2.width);
			/*if ((bmp1.getWidth()/param1.width)>(bmp1.getHeight()/param1.height))
			bmp11 = Bitmap.createScaledBitmap(bmp1, param1.width,param1.width*bmp1.getHeight()/bmp1.getWidth(), false);
			else bmp11 = Bitmap.createScaledBitmap(bmp1, param1.height*bmp1.getWidth()/bmp1.getHeight(),param1.height, false);*/
			//Log.i("Bitmap",param1.width+" "+param1.height+" "+param2.width+" "+param2.height);
			/*if ((bmp2.getWidth()/param2.width)>(bmp2.getHeight()/param2.height))
				bmp21 = Bitmap.createScaledBitmap(bmp2, param2.width,param2.width*bmp2.getHeight()/bmp2.getWidth(), false);
				else bmp21 = Bitmap.createScaledBitmap(bmp2, param2.height*bmp2.getWidth()/bmp2.getHeight(),param2.height, false);*/
			//Log.i("info",param1.width+" "+param1.height+" "+param2.width+" "+param2.height);
	    	LayoutParams param2= (LayoutParams)imgv2.getLayoutParams();
			bmp11 = Bitmap.createScaledBitmap(bmp1,(int)x3,(int)y3, false);
			bmp21 = Bitmap.createScaledBitmap(bmp2,(int)param2.width,(int)param2.height, false);
			Bitmap bmOverlay = Bitmap.createBitmap(bmp11.getWidth(),bmp11.getHeight(), bmp11.getConfig());
	        Canvas canvas = new Canvas(bmOverlay);
	        canvas.drawBitmap(bmp11, 0, 0, null);	
	    	canvas.drawBitmap(bmp21 ,x-imgv1.getX()-m,y-imgv1.getY()-n, null);
	    	//Log.i("kich thuoc",x+" "+y);
	        return bmOverlay;
		}		

		/*detect faces
		* @author 13C Nguyen Tien Thanh*/
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
		 * @author 13A Dao Hong Thuan*/
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
		 * @author 13A Dao Hong Thuan*/
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
	 /* start animation
		 * @author 13C Nguyen Tien Thanh 
		 * @author 13A Dao Hong Thuan*/
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
	    /*make frames of animation
		    * @author 13A Dao Hong Thuan
		    * @author 13B Khong Minh Tri corp
		    * @author 13C Nguyen Tien Thanh corp */
	    private Bitmap overlay1(Bitmap bmp1,Bitmap bmp2) {
	    	x1=bmp1.getWidth();y1=bmp1.getHeight();
	    	x2=img1.getWidth();y2=img1.getHeight();
	    	if (x1/x2>y1/y2) {
	    		x3=x2;
	    		y3=x2*y1/x1;
	    	}
	    	else{
	    		y3=y2;
	    		x3=y2*x1/y1;
	    	}
	       	Bitmap resizeBase = Bitmap.createScaledBitmap(bmp1,(int)x3,(int)y3, true);
	        Bitmap bmOverlay = Bitmap.createBitmap(resizeBase.getWidth(),resizeBase.getHeight(), resizeBase.getConfig());
	    	   
	    	Canvas canvas = new Canvas(bmOverlay);
	           canvas.drawBitmap(resizeBase, 0, 0, null);
	       		Bitmap resize = Bitmap.createScaledBitmap(bmp2,(int)x3,(int)y3, false);
	       		canvas.drawBitmap(resize ,0,0, null);
	           return bmOverlay;
	       }
	    /*save gif file
	     * @param byte[] a: byte array is saved
	     * * @author 13c Nguyen Tien Thanh
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
	     * @author 13C Nguyen tien Thanh*/
	    private byte[] Make_gif(Bitmap b1,Bitmap b2,Bitmap b3,Bitmap b4)
	    {
	    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    	 
	    	encoder encoder = new encoder();
	    	encoder.start(bos);
	    	encoder.addFrame(b1);
	    	encoder.addFrame(b2);
	    	encoder.addFrame(b3);
	    	encoder.addFrame(b4);
	    	encoder.finish();
	    	return bos.toByteArray();
	    }
	    /*find view by id
		 * @author Khong Minh Tri
		 * @author Dao Hong Thuan*/
	 public void init(){
		    img1=(ImageView)findViewById(R.id.imageView1);
		    img2=(ImageView)findViewById(R.id.imageView2);
		    editButton=(Button)findViewById(R.id.abutton);
			effectButton=(Button)findViewById(R.id.button2);
			saveButton=(Button)findViewById(R.id.button3);
			autoButton=(Button)findViewById(R.id.button4);
			controlButton=(Button)findViewById(R.id.button1);
			selectButton=(Button)findViewById(R.id.button5);
			editSpinner=(Spinner)findViewById(R.id.edit);
			gifSpinner=(Spinner)findViewById(R.id.gif);
			b1=(Button)findViewById(R.id.btRotate);
			b2=(Button)findViewById(R.id.btDec);
			b3=(Button)findViewById(R.id.btInc);
			b4=(Button)findViewById(R.id.btOk);
			editButton.setOnClickListener(this);
			effectButton.setOnClickListener(this);
			saveButton.setOnClickListener(this);
			autoButton.setOnClickListener(this);
			controlButton.setOnClickListener(this);
			selectButton.setOnClickListener(this);
			
	  }
	@Override
	/* create option menu
	* @author 13B Khong Minh Tri
	* @author 13A Dao Hong Thuan*/
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.my_app, menu);
		return true;
	}
	/*get Key Back
	* @author 13B Khong Minh Tri
	* @author 13A Dao Hong Thuan*/
	public boolean onKeyDown(int keyCode,KeyEvent event){
		if((keyCode==KeyEvent.KEYCODE_BACK)){
			switch (a) {
			case 0:
				onCam();
				break;
			case 1:
				effectButton.setVisibility(View.VISIBLE);
				editButton.setVisibility(View.VISIBLE);
				saveButton.setVisibility(View.GONE);
				autoButton.setVisibility(View.GONE);
				controlButton.setVisibility(View.GONE);
				editSpinner.setVisibility(View.GONE);
				selectButton.setVisibility(View.GONE);
				gifSpinner.setVisibility(View.GONE);
				img2.setVisibility(View.GONE);
                b1.setVisibility(View.GONE);
                b2.setVisibility(View.GONE);
                b3.setVisibility(View.GONE);
                b4.setVisibility(View.GONE);
				a=0;
				break;
			}
			return true;
		}
		return false;
	}  
	@Override
	/*create option item selected
	* @author 13B Khong Minh Tri*/
	public boolean onOptionsItemSelected(MenuItem item) {
		Bitmap tmp2;
	switch (item.getItemId()) {
	case R.id.save:{
		Toast.makeText(getBaseContext(), "saved",Toast.LENGTH_SHORT).show();
		storeImage(boder2, SystemClock.currentThreadTimeMillis()+".png");
	    break;	
	}
	case R.id.reset:{
		b=0;
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
	/*set onClick
	* @author 13A Dao Hong Thuan*/
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		   case R.id.abutton:{
			 editButton.setVisibility(View.GONE);
			 effectButton.setVisibility(View.GONE);
			 autoButton.setVisibility(View.VISIBLE);
			 controlButton.setVisibility(View.VISIBLE);
			 saveButton.setVisibility(View.GONE);
			 //gifSpinner.setVisibility(View.GONE);
			 editSpinner.setVisibility(View.GONE);
			 img2.setVisibility(View.GONE);
            b1.setVisibility(View.GONE);
             b2.setVisibility(View.GONE);
             b3.setVisibility(View.GONE);
             b4.setVisibility(View.GONE);
			 a=1;
			 break;
		    }
		   case R.id.button2:{
			   editButton.setVisibility(View.GONE);
			   editSpinner.setVisibility(View.GONE);
			   effectButton.setVisibility(View.GONE);
			   controlButton.setVisibility(View.GONE);
			   autoButton.setVisibility(View.GONE);
			   saveButton.setVisibility(View.GONE);
			   gifSpinner.setVisibility(View.GONE);
			   selectButton.setVisibility(View.VISIBLE);
			   a=1;
			   break;
		   }
		   case R.id.button1:{
			   autoButton.setVisibility(View.GONE);
			   controlButton.setVisibility(View.GONE);
			   editSpinner.setVisibility(View.VISIBLE);
			  break; 
		   }
		   case R.id.button4:{
			   
			   edit();
			   break;
		   }
		   case R.id.button5:{
			   saveButton.setVisibility(View.VISIBLE);
			   gifSpinner.setVisibility(View.VISIBLE);
			   selectButton.setVisibility(View.GONE);
			   break;
		   }
		}
		
	}

}
