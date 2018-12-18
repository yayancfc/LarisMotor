package com.yayanheryanto.larismotor.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yayanheryanto.larismotor.model.Transaksi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import de.codecrafters.tableview.TableDataAdapter;

public class LaporanAdapter extends TableDataAdapter<Transaksi> {

    public LaporanAdapter(Context context, List<Transaksi> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Transaksi transaksi = getRowData(rowIndex);
        View renderedView = null;
        switch (columnIndex) {
            case 0:
                renderedView = renderNomor(transaksi);
                break;
            case 1:
                renderedView = renderTanggal(transaksi);
                break;
            case 2:
                renderedView = renderNoMesin(transaksi);
                break;
            case 3:
                renderedView = renderPembeli(transaksi);
                break;
            case 4:
                renderedView = renderSales(transaksi);
                break;
            case 5:
                renderedView = renderKondisi(transaksi);
                break;
            case 6:
                renderedView = renderDp(transaksi);
                break;

        }

        return renderedView;
    }

    private View renderNomor(final Transaksi transaksi) {
        return renderString(transaksi.getNomor()+"");
    }



    private View renderTanggal(final Transaksi transaksi) {

        String hasil = "" ;

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", new Locale("ID"));
        SimpleDateFormat sqlformat = new SimpleDateFormat("yyyy-MM-dd", new Locale("EN"));
        try {
            hasil = df.format(sqlformat.parse(transaksi.getTanggal()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return renderString(hasil);
    }

    private View renderNoMesin(final Transaksi transaksi) {
        String nopol = transaksi.getNopol();
        String hasil = nopol;

        if (hasil == null)
            hasil = transaksi.getNosin();

        return renderString(hasil);

    }

    private View renderPembeli(final Transaksi transaksi) {
        return renderString(transaksi.getPembeli());
    }


    private View renderSales(final Transaksi transaksi) {
        return renderString(transaksi.getSales());
    }

    private View renderKondisi(final Transaksi transaksi) {

        String hasil = "baru";

        if (transaksi.getKondisi().equals("0"))
            hasil = "bekas";

        return renderString(hasil);
    }

    private View renderDp(final Transaksi transaksi) {
        String hasil = "kredit";

        if (transaksi.getDp() == null)
            hasil = "cash";

        return renderString(hasil);
    }


    private View renderString(final String value) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(15);
        textView.setTextColor(Color.BLACK);
        return textView;
    }
}
