package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<NewsHeadlines> headlines;
    private SelectListener listener;

    public CustomAdapter(Context context, List<NewsHeadlines> headlines, SelectListener listener) {
        this.context = context;
        this.headlines = headlines;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        // Gọi các chế độ xem văn bản và hình ảnh
        holder.text_title.setText(headlines.get(position).getTitle());
        holder.text_source.setText(headlines.get(position).getSource().getName());

        // Kiểm tra URL hình ảnh có trống hay không
        if (headlines.get(position).getUrlToImage() != null) {
            Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.img_headline);
        }

        // Lưu position vào một biến cục bộ
        final int currentPosition = position; // Sử dụng biến cục bộ để giữ giá trị hiện tại

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sử dụng currentPosition để gọi listener
                listener.OnNewsClicked(headlines.get(currentPosition));
            }
        });
    }


    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
