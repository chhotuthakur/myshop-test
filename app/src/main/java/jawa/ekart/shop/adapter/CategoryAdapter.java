package jawa.ekart.shop.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import jawa.ekart.shop.R;
import jawa.ekart.shop.fragment.SubCategoryFragment;
import jawa.ekart.shop.helper.Constant;
import jawa.ekart.shop.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    public ArrayList<Category> categorylist;
    int layout;
    Activity activity;
    Context context;


    public CategoryAdapter(Context context, Activity activity, ArrayList<Category> categorylist, int layout) {
        this.context = context;
        this.categorylist = categorylist;
        this.layout = layout;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Category model = categorylist.get(position);
        holder.txttitle.setText(model.getName());
        holder.imgcategory.setDefaultImageResId(R.drawable.placeholder);
        holder.imgcategory.setErrorImageResId(R.drawable.placeholder);
        holder.imgcategory.setImageUrl(model.getImage(), Constant.imageLoader);

        holder.lytMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SubCategoryFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", model.getId());
                bundle.putString("name", model.getName());
                fragment.setArguments(bundle);
                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categorylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txttitle;
        NetworkImageView imgcategory;
        LinearLayout lytMain;

        public ViewHolder(View itemView) {
            super(itemView);
            lytMain = itemView.findViewById(R.id.lytMain);
            imgcategory = itemView.findViewById(R.id.imgcategory);
            txttitle = itemView.findViewById(R.id.txttitle);
        }

    }
}
