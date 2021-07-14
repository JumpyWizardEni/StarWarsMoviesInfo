package com.jumpywiz.starwarsmovies.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jumpywiz.starwarsmovies.App
import com.jumpywiz.starwarsmovies.adapters.MovieInfoAdapter
import com.jumpywiz.starwarsmovies.adapters.MovieListAdapter
import com.jumpywiz.starwarsmovies.databinding.FragmentMovieInfoBinding
import com.jumpywiz.starwarsmovies.ui.MovieClickListener
import com.jumpywiz.starwarsmovies.viewmodels.MovieInfoViewModel
import com.jumpywiz.starwarsmovies.viewmodels.MovieListViewModel
import javax.inject.Inject


class MovieInfoFragment : Fragment() {

    private var binding: FragmentMovieInfoBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieInfoBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        App.appComponent.inject(this)

        val adapter = MovieInfoAdapter()
        with(binding!!) {
            characterRecyclerView.adapter = adapter
            characterRecyclerView.layoutManager = LinearLayoutManager(view.context)
        }

        val viewModel =
            ViewModelProvider(
                requireActivity(),
                viewModelFactory
            ).get(MovieInfoViewModel::class.java)

        viewModel.chars.observe(viewLifecycleOwner, Observer { charsList ->
            adapter.setData(charsList)
        }
        )

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}