package com.adamin90.adamlee.customtransitions;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private  static final String PACKAGE="com.adamin90.adamlee.customtransitions";
    static  float sAnimatorScale=1;

    GridLayout mGridLayout;
    HashMap<ImageView,BitmapBean> mBitmapBeans=new HashMap<>();
    BitmapUtils bitmapUtils=new BitmapUtils();
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ColorMatrix grayMatrix=new ColorMatrix();
        grayMatrix.setSaturation(0);
        ColorMatrixColorFilter grayscaleFilter=new ColorMatrixColorFilter(grayMatrix);
        mGridLayout = (GridLayout) findViewById(R.id.gridLayout);
        mGridLayout.setColumnCount(3);
        mGridLayout.setUseDefaultMargins(true);

        Resources resources=getResources();
        ArrayList<BitmapBean> pictures=bitmapUtils.loadPhotos(resources);
        for(int i=0;i<pictures.size();++i){
            BitmapBean bitmapBean=pictures.get(i);
            BitmapDrawable thumb=new BitmapDrawable(resources,bitmapBean.thumbnail);
            thumb.setColorFilter(grayscaleFilter);
            ImageView imageView=new ImageView(this);
            imageView.setOnClickListener(thumclick);
            imageView.setImageDrawable(thumb);
            mBitmapBeans.put(imageView, bitmapBean);
            mGridLayout.addView(imageView);

        }

    }

    private View.OnClickListener thumclick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int[] screenLocation=new int[2];
            v.getLocationOnScreen(screenLocation);
            BitmapBean info=mBitmapBeans.get(v);
            Intent subActivity=new Intent(MainActivity.this,SubActivity.class);

            int oriention=getResources().getConfiguration().orientation;
            subActivity.putExtra(PACKAGE+".orientation",oriention)
                    .putExtra(PACKAGE+".resourceId",info.resourceId)
                    .putExtra(PACKAGE+".left",screenLocation[0])
                    .putExtra(PACKAGE+".top",screenLocation[1])
                    .putExtra(PACKAGE+".width",v.getWidth())
                    .putExtra(PACKAGE+".height",v.getHeight())
                    .putExtra(PACKAGE + ".description", info.description);
            startActivity(subActivity);
            overridePendingTransition(0,0);

        }
    };

}
