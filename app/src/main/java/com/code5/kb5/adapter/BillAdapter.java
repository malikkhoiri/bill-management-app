package com.code5.kb5.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.code5.kb5.R;
import com.code5.kb5.model.bill.BillData;
import com.code5.kb5.utils.ThousandSeparator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<BillData> billData;
    private List<BillData> billDataFiltered;
    private BillAdapterListener listener;

    public BillAdapter(Context context, List<BillData> billData, BillAdapterListener listener) {
        this.context = context;
        this.billData = billData;
        this.billDataFiltered = billData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bill, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        try {
            String[] strDate = billDataFiltered.get(i).getUpdatedAt().split(" ");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.ENGLISH);
            Date date = format.parse(strDate[0]);
            format.applyPattern("EEE, d MMM yyyy");
            viewHolder.tvCreatedDate.setText(format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String amount = "Rp. " + ThousandSeparator.createCurrency(String.valueOf(billDataFiltered.get(i).getTotalTag()));

        viewHolder.tvBill.setText(billDataFiltered.get(i).getNamaTag());
        viewHolder.tvAmount.setText(amount);
    }

    @Override
    public int getItemCount() {
        return billDataFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    billDataFiltered = billData;
                } else {
                    List<BillData> filteredList = new ArrayList<>();

                    for (BillData row : billData) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getNamaTag().toLowerCase().matches("(?i).*" + charString.toLowerCase() + ".*")) {
                            filteredList.add(row);
                        }
                    }

                    billDataFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = billDataFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                billDataFiltered = (ArrayList<BillData>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface BillAdapterListener{
        void onBillSelected(BillData billData);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_create_date) TextView tvCreatedDate;
        @BindView(R.id.tv_bill) TextView tvBill;
        @BindView(R.id.tv_amount) TextView tvAmount;
        @BindView(R.id.tv_bill_per_person) TextView tvBillPerPerson;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onBillSelected(billDataFiltered.get(getAdapterPosition()));
                }
            });
        }
    }
}
