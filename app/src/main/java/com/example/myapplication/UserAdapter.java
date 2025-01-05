package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = userList.get(position);

        holder.nameTextView.setText("Name: " + user.getName());
        holder.usernameTextView.setText("Username: " + user.getUsername());
        holder.emailTextView.setText("Email: " + user.getEmail());
        holder.phoneTextView.setText("Phone: " + user.getPhone());
        holder.websiteTextView.setText("Website: " + user.getWebsite());

        User.Address address = user.getAddress();
        holder.addressTextView.setText("Address: " + address.getStreet() + ", " + address.getSuite() + ", " + address.getCity() + ", " + address.getZipcode());

        User.Company company = user.getCompany();
        holder.companyTextView.setText("Company: " + company.getName() + "\nCatch Phrase: " + company.getCatchPhrase() + "\nBS: " + company.getBs());

        User.Address.Geo geo = address.getGeo();
        holder.geoTextView.setText("Location: Latitude: " + geo.getLat() + ", Longitude: " + geo.getLng());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, usernameTextView, emailTextView, phoneTextView, websiteTextView, addressTextView, companyTextView, geoTextView;

        public UserViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            phoneTextView = itemView.findViewById(R.id.phoneTextView);
            websiteTextView = itemView.findViewById(R.id.websiteTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            companyTextView = itemView.findViewById(R.id.companyTextView);
            geoTextView = itemView.findViewById(R.id.geoTextView);
        }
    }
}
