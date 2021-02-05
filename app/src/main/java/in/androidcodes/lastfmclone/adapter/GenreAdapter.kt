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

class GenreAdapter:  RecyclerView.Adapter<GenreAdapter.GenreViewHolder>()  {
    inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tagName = itemView.tag_name
    }

    private val differCallback = object : DiffUtil.ItemCallback<Tag>(){

        override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenreAdapter.GenreViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_item_layout, parent, false)
        return GenreViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GenreAdapter.GenreViewHolder, position: Int) {
        val response = differ.currentList[position]
        holder.tagName.text = response.name
        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener?.let { it(response) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Tag) -> Unit)? = null

    fun setOnItemClickListener(listener: (Tag) -> Unit) {
        onItemClickListener = listener
    }
}