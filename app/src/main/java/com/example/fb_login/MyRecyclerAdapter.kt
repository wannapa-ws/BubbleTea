package layout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fb_login.R
import com.example.fb_login.Recycler_view
import com.example.fb_login.list_selected
import org.json.JSONArray

class MyRecyclerAdapter(context: Context, fm: FragmentManager, val dataSource: JSONArray) : RecyclerView.Adapter<MyRecyclerAdapter.Holder>() {

    private val thiscontext : Context = context
    private val fragment: FragmentManager = fm

    class Holder(view : View) : RecyclerView.ViewHolder(view) {
        private val View = view;

        lateinit var layout : LinearLayout
        lateinit var nameTextView: TextView
        lateinit var priceTextView: TextView
        lateinit var sweetTextView: TextView
        lateinit var volumeTextView: TextView
        lateinit var caloriesTextView: TextView
        lateinit var image: ImageView

        fun Holder(){

            layout = View.findViewById<View>(R.id.recyview_layout) as LinearLayout
            nameTextView = View.findViewById<View>(R.id.tv_name) as TextView
            priceTextView = View.findViewById<View>(R.id.tv_price) as TextView
            sweetTextView = View.findViewById<View>(R.id.tv_sweet) as TextView
            volumeTextView = View.findViewById<View>(R.id.tv_volume) as TextView
            caloriesTextView = View.findViewById<View>(R.id.tv_calories) as TextView
            image = View.findViewById<View>(R.id.imgV) as ImageView

        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.recy_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return dataSource.length()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.Holder()

        holder.nameTextView.setText( dataSource.getJSONObject(position).getString("name").toString() )
        holder.priceTextView.setText( dataSource.getJSONObject(position).getString("price").toString() )
        holder.sweetTextView.setText( dataSource.getJSONObject(position).getString("sweet").toString() )
        holder.volumeTextView.setText( dataSource.getJSONObject(position).getString("volume").toString() )
        holder.caloriesTextView.setText( dataSource.getJSONObject(position).getString("calories").toString() )

        Glide.with(thiscontext)
            .load(dataSource.getJSONObject(position).getString("image").toString())
            .into(holder.image)

        holder.layout.setOnClickListener{

            Toast.makeText(thiscontext,holder.nameTextView.text.toString(),Toast.LENGTH_SHORT).show()

            val image:String = dataSource.getJSONObject(position).getString("image").toString()
            val name:String = dataSource.getJSONObject(position).getString("name").toString()
            val price:String = dataSource.getJSONObject(position).getString("price").toString()
            val sweet:String = dataSource.getJSONObject(position).getString("sweet").toString()
            val volume:String = dataSource.getJSONObject(position).getString("volume").toString()
            val calories:String = dataSource.getJSONObject(position).getString("calories").toString()

            val list_selected = list_selected().newInstance(image,name,price,sweet,volume,calories)
            //val fm = thisActivity.supportFragmentManager
            val transaction : FragmentTransaction =  fragment.beginTransaction()
            transaction.replace(com.example.fb_login.R.id.contentContainer,list_selected,"list_selected")
            transaction.addToBackStack("list_selected")
            transaction.commit()

        }

    }
}
