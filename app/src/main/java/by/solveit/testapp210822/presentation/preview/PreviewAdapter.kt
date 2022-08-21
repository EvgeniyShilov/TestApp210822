package by.solveit.testapp210822.presentation.preview

import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import by.solveit.testapp210822.R
import by.solveit.testapp210822.databinding.ItemPreviewBinding
import by.solveit.testapp210822.domain.models.UserPreview
import com.squareup.picasso.Picasso

class PreviewAdapter(
    private val clickListener: (UserPreview) -> Unit
) : ListAdapter<UserPreview, PreviewAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<UserPreview>() {

        override fun areItemsTheSame(
            oldItem: UserPreview,
            newItem: UserPreview
        ) = oldItem.userId == newItem.userId

        override fun areContentsTheSame(
            oldItem: UserPreview,
            newItem: UserPreview
        ) = oldItem == newItem
    }
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = LayoutInflater.from(parent.context)
        .inflate(R.layout.item_preview, parent, false)
        .let { ViewHolder(it, clickListener) }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) = holder.bind(getItem(position))

    class ViewHolder(
        view: View,
        private val clickListener: (UserPreview) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private val binding by viewBinding(ItemPreviewBinding::bind)

        fun bind(
            item: UserPreview
        ) = with(binding) {
            vName.text = item.userName
            vCount.text = item.postCount.toString()
            Picasso.get()
                .load(Uri.parse(item.userAvatarUrl))
                .placeholder(ColorDrawable(vImage.context.getColor(R.color.black)))
                .error(ColorDrawable(vImage.context.getColor(R.color.black)))
                .into(vImage)
            itemView.setOnClickListener { clickListener.invoke(item) }
        }
    }
}
