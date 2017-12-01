package id.trafood.trafood.Home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import id.trafood.trafood.Models.Article;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.Connect;

/**
 * Created by kulinerin 1 on 01/12/2017.
 */

public class ArticleUserAdapater extends RecyclerView.Adapter<ArticleUserAdapater.MyHolda> {
    List<Article> mArticle;

    public ArticleUserAdapater(List<Article> mArticle) {
        this.mArticle = mArticle;
    }

    @Override
    public ArticleUserAdapater.MyHolda onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_article_user,parent,false);
        MyHolda holda = new MyHolda(view);
        return holda;
    }

    @Override
    public void onBindViewHolder(ArticleUserAdapater.MyHolda holder, int position) {
        Glide.with(holder.imageView.getContext()).load(Connect.IMAGE_ARTICLE+mArticle.get(position).getImage())
                .into(holder.imageView);
        holder.judul.setText(mArticle.get(position).getTitle());
        holder.tanggal.setText(mArticle.get(position).getCreated());
    }

    @Override
    public int getItemCount() {
        return mArticle.size();
    }

    public class MyHolda extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView judul, tanggal;
        public MyHolda(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.ivFotoArticlr);
            judul = (TextView) itemView.findViewById(R.id.tvJudulArticle);
            tanggal = (TextView) itemView.findViewById(R.id.tvWaktuArticle);

        }
    }
}
