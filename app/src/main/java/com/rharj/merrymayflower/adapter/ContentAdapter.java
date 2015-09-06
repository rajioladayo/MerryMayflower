package com.rharj.merrymayflower.adapter;

import android.widget.ArrayAdapter;

import com.rharj.merrymayflower.R;
import com.rharj.merrymayflower.model.XmlValueModels;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raji Oladayo on 2/4/2015.
 */
public class ContentAdapter extends ArrayAdapter<XmlValueModels> implements Filterable{

    private List<XmlValueModels> rList;
    private List<XmlValueModels> mOriginalValues;
    private Context context;
    private int resource;
    private TitleFilter filter;
    View v;

    public ContentAdapter(Context context, int resource,List<XmlValueModels> rList)
    {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.mOriginalValues = new ArrayList<XmlValueModels>();
        this.mOriginalValues.addAll(rList);

        this.rList = new ArrayList<XmlValueModels>();
        this.rList.addAll(rList);
    }

   @Override
    public int getCount()
    {
        return rList.size();
    }

    @Override
    public XmlValueModels getItem(int position)
    {
        return rList.get(position);
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

        final XmlValueModels xModel = this.rList.get(position);

        String title = xModel.getTitle();

       ((TextView) v.findViewById(R.id.row_titles)).setText(title);

        return v;
    }

   public void addToList(XmlValueModels s)
    {
        rList.add(s);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter  = new TitleFilter();
        }
        return filter;
    }

    private class TitleFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if(constraint != null && constraint.toString().length() > 0)
            {
                ArrayList<XmlValueModels> filteredItems = new ArrayList<XmlValueModels>();

                for(int i = 0, l = mOriginalValues.size(); i < l; i++)
                {
                    XmlValueModels xmlVal = mOriginalValues.get(i);
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

            rList = (ArrayList<XmlValueModels>)results.values;
            notifyDataSetChanged();
            clear();
            for(int i = 0, l = rList.size(); i < l; i++)
                add(rList.get(i));
            notifyDataSetInvalidated();
        }
    }


}


