package adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mxn.soul.flowingdr.FullScreenImage;
import com.mxn.soul.flowingdr.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import models.Clothes;

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

    public RecyclerViewGalleryMain(List<Clothes> clothesList, Context mContext) {
        this.mContext = mContext;
        this.clothesList = clothesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_gallery_main, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        final String img = clothesList.get(position).getClotheImage();
        Uri uri = Uri.parse(img);
        Picasso.with(mContext)
                .load(uri)
                .into(viewHolder.image);

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

    @Override
    public int getItemCount() {
        return clothesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        ImageView image;
        TextView priceTextOnImage;
        ImageButton onImageFavorite;
        ImageButton onImageFullScreen;
        TextView shopNameTextOnImage;


        ViewHolder(@NonNull View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.image_gallery_main);
            priceTextOnImage = itemView.findViewById(R.id.priceText_onImage_main);
            onImageFavorite = itemView.findViewById(R.id.onImage_favorite);
            onImageFullScreen = itemView.findViewById(R.id.onImage_fullScreen);
            shopNameTextOnImage = itemView.findViewById(R.id.shopNameText_onImage_main);

        }
    }

}
