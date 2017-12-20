package id.trafood.trafood.Rumahmakan.Adapter;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.trafood.trafood.DetailMenu;
import id.trafood.trafood.Models.Menu;
import id.trafood.trafood.Models.MenuDetail;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.Connect;
import id.trafood.trafood.Rest.RestApi;
import id.trafood.trafood.SharedPrefManager;

/**
 * Created by kulinerin 1 on 18/10/2017.
 */

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.MyHolder> {

    List<Menu> mMenu;

    public MenuListAdapter(List<Menu> mMenu) {
        this.mMenu = mMenu;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.litsview_rm_listmenu,parent,false);
        MyHolder myHolder = new MyHolder(v);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.tvLike.setText(mMenu.get(position).getLikes());
        holder.tvHargamenu.setText(mMenu.get(position).getHarga());
        holder.tvNamamemu.setText(mMenu.get(position).getNamamenu());
        holder.pathfoto.setText(mMenu.get(position).getFoto());
        holder.idmenu.setText(mMenu.get(position).getMenuid());
        Picasso.with(holder.ivFotomenu.getContext()).load(Connect.IMAGE_MENU_URL+mMenu.get(position).getFoto())
                .error(R.mipmap.ic_launcher).into(holder.ivFotomenu);

        holder.ivFotomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailMenu.class);
                intent.putExtra("MENUID", mMenu.get(position).getMenuid());
                intent.putExtra("FOTOMENU", mMenu.get(position).getFoto());
                intent.putExtra("HARGA", mMenu.get(position).getHarga());
                intent.putExtra("NAMAMENU", mMenu.get(position).getNamamenu());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMenu.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView tvHargamenu, tvNamamemu,tvLike,idmenu, pathfoto;
        public TextView qtymenu, subtotalmenu, btnPesanmenu, btnBatalpesan, btnJadipesan;
        public LinearLayout linearLayout,linearHarga;
        public ImageView ivFotomenu;
        RestApi restApi;
        SharedPrefManager sharedPrefManager;
        public MyHolder(final View itemView) {
            super(itemView);

            tvLike = (TextView) itemView.findViewById(R.id.tvLikeMenus);
            tvHargamenu = (TextView) itemView.findViewById(R.id.tvHargaMenuRm);
            ivFotomenu =(ImageView) itemView.findViewById(R.id.ivMenuRM);
            tvNamamemu = (TextView) itemView.findViewById(R.id.tvNamaMenuRm);
            qtymenu = (TextView) itemView.findViewById(R.id.tvQtyMenu);
            subtotalmenu = (TextView) itemView.findViewById(R.id.tvSubtotalMenu);
            btnPesanmenu = (TextView) itemView.findViewById(R.id.tvBtnPesanMenu);
            btnBatalpesan = (TextView) itemView.findViewById(R.id.tvBatalPesan);
            btnJadipesan = (TextView) itemView.findViewById(R.id.tvJadiPesan);
            idmenu = (TextView) itemView.findViewById(R.id.tvIdMenurm);
            pathfoto = (TextView) itemView.findViewById(R.id.tvPathFotorm);
            sharedPrefManager = new SharedPrefManager(itemView.getContext());

            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearPesan);
            linearHarga = (LinearLayout) itemView.findViewById(R.id.linearHarga);
            restApi = ApiClient.getClient().create(RestApi.class);

            //qtymenu.setVisibility(View.GONE);
            linearHarga.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);

            btnPesanmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            btnPesanmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    final Dialog dialog = new Dialog(view.getContext());
                    dialog.setContentView(R.layout.dialog_pesan);
                    dialog.setCancelable(false);
                    ImageView imageView = (ImageView) dialog.findViewById(R.id.imageDialog);
                    final TextView tvHargaD = (TextView) dialog.findViewById(R.id.tvHargaDialog);
                    TextView tvNamamemuD = (TextView) dialog.findViewById(R.id.tvNamaMenuDialog);
                    final TextView tvLanjutkan = (TextView) dialog.findViewById(R.id.btnPilihDialog);
                    final Button btnNext = (Button) dialog.findViewById(R.id.btnNextDialog);
                    ImageButton imageButton = (ImageButton) dialog.findViewById(R.id.deleteDialog);
                    final TextView totalharga = (TextView) dialog.findViewById(R.id.tvTotalpriceDialog);
                    //final Spinner spinner = (Spinner) dialog.findViewById(R.id.spinnerDialog);
                    final EditText etCatatanDialog = (EditText) dialog.findViewById(R.id.etCatatanDialog);
                    final Button plusCart = (Button) dialog.findViewById(R.id.btnPlusDialogPesan);
                    final Button minCart = (Button) dialog.findViewById(R.id.btnMinDialogPesan);
                    final TextView tvQtyDialogPesan = (TextView) dialog.findViewById(R.id.tvQtyDialogPesan);

                    tvLanjutkan.setVisibility(View.GONE);
                    btnNext.setText("Pesan");
                    Picasso.with(view.getContext()).load(Connect.IMAGE_MENU_URL+pathfoto.getText().toString())
                            .into(imageView);
                    tvNamamemuD.setText(tvNamamemu.getText().toString());
                    tvQtyDialogPesan.setText(qtymenu.getText().toString());
                    tvHargaD.setText(tvHargamenu.getText().toString());

                    String sumStr = tvQtyDialogPesan.getText().toString();
                    int sum = Integer.valueOf(sumStr);
                    if (sum <= 1){
                        minCart.setEnabled(false);
                    }

                    plusCart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String sumStr = qtymenu.getText().toString();
                            int sum = Integer.valueOf(sumStr);
                            sum += 1;
                            String sumStra = String.valueOf(sum);
                            tvQtyDialogPesan.setText(sumStra);
                            qtymenu.setText(sumStra);

                            String harga = tvHargaD.getText().toString();
                            int hargas = Integer.parseInt(harga);
                            int kali = hargas*sum;
                            subtotalmenu.setText(String.valueOf(kali));
                            totalharga.setText(String.valueOf(kali));

                            if (sumStra.equals(sumStr)){
                                linearLayout.setVisibility(View.GONE);
                            }
                            minCart.setEnabled(true);
                            if (sumStra.equals("1")){
                                minCart.setEnabled(false);
                            }
                        }
                    });

                   minCart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String sumStr = qtymenu.getText().toString();
                            int sum = Integer.valueOf(sumStr);
                            sum -= 1;
                            String sumStra = String.valueOf(sum);
                            tvQtyDialogPesan.setText(sumStra);
                            qtymenu.setText(sumStra);

                            String harga = tvHargaD.getText().toString();
                            int hargas = Integer.parseInt(harga);
                            int kali = hargas*sum;
                            subtotalmenu.setText(String.valueOf(kali));
                            totalharga.setText(String.valueOf(kali));

                            if (sumStra.equals(sumStr)){
                                linearLayout.setVisibility(View.GONE);
                            }
                            if (sumStra.equals("1")){
                                minCart.setEnabled(false);
                            }

                        }
                    });

                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            qtymenu.setText("1");
                            dialog.dismiss();
                        }
                    });

                    btnNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String qty = tvQtyDialogPesan.getText().toString();
                            String userid = sharedPrefManager.getSpUserid();
                            String menuid = idmenu.getText().toString();
                            String subtotal = totalharga.getText().toString();
                            String notes = etCatatanDialog.getText().toString();

                            linearHarga.setVisibility(View.VISIBLE);
                            linearLayout.setVisibility(View.VISIBLE);
                            btnPesanmenu.setVisibility(View.GONE);

                            qtymenu.setText(qty);
                            subtotalmenu.setText(subtotal);
                            dialog.dismiss();

                        }
                    });


                    dialog.show();



                }
            });

            btnBatalpesan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    linearHarga.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                    btnPesanmenu.setVisibility(View.VISIBLE);
                    qtymenu.setText("1");


                }
            });



        }




    }
}
