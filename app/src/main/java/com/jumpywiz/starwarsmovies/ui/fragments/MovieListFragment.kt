package com.jumpywiz.starwarsmovies.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jumpywiz.starwarsmovies.App
import com.jumpywiz.starwarsmovies.adapters.MovieListAdapter
import com.jumpywiz.starwarsmovies.databinding.FragmentMovieListBinding
import com.jumpywiz.starwarsmovies.ui.MovieClickListener
import com.jumpywiz.starwarsmovies.viewmodels.MovieListViewModel
import javax.inject.Inject


class MovieListFragment : Fragment() {
    private var binding: FragmentMovieListBinding? = null

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

        val viewModel =
            ViewModelProvider(
                requireActivity(),
                viewModelFactory
            ).get(MovieListViewModel::class.java)


        val navigate: (Int, Bundle?) -> Unit = { i: Int, bundle: Bundle? ->
            findNavController().navigate(i, bundle)
        }
        val adapter = MovieListAdapter(
            MovieClickListener(
                navigate
            )
        )
        with(binding!!) {
            movieListRecyclerView.adapter = adapter
            movieListRecyclerView.layoutManager = LinearLayoutManager(view.context)
        }

        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            adapter.setData(movies)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}