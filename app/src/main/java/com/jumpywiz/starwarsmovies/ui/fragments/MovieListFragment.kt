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
import com.jumpywiz.starwarsmovies.R
import com.jumpywiz.starwarsmovies.adapters.MovieRecyclerAdapter
import com.jumpywiz.starwarsmovies.databinding.FragmentMovieListBinding
import com.jumpywiz.starwarsmovies.viewmodels.MovieListViewModel
import javax.inject.Inject


class MovieListFragment : Fragment() {
    private var binding: FragmentMovieListBinding? = null
    private var bundle: Bundle? = null
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.appComponent.inject(this)

        val adapter = MovieRecyclerAdapter(createMovieClickListener())
        with(binding!!) {
            movieListRecyclerView.adapter = adapter
            movieListRecyclerView.layoutManager = LinearLayoutManager(view.context)
        }

        val viewModel =
            ViewModelProvider(
                requireActivity(),
                viewModelFactory
            ).get(MovieListViewModel::class.java)

        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            adapter.setData(movies)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun createMovieClickListener(): View.OnClickListener {
        return View.OnClickListener {  }
    }
}