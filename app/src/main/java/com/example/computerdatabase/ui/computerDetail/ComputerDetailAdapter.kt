package com.example.computerdatabase.ui.computerDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.computerdatabase.R
import com.example.computerdatabase.entity.ComputerDetail
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_image.view.*
import kotlinx.android.synthetic.main.item_info.view.*

class ComputerDetailAdapter: RecyclerView.Adapter<ComputerDetailAdapter.DetailViewHolder>() {

    val list = mutableListOf<DetailData>()

    fun setData(detail: ComputerDetail) {
        if (detail.imageUrl != null) {
            list.add(ImageData(detail.imageUrl ?: return))
        }
        if (detail.company != null) {
            list.add(InfoData("Company", detail.company?.name ?: return))
        }
        if (detail.introduced != null) {
            list.add(InfoData("Introduced date", detail.introduced ?: return))
        }
        if (detail.discounted != null) {
            list.add(InfoData("Discontinued date", detail.discounted ?: return))
        }
        if (detail.description != null) {
            list.add(DescriptionData(detail.description ?: return))
        }
        list.add(SimilarData(detail.id))
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (list.get(position)) {
            is ImageData -> 0
            is InfoData -> 1
            is DescriptionData -> 2
            is SimilarData -> 3
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> ImageViewHolder(inflater.inflate(R.layout.item_image, parent, false))
            1 -> InfoViewHolder(inflater.inflate(R.layout.item_info, parent, false))
            2 -> DescriptionViewHolder(inflater.inflate(R.layout.item_description, parent, false))
            3 -> SimilarViewHolder(inflater.inflate(R.layout.item_similar, parent, false))
            else -> {throw IllegalArgumentException("Unknown Data")}
        }

    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val data = list.get(position)
        holder.bind(data)
    }

    abstract class DetailViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        abstract fun bind(detailData: DetailData)
    }

    class ImageViewHolder(itemView: View): DetailViewHolder(itemView) {

        override fun bind(detailData: DetailData) {
            if (detailData is ImageData) {
                Picasso.get().load(detailData.url).into(itemView.image)
            }
        }
    }

    class InfoViewHolder(itemView: View): DetailViewHolder(itemView) {
        override fun bind(detailData: DetailData) {
            if (detailData is InfoData) {
                itemView.tvTitle.text = detailData.title
                itemView.tvValue.text = detailData.info
            }
        }
    }
    class DescriptionViewHolder(itemView: View): DetailViewHolder(itemView) {
        override fun bind(detailData: DetailData) {
            if (detailData is DescriptionData) {
                itemView.tvValue.text = detailData.description
            }
        }
    }
    class SimilarViewHolder(itemView: View): DetailViewHolder(itemView) {
        override fun bind(detailData: DetailData) {

        }
    }
}

sealed class DetailData
data class ImageData(val url: String): DetailData()
data class InfoData(val title: String, val info: String): DetailData()
data class DescriptionData(val description: String): DetailData()
data class SimilarData(val id: Int): DetailData()