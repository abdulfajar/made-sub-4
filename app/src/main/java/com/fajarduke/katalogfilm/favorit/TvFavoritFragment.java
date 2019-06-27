package com.fajarduke.katalogfilm.favorit;


import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.fajarduke.katalogfilm.R;

import static com.fajarduke.katalogfilm.db.DatabaseTvContract.TvColumns.CONTENT_URI_TV;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvFavoritFragment extends Fragment {

    private ProgressBar progressBar;
    private FavoritTvAdapter adapter;
    private RecyclerView rvFav;
    private Cursor listFavorite;


    public TvFavoritFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_favorit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBarFavTv);

        adapter = new FavoritTvAdapter(getContext());
        adapter.setListFavorit(listFavorite);
        adapter.notifyDataSetChanged();
        rvFav = view.findViewById(R.id.rv_fav_tv);
        rvFav.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFav.setAdapter(adapter);

        new LoadNoteAsync().execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        new LoadNoteAsync().execute();
    }

    private class LoadNoteAsync extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected Cursor doInBackground(Void... voids) {
//            getContext().getContentResolver()
            return getContext().getContentResolver().query(CONTENT_URI_TV, null, null, null, null);

        }

        @Override
        protected void onPostExecute(Cursor favorite) {
            super.onPostExecute(favorite);
            progressBar.setVisibility(View.GONE);

            listFavorite = favorite;
            adapter.setListFavorit(listFavorite);
            adapter.notifyDataSetChanged();

            // Tidak error
//            if (listFavorite != null){
//                if (listFavorite.getCount() == 0) {
//                    Snackbar.make(rvFav, "Tidak ada data saat ini", Snackbar.LENGTH_SHORT).show();
//                }
//            }

            // Error disini
            if (listFavorite.getCount() == 0) {
                Snackbar.make(rvFav, "Tidak ada data saat ini", Snackbar.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
