package id.trafood.trafood.Home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.trafood.trafood.Models.Article;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.Connect;

/**
 * Created by kulinerin 1 on 30/10/2017.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.Myholders> {
    List<Article> mArtcile;

    public ArticleAdapter(List<Article> mArtcile) {
        this.mArtcile = mArtcile;
    }

    @Override
    public Myholders onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_article, parent, false);
        Myholders hollder = new Myholders(view);
        return hollder;
    }

    @Override
    public void onBindViewHolder(Myholders holder, int position) {
        Picasso.with(holder.ivFoto.getContext()).load(Connect.IMAGE_ARTICLE+mArtcile.get(position).getImage())
                .error(R.mipmap.ic_launcher).into(holder.ivFoto);
        holder.tvTitle.setText(mArtcile.get(position).getTitle());
        holder.tvCategory.setText(mArtcile.get(position).getCategory());
        holder.tvWriter.setText(mArtcile.get(position).getNama());
    }

    @Override
    public int getItemCount() {
        return mArtcile.size();
    }

    public class Myholders extends RecyclerView.ViewHolder {
        public ImageView ivFoto;
        public TextView tvTitle, tvCategory, tvWriter;
        public Myholders(View itemView) {
            super(itemView);
            ivFoto = (ImageView) itemView.findViewById(R.id.ivArticle);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitleArticle);
            tvCategory = (TextView) itemView.findViewById(R.id.tvCategoryArticle);
            tvWriter = (TextView) itemView.findViewById(R.id.tvWriterArticle);
        }
    }
}
