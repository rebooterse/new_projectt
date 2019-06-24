package fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.ref.WeakReference;

public class SaveImages implements Target {
    private Context context;

    private WeakReference<ContentResolver> mResolver;
    private String imageName;
    private String desc;

    public SaveImages(Context context,ContentResolver mResolver, String imageName,String desc) {

        this.mResolver = new WeakReference<ContentResolver>(mResolver);
        this.imageName = imageName;
        this.context = context;
        this.desc = desc;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
ContentResolver contentResolver = mResolver.get();

if (contentResolver != null)
    MediaStore.Images.Media.insertImage(contentResolver,bitmap,imageName,desc);





    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {

    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }
}
