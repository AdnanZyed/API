package com.example.myapplication;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        userList = new ArrayList<>();
        userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);

        loadUsers();
    }

    private void loadUsers() {
        String url = "https://jsonplaceholder.typicode.com/users";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject userJson = response.getJSONObject(i);
                                User user = new User();
                                user.setId(userJson.getInt("id"));
                                user.setName(userJson.getString("name"));
                                user.setUsername(userJson.getString("username"));
                                user.setEmail(userJson.getString("email"));

                                JSONObject addressJson = userJson.getJSONObject("address");
                                User.Address address = new User.Address();
                                address.setStreet(addressJson.getString("street"));
                                address.setSuite(addressJson.getString("suite"));
                                address.setCity(addressJson.getString("city"));
                                address.setZipcode(addressJson.getString("zipcode"));

                                JSONObject geoJson = addressJson.getJSONObject("geo");
                                User.Address.Geo geo = new User.Address.Geo();
                                geo.setLat(geoJson.getString("lat"));
                                geo.setLng(geoJson.getString("lng"));
                                address.setGeo(geo);

                                user.setAddress(address);

                                JSONObject companyJson = userJson.getJSONObject("company");
                                User.Company company = new User.Company();
                                company.setName(companyJson.getString("name"));
                                company.setCatchPhrase(companyJson.getString("catchPhrase"));
                                company.setBs(companyJson.getString("bs"));

                                user.setCompany(company);

                                user.setPhone(userJson.getString("phone"));
                                user.setWebsite(userJson.getString("website"));

                                userList.add(user);
                            }
                            userAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error parsing data", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error loading data", Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }
}
