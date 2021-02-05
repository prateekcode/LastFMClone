package `in`.androidcodes.lastfmclone.adapter

import `in`.androidcodes.lastfmclone.R
import `in`.androidcodes.lastfmclone.model.topgenre.Tag
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.single_item_layout.view.*

class GenreAdapter2(
    private var responseItem: List<`in`.androidcodes.lastfmclone.model.top.Tag>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<GenreAdapter2.GenreViewHolder>() {
    inner class GenreViewHolder(itemView: View, onItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        var tagName = itemView.tag_name

        init {
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(adapterPosition)
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenreAdapter2.GenreViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.single_item_layout, parent, false)
        return GenreViewHolder(itemView, onItemClickListener)
    }

    override fun onBindViewHolder(holder: GenreAdapter2.GenreViewHolder, position: Int) {
        val response = responseItem[position]
        holder.tagName.text = response.name

    }

    override fun getItemCount(): Int {
        return responseItem.size
    }


    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


}