package com.softstark.hackernews.core.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.softstark.hackernews.R
import com.softstark.hackernews.presentation.feature.models.NewsView

class NewsListAdapter(
    private var onClickedListener: OnClickedListener? = null
) :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private val listOfNewsView: MutableList<NewsView> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun refreshList(listOfNewsView: List<NewsView>) {
        this.listOfNewsView.clear()
        this.listOfNewsView.addAll(listOfNewsView)
        notifyDataSetChanged()
    }

    fun setRemoveNewsChangeListener(onClickedListener: OnClickedListener) {
        this.onClickedListener = onClickedListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfNewsView.size
    }

    fun restoreItem(item: NewsView?, position: Int) {
        listOfNewsView.add(position, item!!)
        notifyItemInserted(position)
    }

    fun removeAt(position: Int) {
        listOfNewsView.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = listOfNewsView[position]

        // sets the texts to the textview from our itemHolder class
        holder.title.text = itemsViewModel.title
        holder.author.text = itemsViewModel.author
        holder.createAt.text = itemsViewModel.createAt
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val author: TextView = itemView.findViewById(R.id.tvAuthor)
        val createAt: TextView = itemView.findViewById(R.id.tvCreateAt)
    }

    interface OnClickedListener {
        fun onClickChanged(itemView: NewsView)
    }
}
