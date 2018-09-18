package com.test.bd.kotlin_db

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso


class RecyclerAdapter(private val mContext: Context,
                      private val posts: List<ContactList>?)
    : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun getItemCount():Int{
        if (posts != null) {
            return posts.size
        }
        return Log.i("Error","Error")
    }




    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.post_item, parent, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItem(posts!![position])

 }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(contactList: ContactList){
            val name: TextView = itemView.findViewById(R.id.postitem_name) as TextView
            val phone: TextView = itemView.findViewById(R.id.postitem_phone) as TextView
            val image: ImageView = itemView.findViewById(R.id.post_imageView) as ImageView
            val company: TextView = itemView.findViewById(R.id.postitem_company) as TextView
            val gender: TextView = itemView.findViewById(R.id.postitem_gender) as TextView
            val email: TextView = itemView.findViewById(R.id.postitem_email) as TextView




            name.text = contactList.name
            phone.text = contactList.phone
            //image.image = contactList.getPicture()
            Picasso.get().load(contactList.getPicture())
                    .resize(100, 100)
                    .into(image)
            company.text = contactList.company
            gender.text = contactList.gender
            email.text = contactList.email
            val sendId = contactList.id
            itemView.setOnClickListener {

                val intent = Intent(mContext, DetailsActivity::class.java)
                intent.putExtra("id",sendId)

                mContext.startActivity(intent)
                Toast.makeText(mContext, name.text, Toast.LENGTH_LONG ).show()
            }

        }





    }
}