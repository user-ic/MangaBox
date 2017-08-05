package com.sylach.mangacube;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;

import adapter.MangaListAdapter;
import model.Endpoint;
import model.MangaStack;
import model.TempBase;
import volley.RequestManager;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener {


    Boolean exit = false;
    TempBase tempBase;

    ArrayList<MangaStack> mangaStack;
    //
    SwipeRefreshLayout refreshLayoutMain;
    RecyclerView recyclerViewMain;
    MangaListAdapter mangaListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        refreshLayoutMain = (SwipeRefreshLayout) findViewById(R.id.refreshLayoutMain);
        recyclerViewMain = (RecyclerView) findViewById(R.id.recyclerViewMain);
        recyclerViewMain.setLayoutManager(manager);
        refreshLayoutMain.setOnRefreshListener(MainActivity.this);
        //
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //
        getContentRequest(Endpoint.MANGA_EDEN_ALL.getValue());
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (exit) {
                super.onBackPressed();
            }
            else {
                Toast.makeText(this, R.string.btnBackExitMsg, Toast.LENGTH_SHORT).show();
                exit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit = false;
                    }
                }, 3 * 1000);
            }
        }
    }


    public void getContentRequest(String url) {
        //int val = (int)(Math.random() * (ReadableOnly.LOADING_MSG.length));
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
                        tempBase = gson.fromJson(response, TempBase.class);
                        mangaStack = new ArrayList<>();

                        for (int i = 0; i < tempBase.getMangaList().length; i++)
                            mangaStack.add(new MangaStack(
                                    Endpoint.MANGA_EDEN_IMG.getValue() + tempBase.getMangaList()[i].getCover(),
                                    tempBase.getMangaList()[i].getTitle(),
                                    tempBase.getMangaList()[i].getId()
                            ));

                        refreshLayoutMain.setRefreshing(false);
                        mangaListAdapter = new MangaListAdapter(getApplicationContext(), mangaStack);
                        recyclerViewMain.setAdapter(mangaListAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_LONG).show();

                    }
                });
        RequestManager.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.itemSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRefresh() {
        getContentRequest(Endpoint.MANGA_EDEN_ALL.getValue());
        /*
        Handler handlerTimer = new Handler();
        handlerTimer.postDelayed(new Runnable(){
            public void run() {
                //getContentRequest(ReadableOnly.MEDEN_0EN);
            }}, 1500);
        */
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) throws NullPointerException{
        if(mangaStack != null && mangaStack.size() > 0){
            newText = newText.toLowerCase();
            ArrayList<MangaStack> newList = new ArrayList<MangaStack>();

            for(MangaStack mgc : mangaStack){
                String name = mgc.getTitle().toLowerCase();
                if(name.contains(newText))
                    newList.add(mgc);
            }
            mangaListAdapter.setFilter(newList);
            recyclerViewMain.setAdapter(mangaListAdapter);
        }
        return true;
    }
}
