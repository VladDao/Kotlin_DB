package com.test.bd.kotlin_db

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.realm.Realm
import io.realm.kotlin.where

 class DetailsActivity : AppCompatActivity() {

    private var tagsList: ListView? = null
    private var friendLists: ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_contact)

       getIncomingIntent()
    }

    private fun getIncomingIntent() {
        val personId = intent.getStringExtra("id")
        val realm = Realm.getDefaultInstance()

        realm.beginTransaction()
        val dBPosts = realm
                .where<ContactList>().equalTo("id",personId)
                .findFirst()


        // .asFlowable()
        realm.commitTransaction()

        setContent(dBPosts!!)


    }



    private fun setContent(dBPosts:ContactList) {


        val deImageView = findViewById<ImageView>(R.id.detail_imageView);

        Picasso.get().load(dBPosts.getPicture())
                .resize(200 , 200)
                .into(deImageView)


        val name = findViewById<TextView>(R.id.detail_name)
        val company = findViewById<TextView>(R.id.detail_company)
        val gender = findViewById<TextView>(R.id.detail_gender)
        val email = findViewById<TextView>(R.id.detail_email)
        val phone = findViewById<TextView>(R.id.detail_phone)
        val friends  = findViewById<ListView>(R.id.friendList)

        val mFriend = dBPosts.friends
        val tags = dBPosts.tags


        tagsList = this.findViewById(R.id.tagsList)
        val tagsAdapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                tags)
        tagsList!!.setAdapter(tagsAdapter);


        val myItems =arrayOf("Larry", "Curly", "Moe")
        var i = 0

        while (i < 3) {

            val temp= mFriend?.get(i)?.name.toString()
             if (temp != null) {
                 myItems.set(i,temp)
            }
            i++

           // Toast.makeText(this, myItems[i], Toast.LENGTH_LONG ).show()
        }





        friendLists = findViewById(R.id.friendList)
        val friendAdapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                myItems)
        friendLists!!.setAdapter(friendAdapter);




        name.text = dBPosts.name
        company.text = dBPosts.company
        gender.text = dBPosts.gender
        email.text = dBPosts.email
        phone.text = dBPosts.phone


/*
        // ListView deFriend = findViewById(R.id.friendList);
        // ListView daTags = findViewById(R.id.tagsList);
        tagsList = findViewById<View>(R.id.tagsList) as ListView?
        val tagsAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                tags)
        tagsList!!.adapter = tagsAdapter
        /*
        friendList=(ListView)findViewById(R.id.friendList);
        ArrayAdapter<String>friendAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                friend);
        friendList.setAdapter(friendAdapter);
        */
        /*
        Picasso.get().load(picture)
                .resize(200 , 200)
                .into(deImageView);
                */
        deName.text = name
        deCompany.text = company
        deGender.text = gender
        deEmail.text = email
        dePhone.text = phone
        // deFriend.set(deFriend);
        //daTags.setAdapter(adapter);
        //  friend.setRecyclerListener((AbsListView.RecyclerListener) listsss);
*/
    }


}