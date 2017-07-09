package com.nelsonsachse.aroundme.feature;

import com.nelsonsachse.aroundme.R;
import com.nelsonsachse.aroundme.api.data.Venue;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Venue> venues = new ArrayList<>();

    @Override public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_venue, parent, false);

        return new VenueViewHolder(view);
    }

    @Override public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((VenueViewHolder) holder).bind(venues.get(position));
    }

    @Override public int getItemCount() {
        return venues.size();
    }

    public void setData(List<Venue> items) {
        venues.clear();
        venues.addAll(items);
        notifyDataSetChanged();
    }

    static class VenueViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.venue_textview_name)
        TextView name;
        @BindView(R.id.venue_textview_address)
        TextView address;
        @BindView(R.id.venue_textview_postcode)
        TextView postcode;
        @BindView(R.id.venue_textview_rating)
        TextView rating;

        VenueViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(final Venue venue) {
            name.setText(venue.getName());
            address.setText(venue.getLocation().getAddress());
            postcode.setText(venue.getLocation().getPostalCode());
            rating.setText(Double.toString(venue.getRating()));
        }
    }
}
