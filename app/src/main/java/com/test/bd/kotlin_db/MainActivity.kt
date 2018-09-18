package com.test.bd.kotlin_db

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import io.realm.kotlin.where
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*





class MainActivity : AppCompatActivity() {
    lateinit var RealmContact: RealmList<ContactList>
    internal var recyclerView: RecyclerView? = null
    internal var posts: MutableList<ContactList>? = null



    // Instead of
//    RealmObject.addChangeListener(contactList, changeListener)

// you can do

    //realm.delete<ContactList>()
   // realm.where<ContactList>()
   // .findAllAsyc()
    //.asFlowable()

//    contactList.addChangeListener()


  //  internal var postsFriend: List<List<Friend>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("myrealm.realm").build()
        Realm.setDefaultConfiguration(config)





        apiService = Controller.api
        posts = ArrayList()
   //     postsFriend = ArrayList()

        val realm = Realm.getDefaultInstance()

            realm.beginTransaction()
            val dBPosts = realm
                    .where<ContactList>()
                    .findAll()
                   // .asFlowable()
            realm.commitTransaction()



        recyclerView = findViewById(R.id.posts_recycle_view)
        val layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager

        val adapter = RecyclerAdapter(this, dBPosts)
        recyclerView!!.adapter = adapter

        apiService!!.friendList.enqueue(object : Callback<List<ContactList>> {
            override fun onResponse(call: Call<List<ContactList>>, response: Response<List<ContactList>>) {
                (posts as ArrayList<ContactList>).addAll(response.body()!!)
                recyclerView!!.adapter.notifyDataSetChanged()


                val realm = Realm.getDefaultInstance() // opens "myrealm.realm"
                try {
                    realm.beginTransaction()
                    realm.copyToRealmOrUpdate(this@MainActivity.posts)
                   /* val dogs = realm
                            .where<ContactList>()
                            .findAll()
                            .asFlowable()
                     */

                   // Log.i("Id", dogs.)

                    realm.commitTransaction()
                   // realm.executeTransaction()
                } finally {
                    realm.close()
                }

            }

            override fun onFailure(call: Call<List<ContactList>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "An error occurred during networking", Toast.LENGTH_SHORT).show()
            }
        })
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contactList = new ArrayList<>();

        parentView = findViewById(R.id.parentLayout);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(parentView, contactList.get(position).getName() + " => " + contactList.get(position).getPhone(), Snackbar.LENGTH_LONG).show();
            }
        });

        Toast toast =
                Toast.makeText(getApplicationContext(), R.string.string_click_to_load, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull final View view) {


                 // Checking Internet Connection

                if (InternetConnection.checkConnection(getApplicationContext())) {
                    final ProgressDialog dialog;
                     //Progress Dialog for User Interaction

                    dialog = new ProgressDialog(MainActivity.this);
                    dialog.setTitle(getString(R.string.string_getting_json_title));
                    dialog.setMessage(getString(R.string.string_getting_json_message));
                    dialog.show();

                    //Creating an object of our api interface
                    ApiService api = RetroClient.getApiService();

                    //Calling JSON

                    //Call<FriendList> call = api.getFriendList();
                    Call<List<FriendList>> friend = api.getFriendList();

                    // Enqueue Callback will be call when get response...

                    friend.enqueue(new Callback<List<FriendList>>() {
                        @Override
                        public void onResponse(Call<List<FriendList>> friend, Response<List<FriendList>> response) {
                            //Dismiss Dialog
                            dialog.dismiss();

                            if (response.isSuccessful()) {
                                Snackbar.make(parentView, "Done", Snackbar.LENGTH_LONG).show();

                                contactList = response.body().getFriends();


                                adapter = new ContactAdapted(MainActivity.this, contactList);
                                listView.setAdapter(adapter);

                            } else {
                                Snackbar.make(parentView, R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<FriendList>> friend, Throwable t) {
                            dialog.dismiss();
                        }
                    });

                } else {
                    Snackbar.make(parentView, R.string.string_internet_connection_not_available, Snackbar.LENGTH_LONG).show();
                }
            }
        });
*/
    }

    companion object {

        private var apiService: ApiService? = null
    }
}

