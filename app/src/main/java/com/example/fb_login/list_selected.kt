package com.example.fb_login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide

/**
 * A simple [Fragment] subclass.
 */
class list_selected : Fragment() {

    var image:String ?= null
    var name:String ?= null
    var price:String ?= null
    var sweet:String ?= null
    var volume:String ?= null
    var calories:String ?= null

    fun newInstance(image:String,name:String,price: String,sweet: String,volume: String,calories: String): list_selected {

        val fragment = list_selected()
        val bundle = Bundle()
        bundle.putString("image",image);
        bundle.putString("name",name);
        bundle.putString("price",price);
        bundle.putString("sweet",sweet);
        bundle.putString("volume",volume);
        bundle.putString("calories",calories);

        fragment.setArguments(bundle)

        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            this.image = bundle.getString("image").toString()
            this.name = bundle.getString("name").toString()
            this.price = bundle.getString("price").toString()
            this.sweet = bundle.getString("sweet").toString()
            this.volume = bundle.getString("volume").toString()
            this.calories = bundle.getString("calories").toString()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_selected, container, false)
        val realDB: Button = view.findViewById(R.id.btn_db);
        val back: Button = view.findViewById(R.id.btn_back);

        val imgView : ImageView = view.findViewById(R.id.imgV)
        val nameText : TextView = view.findViewById(R.id.tv_name)
        val priceText : TextView = view.findViewById(R.id.tv_price)
        val sweetText : TextView = view.findViewById(R.id.tv_sweet)
        val volumeText : TextView = view.findViewById(R.id.tv_volume)
        val caloriesText : TextView = view.findViewById(R.id.tv_calories)

        nameText.text = this.name
        priceText.text = this.price
        sweetText.text = this.sweet
        volumeText.text = this.volume
        caloriesText.text = this.calories

        Glide.with(activity!!.baseContext).load(image).into(imgView)

        realDB.setOnClickListener {
            val DataRealtime = DataRealtime()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.contentContainer, DataRealtime, "DataRealtime")
            transaction.addToBackStack("DataRealtime")
            transaction.commit()
        }

        back.setOnClickListener {
            val Recycler_view = Recycler_view()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.contentContainer, Recycler_view, "Recycler_view")
            transaction.addToBackStack("Recycler_view")
            transaction.commit()
        }

        return view
    }

}
