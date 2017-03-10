package com.example.nur.derplist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nur.derplist.R;
import com.example.nur.derplist.model.Inspiration;

import java.util.List;

/**
 * Created by Nur on 2/7/2017.
 */

public class InspirationAdapter extends RecyclerView.Adapter<InspirationAdapter.MyViewHolder> {

    private List<Inspiration> inspirationList;

    private ItemClickCallback itemClickCallback;

    public InspirationAdapter(List<Inspiration> inspirationList) {
        this.inspirationList = inspirationList;
    }

    public void setInspirationList(List<Inspiration> inspirationList) {
        this.inspirationList.clear();
        this.inspirationList = inspirationList;
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Inspiration inspiration = inspirationList.get(position);

        holder.name.setText(inspiration.getName());
        holder.content.setText(inspiration.getContent());

        if (inspiration.isBookmark()) {
            holder.bookmark.setImageResource(R.drawable.ic_star_black_24dp);
        } else {
            holder.bookmark.setImageResource(R.drawable.ic_star_border_black_24dp);
        }
    }

    @Override
    public int getItemCount() {
        return inspirationList.size();
    }

    public interface ItemClickCallback {
        void onItemClick(int position);
        void ontBookmarkClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView icon, bookmark;
        private TextView name, content;
        private View container;

        public MyViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.iv_item_icon);
            bookmark = (ImageView) itemView.findViewById(R.id.iv_item_bookmark);
            name = (TextView) itemView.findViewById(R.id.tv_item_name);
            content = (TextView) itemView.findViewById(R.id.tv_item_content);
            container = (View) itemView.findViewById(R.id.cont_item_root);

            bookmark.setOnClickListener(this);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.cont_item_root) {
                itemClickCallback.onItemClick(getAdapterPosition());
            } else {
                itemClickCallback.ontBookmarkClick(getAdapterPosition());
            }
        }
    }
}
