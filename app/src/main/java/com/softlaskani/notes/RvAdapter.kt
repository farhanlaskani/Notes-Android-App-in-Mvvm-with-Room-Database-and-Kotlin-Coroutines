package com.softlaskani.notes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.softlaskani.notes.room.Note

class RvAdapter(private val itemclick: Itemclick) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    private var mlist = ArrayList<Note>()

    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val titlte = itemview.findViewById<TextView>(R.id.rv_title)
        val date = itemview.findViewById<TextView>(R.id.rv_date)
        val delete = itemview.findViewById<ImageView>(R.id.rvdelete)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
   return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val myposition = mlist[position]

        holder.date.text = "Last update ${myposition.date}"
        val checktitle = myposition.title
        if (checktitle ==null ){
            holder.titlte.text = myposition.content
        } else {
            holder.titlte.text = myposition.title
        }

        holder.itemView.setOnClickListener{
            itemclick.onitemclick(myposition)

        }

        holder.itemView.setOnLongClickListener {
           holder.delete.visibility = View.VISIBLE
            true
        }
        holder.delete.setOnClickListener {
            itemclick.onitemdelete(myposition)
            holder.delete.visibility = View.GONE
        }



    }

    @SuppressLint("NotifyDataSetChanged")
    fun updatelist(newlist : List<Note>){
        mlist.clear()
        mlist.addAll(newlist)
        notifyDataSetChanged()
    }



}