package com.dicoding.academies.ayi.cataloguemovie.activity;

import android.content.Context;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dicoding.academies.ayi.cataloguemovie.db.FavoriteHelper;
import com.dicoding.academies.ayi.cataloguemovie.entity.Favorite;
import com.dicoding.academies.ayi.cataloguemovie.entity.MovieModel;
import com.dicoding.academies.ayi.cataloguemovie.R;
import com.dicoding.academies.ayi.cataloguemovie.entity.NewMovieModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieItemActivity extends AppCompatActivity {
    public static String EXTRA_MOVIE = "extra_movie";
    public static String IS_FAVORITE = "is_favorite";
    //@BindView(R.id.title) TextView title;
    @BindView(R.id.popularity) TextView popularity;
    @BindView(R.id.overview) TextView overview;
    @BindView(R.id.release_date) TextView release_note;
    @BindView(R.id.btn_favorite)
    Button btnFovorite;
//    @BindView(R.id.img_banner)
//    ImageView imageBanner;
//    @BindView(R.id.img_movie)
//    ImageView imgMovie;
    Context context;
    private FavoriteHelper favoriteHelper;
    private boolean isFavorite = false;
    private int favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_item);
        ButterKnife.bind(this);
        final MovieModel mMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        //title.setText(mMovie.getTitle());
        release_note.setText(mMovie.getRelease_date());
        popularity.setText(mMovie.getPopulariry());
        overview.setText(mMovie.getOverview());
        ImageView imageBanner = (ImageView) findViewById(R.id.img_banner);
        ImageView imgMovie = (ImageView) findViewById(R.id.img_movie);
        Picasso.with(this).load(mMovie.getBanner()).into(imageBanner);
        Picasso.with(this).load(mMovie.getPoster()).into(imgMovie);
        favoriteHelper = new FavoriteHelper(this);
        favoriteHelper.open();

        favorite = getIntent().getIntExtra(IS_FAVORITE, 0);
        if (favorite == 1){
            isFavorite = true;
            btnFovorite.setText("hapus favorite");
        }

        btnFovorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFavorite){
                    favoritkan(mMovie);
                    Toast.makeText(MovieItemActivity.this, "Berhasil Difavoritkan", Toast.LENGTH_LONG).show();
                }else {
                    deleteFavorite(mMovie);
                    Toast.makeText(MovieItemActivity.this, "Favorite Dihapus", Toast.LENGTH_LONG).show();
                }
            }
        });

        getSupportActionBar().setTitle(mMovie.getTitle());
    }

    private void favoritkan(MovieModel mMovie){
        Favorite favorites = new Favorite();
        favorites.setTitle(mMovie.getTitle());
        favorites.setOverview(mMovie.getOverview());
        favorites.setPopulariry(mMovie.getPopulariry());
        favorites.setRelease_date(mMovie.getRelease_date());
        favorites.setPoster(mMovie.getPoster());
        favorites.setBanner(mMovie.getBanner());

        favoriteHelper.insert(favorites);
        //favoriteHelper.close();
    }

    private void deleteFavorite(MovieModel mMovie){
        favoriteHelper.delete(mMovie.getId());
    }
}
