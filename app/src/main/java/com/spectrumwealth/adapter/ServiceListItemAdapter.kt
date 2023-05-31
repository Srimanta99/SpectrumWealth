package com.spectrumwealth.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.dazzlebloom.utiles.customtypeface.CustomTypeface
import com.spectrumwealth.R
import com.spectrumwealth.`interface`.OnItemClickInterface
import com.spectrumwealth.model.ServicesBycategoryResponseModel
import com.spectrumwealth.ui.ServiceWebViewActivity

class ServiceListItemAdapter(
    val mainActivity: Context,
    val servicelist: ArrayList<ServicesBycategoryResponseModel.ServiceList>,
    val onitemclick: OnItemClickInterface
) : RecyclerView.Adapter<ServiceListItemAdapter.ViewHolder>() {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val img = view.findViewById<AppCompatImageView>(R.id.img_service)
        val llCard = view.findViewById<LinearLayout>(R.id.llcard)
        val prodname : TextView = view.findViewById(R.id.tv_prodname)
        val category :TextView =view.findViewById(R.id.tv_category)
        val subcat : TextView = view.findViewById(R.id.tv_subcat)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_sublist, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(this.mainActivity)
            .load(servicelist.get(position).photo)
           // .centerInside()
            .error(R.drawable.placeholder)
            .apply( RequestOptions()
                .override(Target.SIZE_ORIGINAL)
                .format(DecodeFormat.PREFER_ARGB_8888))
            .into(holder.img);

        holder.prodname.text = servicelist.get(position).name
        holder.category.text = "Category : "+servicelist.get(position).categoryname
        holder.subcat.text = "Subcategory : "+ servicelist.get(position).subcategoryname
        holder.llCard.setOnClickListener {
           // onitemclick.onItemClick(servicelist.get(position))
            val webViewActivity = Intent(mainActivity, ServiceWebViewActivity::class.java)
            webViewActivity.putExtra("serviceurl",servicelist.get(position).url)
            webViewActivity.putExtra("servicename", servicelist.get(position).name)
            mainActivity.startActivity(webViewActivity)
        }

        holder.prodname.typeface = CustomTypeface(mainActivity).openSansSemiBold

    }

    override fun getItemCount(): Int {
       return servicelist.size
    }
}