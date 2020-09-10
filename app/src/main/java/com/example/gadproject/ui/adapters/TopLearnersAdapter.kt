package com.example.gadproject.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gadproject.R
import com.example.gadproject.models.TopLearner
import kotlinx.android.synthetic.main.holder_hours.view.*

class TopLearnersAdapter: RecyclerView.Adapter<TopLearnersAdapter.HoursHolder>() {

    private var hours = listOf<TopLearner>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopLearnersAdapter.HoursHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_hours, parent, false)
        return HoursHolder(view)
    }

    override fun getItemCount(): Int {
        return hours.size
    }

    override fun onBindViewHolder(holder: TopLearnersAdapter.HoursHolder, position: Int) {
        val hour = hours[position]
        holder.bind(hour)
    }

    fun setHours(subs: List<TopLearner>){
        hours = subs
        notifyDataSetChanged()
    }

    inner class HoursHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(topLearner: TopLearner){
            itemView.name_text.text = topLearner.name
            itemView.text_country.text = topLearner.country
            itemView.text_score.text = "${topLearner.hours} skill IQ Score, "
            Glide.with(itemView.context).asBitmap().load(topLearner.badgeUrl).into(itemView.skill_image)
        }
    }
}