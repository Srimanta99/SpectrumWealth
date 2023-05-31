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
import com.spectrumwealth.ui.SeeMoreActivity
import com.spectrumwealth.ui.SubCategoryViewMoreActivity


class SubCategoryChildAdapter(
    val seemoreActivity: SeeMoreActivity,
    val services: ArrayList<ViewMoreApiResponse.Services>,
    val id: String,
    val subcategory: String
): RecyclerView.Adapter<SubCategoryChildAdapter.ChildViewHolder>() {

    class ChildViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img = view.findViewById<AppCompatImageView>(com.spectrumwealth.R.id.img_service)
        val llCard = view.findViewById<LinearLayout>(com.spectrumwealth.R.id.llcard)
        val prodname : TextView = view.findViewById(com.spectrumwealth.R.id.tv_prodname)
        val category :TextView =view.findViewById(com.spectrumwealth.R.id.tv_category)
        val subcat : TextView = view.findViewById(com.spectrumwealth.R.id.tv_subcat)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        return SubCategoryChildAdapter.ChildViewHolder(LayoutInflater.from(seemoreActivity)
            .inflate(com.spectrumwealth.R.layout.item_sublist, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {

        holder.prodname.text = services.get(position).name
        holder.prodname.typeface = CustomTypeface(seemoreActivity).openSansSemiBold
        Glide.with(this.seemoreActivity)
            .load(services.get(position).photo)
            // .centerInside()
            .error(R.drawable.placeholder)
            .apply( RequestOptions()
                .override(Target.SIZE_ORIGINAL)
                .format(DecodeFormat.PREFER_ARGB_8888))
            .into(holder.img);

        holder.itemView.setOnClickListener {
            val intent = Intent(seemoreActivity, SubCategoryViewMoreActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("catname", subcategory)
            seemoreActivity.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
       return  services.size
    }
}