package com.test_android_osmany01.ui.xinrepo

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.xinrepositories.Repository
import com.test_android_osmany01.R
import com.test_android_osmany01.databinding.ViewReposBinding
import com.test_android_osmany01.ui.common.*
import com.test_android_osmany01.ui.common.PromptUtils.negativeButton
import com.test_android_osmany01.ui.common.PromptUtils.positiveButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class XinRepoAdapter(private val scope: CoroutineScope) :
    ListAdapter<Repository, XinRepoAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_repos, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
        scope.collectFlow(itemView.onClickEvents) {

            PromptUtils.alertDialog(itemView.context, R.style.AlertDialog_AppCompat_Light) {
                setTitle(context.getString(R.string.alert_title))
                setMessage(context.getString(R.string.alert_message))
                positiveButton(){
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(item.html_url)
                    itemView.context.startActivity(intent)
                    it.dismiss()
                }
                negativeButton(){
                    it.dismiss()
                }
            }.show()
        }
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ViewReposBinding.bind(itemView)

        fun bind(item: Repository) = with(binding) {
            title.text = item.name
            description.text = item.description
            ownerImage.loadWithGlide(item.owner.avatar_url)

            if (item.forks_count > 0) content.setBackgroundColor(Color.GREEN)
            else content.setBackgroundColor(Color.WHITE)

        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Repository>() {
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem == newItem
    }
}