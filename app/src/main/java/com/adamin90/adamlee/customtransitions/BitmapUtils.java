package com.adamin90.adamlee.customtransitions;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by adamlee on 2015/8/8.
 */
public class BitmapUtils {
    int[] mPhotos={
            R.drawable.p1,
            R.drawable.p2,R.drawable.p3,
            R.drawable.p4
    };

    String[] mDescriptions = {
            "This picture was taken while sunbathing in a natural hot spring, which was " +
                    "unfortunately filled with acid, which is a lasting memory from that trip, whenever I " +
                    "I look at my own skin.",
            "I took this shot with a pinhole camera mounted on a tripod constructed out of " +
                    "soda straws. I felt that that combination best captured the beauty of the landscape " +
                    "in juxtaposition with the detritus of mankind.",
            "I don't remember where or when I took this picture. All I know is that I was really " +
                    "drunk at the time, and I woke up without my left sock.",
            "Right before I took this picture, there was a busload of school children right " +
                    "in my way. I knew the perfect shot was coming, so I quickly yelled 'Free candy!!!' " +
                    "and they scattered.",
    };

    static HashMap<Integer, Bitmap> sBitmapResourceMap = new HashMap<Integer, Bitmap>();
    public ArrayList<BitmapBean> loadPhotos(Resources resources){
       ArrayList<BitmapBean> pictures=new ArrayList<>();
        for(int i=0;i<30;i++){
            int reId=mPhotos[((int) (Math.random() * mPhotos.length))];
            Bitmap bitmap=getBitmap(resources,reId);
            Bitmap thumbnail=getThumbnail(bitmap, 200);
            String description=mDescriptions[(int) (Math.random() * mDescriptions.length)];
            pictures.add(new BitmapBean(reId,description,thumbnail));
        }
        return pictures;
    }

    private Bitmap getThumbnail(Bitmap original, int max) {

        int width=original.getWidth();
        int height=original.getHeight();
        int scaledWidth,scaledHeight;
        if(width>=height){
            float scaleFactor=(float)max/width;
            scaledWidth=200;
            scaledHeight=(int)(scaleFactor*height);
        }else {
            float scaleFactor=(float)max/height;
            scaledWidth=(int)(scaleFactor*width);
            scaledHeight=200;
        }
        Bitmap thumbnail=Bitmap.createScaledBitmap(original,scaledWidth,scaledHeight,true);
        return  thumbnail;
    }

    static Bitmap getBitmap(Resources resources, int reId) {
        Bitmap bitmap=sBitmapResourceMap.get(reId);
        if(bitmap==null){
            bitmap= BitmapFactory.decodeResource(resources,reId);
            sBitmapResourceMap.put(reId,bitmap);
        }
        return bitmap;
    }
}
