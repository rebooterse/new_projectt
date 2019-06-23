package adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mxn.soul.flowingdr.FullScreenImage;
import com.mxn.soul.flowingdr.MainActivity;
import com.mxn.soul.flowingdr.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import models.Clothes;
import models.Shoes;

public class RecyclerViewGalleryMain extends RecyclerView.Adapter<RecyclerViewGalleryMain.ViewHolder> {
    @SuppressLint("StaticFieldLeak")
    private

    String img;


    private ImageView imageViewForFullScreen;
    private TextView copyShopNameTextOnImage;
    private TextView copyPriceTextOnImage;
    private ImageButton copyOnImageFullScreen;
    private ImageButton copyOnImageFavorite;

    private Context mContext;

    private List<Clothes> clothesList;
    private List<Shoes> shoesList;


    public RecyclerViewGalleryMain(List<Clothes> clothesList, Context mContext) {
        this.mContext = mContext;
        this.clothesList = clothesList;
    }

    public RecyclerViewGalleryMain(Context mContext, List<Shoes> shoesList) {
        this.mContext = mContext;
        this.shoesList = shoesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_gallery_main, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        if (MainActivity.x == 0) {
            int def = MainActivity.shoesList.get(position).getImage();
            viewHolder.image.setImageResource(def);
        } else if (MainActivity.x == 1) {
         img = MainActivity.finalList.get(position).getClotheImage();
         Uri uri = Uri.parse(img);
         Picasso.with(mContext)
                 .load(uri)
                 .into(viewHolder.image);
//            int orinakImg = MainActivity.finalList.get(position).getClotheImage();
//            viewHolder.image.setImageResource(orinakImg);
        }

        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageViewForFullScreen != null) {
                    imageViewForFullScreen.clearColorFilter();
                    copyShopNameTextOnImage.setVisibility(View.INVISIBLE);
                    copyPriceTextOnImage.setVisibility(View.INVISIBLE);
                    copyOnImageFullScreen.setVisibility(View.INVISIBLE);
                    copyOnImageFavorite.setVisibility(View.INVISIBLE);


                }
                if (viewHolder.priceTextOnImage.getVisibility() == View.INVISIBLE) {
                    viewHolder.image.setColorFilter(Color.HSVToColor(170, new float[3]));
                    viewHolder.priceTextOnImage.setText(clothesList.get(position).getPrice());
                    viewHolder.priceTextOnImage.setVisibility(View.VISIBLE);
                    viewHolder.onImageFavorite.setVisibility(View.VISIBLE);
                    //todo srtiky
                    viewHolder.onImageFullScreen.setVisibility(View.VISIBLE);
                    viewHolder.shopNameTextOnImage.setText(clothesList.get(position).getShopName());
                    viewHolder.shopNameTextOnImage.setVisibility(View.VISIBLE);


                    copyShopNameTextOnImage = viewHolder.itemView.findViewById(viewHolder.shopNameTextOnImage.getId());
                    copyPriceTextOnImage = viewHolder.itemView.findViewById(viewHolder.priceTextOnImage.getId());
                    copyOnImageFullScreen = viewHolder.itemView.findViewById(viewHolder.onImageFullScreen.getId());
                    copyOnImageFavorite = viewHolder.itemView.findViewById(viewHolder.onImageFavorite.getId());
                    imageViewForFullScreen = viewHolder.itemView.findViewById(viewHolder.image.getId());

                }
            }
        });
        viewHolder.onImageFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FullScreenImage.class);
                intent.putExtra("IMG", img);
                mContext.startActivity(intent);

            }
        });
    }
//   private static Target getTarget(final String url){
//        Target target = new Target(){
//
//            @Override
//            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
//                new Thread(new Runnable() {
//
//                    @Override
//                    public void run() {
//
//                        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + url);
//                        try {
//                            file.createNewFile();
//                            FileOutputStream ostream = new FileOutputStream(file);
//                            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
//                            ostream.flush();
//                            ostream.close();
//                        } catch (IOException e) {
//                            Log.e("IOException", e.getLocalizedMessage());
//                        }
//                    }
//                }).start();
//
//            }
//
//            @Override
//            public void onBitmapFailed(Drawable errorDrawable) {
//
//            }
//
//            @Override
//            public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//            }
//        };
//        return target;
//    }

    @Override
    public int getItemCount() {
        return 18;
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        ImageView image;
        TextView priceTextOnImage;
        ImageButton onImageFavorite;
        ImageButton onImageFullScreen;
        TextView shopNameTextOnImage;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_gallery_main);
            priceTextOnImage = itemView.findViewById(R.id.priceText_onImage_main);
            onImageFavorite = itemView.findViewById(R.id.onImage_favorite);
            onImageFullScreen = itemView.findViewById(R.id.onImage_fullScreen);
            shopNameTextOnImage = itemView.findViewById(R.id.shopNameText_onImage_main);

        }
    }

}
