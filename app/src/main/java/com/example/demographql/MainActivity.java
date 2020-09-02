package com.example.demographql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // First, create an `ApolloClient`
// Replace the serverUrl with your GraphQL endpoint
        ApolloClient apolloClient = ApolloClient.builder()
                .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/")
                .build();

// Then enqueue your query
        apolloClient.query(new LaunchListQuery())
                .enqueue(new ApolloCall.Callback<LaunchListQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<LaunchListQuery.Data> response) {
                        Log.e("Apollo", "Launch site: " + response.getData().launches.launches.get(1));
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        Log.e("Apollo", "Error", e);
                    }
                });
    }
}