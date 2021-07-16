package com.jumpywiz.starwarsmovies.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jumpywiz.starwarsmovies.App
import com.jumpywiz.starwarsmovies.adapters.MovieListAdapter
import com.jumpywiz.starwarsmovies.databinding.FragmentMovieListBinding
import com.jumpywiz.starwarsmovies.ui.MovieClickListener
import com.jumpywiz.starwarsmovies.viewmodels.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private var binding: FragmentMovieListBinding? = null

    val viewModel: MovieListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionBar: ActionBar = (activity as AppCompatActivity).supportActionBar!!
        actionBar.setHomeButtonEnabled(false)
        actionBar.setDisplayHomeAsUpEnabled(false)
        actionBar.title = "Star Wars Movies"
        val navigate: (Int, Bundle?) -> Unit = { i: Int, bundle: Bundle? ->
            findNavController().navigate(i, bundle)
        }
        val adapter = MovieListAdapter(
            navigate
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