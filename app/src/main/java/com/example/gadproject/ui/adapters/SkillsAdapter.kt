package com.example.gadproject.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gadproject.R
import com.example.gadproject.models.Skills
import kotlinx.android.synthetic.main.holder_skills.view.*

class SkillsAdapter :RecyclerView.Adapter<SkillsAdapter.SkillHolder>(){

    private var skills = listOf<Skills>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsAdapter.SkillHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_skills, parent, false)
        return SkillHolder(view)
    }

    override fun onBindViewHolder(holder: SkillsAdapter.SkillHolder, position: Int) {
       val skill = skills[position]
        holder.bind(skill)
    }

    override fun getItemCount(): Int {
        return skills.size
    }

    fun setSkills(subs: List<Skills>){
        skills = subs
        notifyDataSetChanged()
    }

    inner class SkillHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(skills: Skills){
            itemView.name_text.text = skills.name
            itemView.text_country.text = skills.country
            itemView.text_score.text = "${skills.score} skill IQ Score, "
            Glide.with(itemView.context).asBitmap().load(skills.badgeUrl).into(itemView.skill_image)
        }
    }



}