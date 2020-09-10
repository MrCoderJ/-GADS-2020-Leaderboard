package com.example.gadproject.ui.fragment

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gadproject.R
import com.example.gadproject.api.Status
import com.example.gadproject.ui.adapters.SkillsAdapter
import com.example.gadproject.util.GadsApp
import com.example.gadproject.viewModel.SkillsViewModel
import kotlinx.android.synthetic.main.fragment_learning_leaders.*
import kotlinx.android.synthetic.main.fragment_skill_leaders.*


class SkillLeadersFragment : Fragment() {

    private lateinit var viewModel : SkillsViewModel
    private lateinit var adapter: SkillsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val factory = GadsApp.getViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory).get(SkillsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_skill_leaders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SkillsAdapter()
        setUpRecyclerView()
    }


    override fun onResume() {
        super.onResume()
        observer(viewModel)
        viewModel.fetchData()
    }

    private fun setUpRecyclerView(){
        val manager = LinearLayoutManager(context)
        recyclerViewSkill.layoutManager = manager
        recyclerViewSkill.adapter = adapter
        //val divider = DividerItemDecoration(context, ClipDrawable.HORIZONTAL)
        //recyclerViewSkill.addItemDecoration(divider)
    }

    private fun observer(viewModel: SkillsViewModel){
        viewModel.skills.observe(this, Observer {
            when (it.status){
                Status.LOADING -> setLoading(true)
                Status.SUCCESS ->{
                    adapter.setSkills(it.data!!)
                    setLoading(false)
                }
            }
        })
    }
    private fun setLoading(loading: Boolean) {
        loader_skill.visibility = if (loading) View.VISIBLE else View.GONE
    }
    companion object {
        fun newInstance() = SkillLeadersFragment()
    }
}