package com.spectrumwealth.adapter

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
import com.spectrumwealth.model.ServiceListItem
import com.spectrumwealth.ui.MainActivity
import com.spectrumwealth.ui.SeeMoreActivity


class SerViceChildAdapter(
    val mainActivity: MainActivity,
    val services: ArrayList<ServiceListItem>,
    val category: String,
    val id: String
): RecyclerView.Adapter<SerViceChildAdapter.ChildViewHolder>() {

    class ChildViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img = view.findViewById<AppCompatImageView>(com.spectrumwealth.R.id.img_service)
        val llCard = view.findViewById<LinearLayout>(com.spectrumwealth.R.id.llcard)
        val prodname : TextView = view.findViewById(com.spectrumwealth.R.id.tv_prodname)
        val category :TextView =view.findViewById(com.spectrumwealth.R.id.tv_category)
        val subcat : TextView = view.findViewById(com.spectrumwealth.R.id.tv_subcat)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        return SerViceChildAdapter.ChildViewHolder(LayoutInflater.from(mainActivity)
            .inflate(com.spectrumwealth.R.layout.item_sublist, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {

        holder.prodname.text = services.get(position).subcategory
        holder.prodname.typeface = CustomTypeface(mainActivity).openSansSemiBold
        Glide.with(this.mainActivity)
            .load(services.get(position).photo)
            // .centerInside()
            .error(R.drawable.placeholder)
            .apply( RequestOptions()
                .override(Target.SIZE_ORIGINAL)
                .format(DecodeFormat.PREFER_ARGB_8888))
            .into(holder.img);
        holder.itemView.setOnClickListener {
            val intent = Intent(mainActivity, SeeMoreActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("catname", category)
            mainActivity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return  services.size
    }
}