package com.fajarduke.katalogfilm.tv;


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
public class TvFragment extends Fragment {
    private ProgressBar progressBar;
    private TvAdapter adapter;
    private MovieViewModel mainViewModel;
    private RecyclerView rvTv;

    public TvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        mainViewModel.getListTv().observe(this, getData);

        progressBar = view.findViewById(R.id.progressBarTv);

        adapter = new TvAdapter();
        adapter.notifyDataSetChanged();
        rvTv = view.findViewById(R.id.rv_tv);
        rvTv.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTv.setAdapter(adapter);

        mainViewModel.setListTv();
        showLoading(true);
    }

    private Observer<ArrayList<TvItem>> getData = new Observer<ArrayList<TvItem>>() {
        @Override
        public void onChanged(final ArrayList<TvItem> listItems) {
            if (listItems != null) {
                adapter.setData(listItems);
                showLoading(false);
                ItemClickSupport.addTo(rvTv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Toast.makeText(getContext(), "Kamu memilih "+ listItems.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), DetailActivity.class);

                        intent.putExtra(DetailActivity.EXTRA_DATA, listItems.get(position));
                        intent.putExtra(DetailActivity.EXTRA_NAME, 2);
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
