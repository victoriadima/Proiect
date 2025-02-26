package com.example.proiectfinal.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proiectfinal.R;
import com.example.proiectfinal.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movieList;

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.titleTextView.setText(movie.getTitle());
        holder.ratingTextView.setText(String.valueOf(movie.getRating()));
        holder.movieImageView.setImageResource(movie.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        Log.d("MovieLog", "Adapter item count: " + (movieList != null ? movieList.size() : 0));
        return movieList != null ? movieList.size() : 0;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView ratingTextView;
        ImageView movieImageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.movie_title);
            ratingTextView = itemView.findViewById(R.id.movie_rating);
            movieImageView = itemView.findViewById(R.id.movie_image);
        }
    }

    public void addMovie(Movie movie) {
        if (!movieList.contains(movie)) {
            movieList.add(movie);
            notifyItemInserted(movieList.size() - 1);
        } else {
            Log.d("MovieLog", "Movie already exists: " + movie.getTitle());
        }
    }

    public void updateMovies(List<Movie> newMovies) {
        movieList.clear();
        movieList.addAll(newMovies);
        notifyDataSetChanged();
    }
}





