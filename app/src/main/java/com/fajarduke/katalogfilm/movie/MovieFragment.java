package com.fajarduke.katalogfilm.movie;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fajarduke.katalogfilm.DetailActivity;
import com.fajarduke.katalogfilm.ItemClickSupport;
import com.fajarduke.katalogfilm.MovieViewModel;
import com.fajarduke.katalogfilm.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private ProgressBar progressBar;
    private MovieAdapter adapter;
    private MovieViewModel mainViewModel;
    private RecyclerView rvMovie;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        mainViewModel.getListMovie().observe(this, getData);

        progressBar = view.findViewById(R.id.progressBar);

        adapter = new MovieAdapter();
        adapter.notifyDataSetChanged();
        rvMovie = view.findViewById(R.id.rv_movie);
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovie.setAdapter(adapter);
        mainViewModel.setListMovie();
        showLoading(true);
    }

    private Observer<ArrayList<MovieItem>> getData = new Observer<ArrayList<MovieItem>>() {
        @Override
        public void onChanged(final ArrayList<MovieItem> listItems) {
            if (listItems != null) {
                adapter.setData(listItems);
                showLoading(false);
                ItemClickSupport.addTo(rvMovie).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Toast.makeText(getContext(), "Kamu memilih "+listItems.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), DetailActivity.class);

                        intent.putExtra(DetailActivity.EXTRA_DATA, listItems.get(position));
                        intent.putExtra(DetailActivity.EXTRA_NAME, 1);
                        startActivity(intent);
                    }
                });
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
