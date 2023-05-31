package com.spectrumwealth.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dazzlebloom.utiles.customtypeface.CustomTypeface
import com.spectrumwealth.R
import com.spectrumwealth.`interface`.OnCategoryItemClickInterface
import com.spectrumwealth.model.CategoryApiResponseModel
import com.spectrumwealth.ui.MainActivity
import com.spectrumwealth.utils.customdialog.CustomCategoryDialog

class CategoryListItemAdapter(
    val mainActivity: MainActivity,
    val categorylist: ArrayList<CategoryApiResponseModel.CategoryItem>,
    val  dialog: CustomCategoryDialog,
    val onitemclick: OnCategoryItemClickInterface
) : RecyclerView.Adapter<CategoryListItemAdapter.ViewHolder>() {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val categoryname = view.findViewById<TextView>(R.id.tv_catname)
        val rrcard = view.findViewById<RelativeLayout>(R.id.rr_card)
        val chkCategory = view.findViewById<CheckBox>(R.id.chk_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_category, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.categoryname.text = categorylist.get(position).category
        holder.categoryname.typeface = CustomTypeface(mainActivity).openSansSemiBold
        holder.rrcard.setOnClickListener {
            onitemclick.onItemClick(categorylist.get(position), position)
        }

        if (dialog.mCheckedPostion == position){
            holder.chkCategory.setChecked(true);
        }else{
            holder.chkCategory.setChecked(false);
        }

        holder.chkCategory.setOnCheckedChangeListener { compoundButton, b ->
            //dialog.categoryList.get(position).isselect = true
            dialog.mCheckedPostion = position
            onitemclick.onItemClick(categorylist.get(position), position)

           /* for ( i in 0 until  categorylist.size){
                if (i == position) {
                    dialog.categoryList.get(i).isselect = true
                }else
                    dialog.categoryList.get(i).isselect = false
            }*/

           /* for ( i in 0 until  categorylist.size){
                if (holder.chkCategory.isChecked) {
                    categorylist.get(position).isselect = true
                }else
                    categorylist.get(position).isselect = false

            }*/
           // mainActivity.runOnUiThread {
                //notifyDataSetChanged()
          //  }

            /*Handler().post(Runnable {
                dialog.categoryListItemAdapter?.notifyDataSetChanged()
            })*/
        }


    }

    override fun getItemCount(): Int {
       return categorylist.size
    }
}