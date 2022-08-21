package by.solveit.testapp210822.presentation.details

import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import by.solveit.testapp210822.R
import by.solveit.testapp210822.databinding.ItemDetailsHeaderBinding
import by.solveit.testapp210822.databinding.ItemDetailsPostBinding
import by.solveit.testapp210822.presentation.details.DetailsAdapter.ViewHolder.HeaderHolder
import by.solveit.testapp210822.presentation.details.DetailsAdapter.ViewHolder.PostHolder
import by.solveit.testapp210822.presentation.details.DetailsItem.HeaderItem
import by.solveit.testapp210822.presentation.details.DetailsItem.PostItem
import com.squareup.picasso.Picasso

class DetailsAdapter : ListAdapter<DetailsItem, DetailsAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<DetailsItem>() {

        override fun areItemsTheSame(
            oldItem: DetailsItem,
            newItem: DetailsItem
        ) = oldItem is HeaderItem && newItem is HeaderItem ||
                oldItem is PostItem && newItem is PostItem && oldItem.post.id == newItem.post.id

        override fun areContentsTheSame(
            oldItem: DetailsItem,
            newItem: DetailsItem
        ) = oldItem == newItem
    }
) {

    @LayoutRes
    override fun getItemViewType(
        position: Int
    ) = when (getItem(position)) {
        is HeaderItem -> R.layout.item_details_header
        is PostItem -> R.layout.item_details_post
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        @LayoutRes viewType: Int
    ) = LayoutInflater.from(parent.context)
        .inflate(viewType, parent, false)
        .let {
            when (viewType) {
                R.layout.item_details_header -> HeaderHolder(it)
                R.layout.item_details_post -> PostHolder(it)
                else -> error("Unexpected case")
            }
        }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) = when (val item = getItem(position)) {
        is HeaderItem -> (holder as? HeaderHolder)?.bind(item)
        is PostItem -> (holder as? PostHolder)?.bind(item)
    } ?: Unit

    sealed class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        class HeaderHolder(
            view: View
        ) : ViewHolder(view) {

            private val binding by viewBinding(ItemDetailsHeaderBinding::bind)

            fun bind(
                item: HeaderItem
            ) = with(binding) {
                Picasso.get()
                    .load(Uri.parse(item.userAvatarUrl))
                    .placeholder(ColorDrawable(vImage.context.getColor(R.color.black)))
                    .error(ColorDrawable(vImage.context.getColor(R.color.black)))
                    .into(vImage)
            }
        }

        class PostHolder(
            view: View
        ) : ViewHolder(view) {

            private val binding by viewBinding(ItemDetailsPostBinding::bind)

            fun bind(
                item: PostItem
            ) = with(binding) {
                vTitle.text = item.post.title
                vBody.text = item.post.body
            }
        }
    }
}
