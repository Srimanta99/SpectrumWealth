package com.spectrumwealth.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.dazzlebloom.utiles.customtypeface.CustomTypeface
import com.spectrumwealth.*
import com.spectrumwealth.`interface`.OnItemClickInterface
import com.spectrumwealth.ui.SeeMoreActivity
import com.spectrumwealth.ui.SubCategoryViewMoreActivity


class SubCategoryParentItem(
    val seemoreActivity: SeeMoreActivity,
    val servicelist: ArrayList<ViewMoreApiResponse.SubCategory>,
    val onitemclick: OnItemClickInterface
) : RecyclerView.Adapter<SubCategoryParentItem.ViewHolder>() {
    var viewPool = RecycledViewPool()
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
       // val llparent = view.findViewById<LinearLayout>(R.id.llparent)

        val  ParentItemTitle  = view.findViewById<TextView>(R.id.parent_item_title)
        val  ChildRecyclerView : RecyclerView = view.findViewById(R.id.child_recyclerview)
        val tv_seemoreitems : AppCompatTextView = view.findViewById(R.id.tv_seemoreitems)
        val rlnoitem : RelativeLayout =view.findViewById(R.id.rlnoitem)
        val tv_noitem : TextView = view.findViewById(R.id.tv_noitem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return SubCategoryParentItem.ViewHolder(LayoutInflater.from(seemoreActivity).inflate(R.layout.serviceparentitem, parent, false))
    }

    override fun onBindViewHolder(parentViewHolder: ViewHolder, position: Int) {
        /*if (position % 2==0){
            parentViewHolder.llparent.setBackgroundResource(R.drawable.rectangle_rounded_yellow)
        }*/
         parentViewHolder.ParentItemTitle.typeface = CustomTypeface(seemoreActivity).openSansBold
        parentViewHolder.tv_seemoreitems.typeface = CustomTypeface(seemoreActivity).openSansBold

        parentViewHolder.ParentItemTitle.text = servicelist.get(position).subcategory

        if (servicelist.get(position).services.size>0) {
            val layoutManager = LinearLayoutManager(
                parentViewHolder.ChildRecyclerView.getContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            layoutManager.setInitialPrefetchItemCount(4)

            val childAdapter =
                SubCategoryChildAdapter(seemoreActivity, servicelist.get(position).services,servicelist.get(position).id, servicelist.get(position).subcategory )

            parentViewHolder
                .ChildRecyclerView
                .setLayoutManager(layoutManager);

            parentViewHolder
                .ChildRecyclerView
                .setAdapter(childAdapter);

            parentViewHolder
                .ChildRecyclerView
                .setRecycledViewPool(viewPool);
        }else{
            parentViewHolder.rlnoitem.visibility= View.VISIBLE
            parentViewHolder.tv_noitem.typeface= CustomTypeface(seemoreActivity).openSansBold
            parentViewHolder.tv_seemoreitems.visibility= View.GONE
        }
        //parentViewHolder.tv_seemoreitems.visibility= View.GONE


        parentViewHolder.tv_seemoreitems.setOnClickListener {
            val intent = Intent(seemoreActivity, SubCategoryViewMoreActivity::class.java)
            intent.putExtra("id", servicelist.get(position).id)
            intent.putExtra("catname", servicelist.get(position).subcategory)
            seemoreActivity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return servicelist.size
    }
}