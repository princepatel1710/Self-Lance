package com.example.self_lance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


class HelplineDiffUtil extends DiffUtil.ItemCallback<UserInformation> {

    @Override
    public boolean areItemsTheSame(@NonNull UserInformation oldItem, @NonNull UserInformation newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull UserInformation oldItem, @NonNull UserInformation newItem) {
        return false;
    }
}

public class HelplineAdapter extends ListAdapter<UserInformation, HelplineAdapter.ViewHolder> {

    private ItemClickCallback callback;

    public HelplineAdapter(ItemClickCallback callback) {
        super(new HelplineDiffUtil());
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hire_freelancer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        UserInformation mBean = getItem(position);
        holder.tvName.setText(mBean.getFirstName() + " " + mBean.getLastName());
        holder.tvDesc.setText(mBean.getDescription());
        holder.tvPrice.setText(mBean.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onItemClick(position);
            }
        });
    }

    public UserInformation getCurrentItem(int position) {
        return getItem(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDesc, tvPrice;

        public ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvUserName);
            tvDesc = view.findViewById(R.id.tvDesc);
            tvPrice = view.findViewById(R.id.tvPrice);
        }
    }


}
