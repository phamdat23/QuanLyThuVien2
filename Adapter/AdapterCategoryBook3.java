package vn.edu.poly.duanmau.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.poly.duanmau.DAO.DAO_Book;
import vn.edu.poly.duanmau.DAO.DAO_BookCategory;
import vn.edu.poly.duanmau.Model.Book;
import vn.edu.poly.duanmau.Model.Category;
import vn.edu.poly.duanmau.R;
import vn.edu.poly.duanmau.ViewHoder.ViewHoderBook;

public class AdapterCategoryBook3 extends RecyclerView.Adapter<ViewHoderBook> {

    DAO_Book dao_book;
    ArrayList<Book> listBook3;
    ArrayList<Category> listCategory;
    DAO_BookCategory dao_bookCategory;
    Context context;

    public AdapterCategoryBook3(DAO_Book dao_book, ArrayList<Book> listBook3, ArrayList<Category> listCategory, DAO_BookCategory dao_bookCategory) {
        this.dao_book = dao_book;
        this.listBook3 = listBook3;
        this.listCategory = listCategory;
        this.dao_bookCategory = dao_bookCategory;
    }

    @NonNull
    @Override
    public ViewHoderBook onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View row = layoutInflater.inflate(R.layout.item_book, parent, false);
        ViewHoderBook viewHoderBook = new ViewHoderBook(row);
        context = parent.getContext();
        dao_book = new DAO_Book(context);
        dao_bookCategory = new DAO_BookCategory(context);
        return viewHoderBook;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderBook holder, int position) {
        final int index = position;
        dao_book.opend();
        dao_bookCategory.opend();
        listCategory = dao_bookCategory.selectAll();
        listBook3 = dao_book.selectCategory3(listCategory);
        Book book = listBook3.get(index);
        holder.tvTitleBook.setText(book.getTitleBook());
        holder.tvPriceBook.setText("Gi?? m?????n: " + book.getGia() + "$");
        holder.tvChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogChiTiet(book);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBook3 == null ? 0 : listBook3.size();
    }

    private TextView tvIdSach;
    private TextView tvTitleSach;
    private TextView tvTheLoai;
    private TextView tvTacGia;
    private TextView tvSoLuong;
    private TextView tvGia;

    public void dialogChiTiet(Book obj) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.layout_chi_tiet_sach);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        tvIdSach = dialog.findViewById(R.id.tv_idSach);
        tvTitleSach = dialog.findViewById(R.id.tv_titleSach);
        tvTheLoai = dialog.findViewById(R.id.tv_the_loai);
        tvTacGia = dialog.findViewById(R.id.tv_tacGia);
        tvSoLuong = dialog.findViewById(R.id.tv_soLuong);
        tvGia = dialog.findViewById(R.id.tv_gia);
        tvIdSach.setText("M?? s??ch: " + obj.getIdBook());
        tvTitleSach.setText("T??n s??ch: " + obj.getTitleBook());
        tvTheLoai.setText("Th??? lo???i: " + obj.getIdCategory() + "|" + obj.getTitleCategory());
        tvTacGia.setText("T??c gi???: " + obj.getTacGia());
        tvSoLuong.setText("S??? l?????ng s???n c??: " + obj.getSoLuong());
        tvGia.setText("Gi?? m?????n: " + obj.getGia() + "$");
        dialog.show();
    }
}
