package com.rharj.merrymayflower.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.rharj.merrymayflower.R;
import com.rharj.merrymayflower.model.Favorite;

/**
 * Created by USER on 4/16/15.
 */
public class FavoriteAdapter extends ArrayAdapter<Favorite> implements Filterable {

    private List<Favorite> rList;
    private List<Favorite> mOriginalValues;
    private Context context;
    private int resource;
    private TitleFilter filter;
    View v;

    public FavoriteAdapter(Context context, int resource, List<Favorite> rList) {

        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.mOriginalValues = new ArrayList<Favorite>();
        this.mOriginalValues.addAll(rList);

        this.rList = new ArrayList<Favorite>();
        this.rList.addAll(rList);

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        v = convertView;
        if (convertView == null)
        {
            LayoutInflater vi = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(this.resource, null);
        }

        final Favorite favorite = this.rList.get(position);

        String title = favorite.getTitle();

        ((TextView) v.findViewById(R.id.favorite_titles)).setText(title);

        return v;
    }

    @Override
    public int getCount()
    {
        return rList.size();
    }

    @Override
    public Favorite getItem(int position)
    {
        return rList.get(position);
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public void addToList(Favorite s)
    {
        rList.add(s);
        notifyDataSetChanged();
    }

    private class TitleFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if(constraint != null && constraint.toString().length() > 0)
            {
                ArrayList<Favorite> filteredItems = new ArrayList<Favorite>();

                for(int i = 0, l = mOriginalValues.size(); i < l; i++)
                {
                    Favorite xmlVal = mOriginalValues.get(i);
                    if(xmlVal.toString().toLowerCase().contains(constraint))
                        filteredItems.add(xmlVal);
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            }
            else
            {
                synchronized(this)
                {
                    result.values = mOriginalValues;
                    result.count = mOriginalValues.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            rList = (ArrayList<Favorite>)results.values;
            notifyDataSetChanged();
            clear();
            for(int i = 0, l = rList.size(); i < l; i++)
                add(rList.get(i));
            notifyDataSetInvalidated();
        }
    }
}
