package com.example.gadproject.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gadproject.R
import com.example.gadproject.api.Status
import com.example.gadproject.ui.adapters.TopLearnersAdapter
import com.example.gadproject.util.GadsApp
import com.example.gadproject.viewModel.TopLearnersViewModel
import kotlinx.android.synthetic.main.fragment_learning_leaders.*


class LearningLeadersFragment : Fragment() {

    private lateinit var viewModel : TopLearnersViewModel
    private lateinit var adapter: TopLearnersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory = GadsApp.getViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory).get(TopLearnersViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learning_leaders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TopLearnersAdapter()
        setUpRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        observer(viewModel)
        viewModel.fetchData()
    }
    private fun setUpRecyclerView(){
        val manager = LinearLayoutManager(context)
        recyclerViewHours.layoutManager = manager
        recyclerViewHours.adapter = adapter
        //val divider = DividerItemDecoration(context, ClipDrawable.HORIZONTAL)
        //recyclerViewSkill.addItemDecoration(divider)
    }


    private fun observer(viewModel: TopLearnersViewModel){
        viewModel.topLearner.observe(this, Observer {
            when (it.status){
                Status.LOADING -> {
                    setLoading(true)
                }
                Status.SUCCESS ->{
                    adapter.setHours(it.data!!)
                    setLoading(false)
                }
            }
        })
    }

    private fun setLoading(loading: Boolean) {
        loader.visibility = if (loading) View.VISIBLE else View.GONE
    }

   companion object{
       fun newInstance() = LearningLeadersFragment()
   }
}