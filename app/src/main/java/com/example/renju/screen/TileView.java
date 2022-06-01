package com.example.renju.screen;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.content.res.AppCompatResources;

import com.example.renju.R;
import com.example.renju.logic.Const;
import com.example.renju.logic.Tile;

public class TileView {

    public interface OnTileClickListener {
        void onTileClick(Tile tile);
    }

    ImageView imageView;
    OnTileClickListener listener;


    TileView(Context context, Tile tile) {

        tile.setObserver(new Tile.Observer() {
            @Override
            public void onSetPiece(int color) {
                if (color == Const.WHITE) {
                    imageView.setImageResource(R.drawable.piece_white_merged);
                } else imageView.setImageResource(R.drawable.piece_black_merged);
            }
        });

        imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.tile_plus);
        imageView.setOnClickListener(view -> {
            listener.onTileClick(tile);
        });
    }


    public void setImageViewWidth(int width) {
        imageView.setLayoutParams(new TableRow.LayoutParams(width, width));
    }

    public ImageView getImageView() {
        return imageView;
    }


    public void setOnTileClickListener(OnTileClickListener onTileClickListener) {
        listener = onTileClickListener;
    }
}
