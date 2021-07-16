package com.jumpywiz.starwarsmovies.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jumpywiz.starwarsmovies.adapters.MovieListAdapter
import com.jumpywiz.starwarsmovies.databinding.FragmentMovieListBinding
import com.jumpywiz.starwarsmovies.viewmodels.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private var binding: FragmentMovieListBinding? = null

    val viewModel: MovieListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            repeatButton.setOnClickListener {
                viewModel.getMoviesList()
            }
        }

        with(viewModel) {
            movies.observe(viewLifecycleOwner, { movies ->
                adapter.setData(movies)
            })

            isLoading.observe(viewLifecycleOwner, { state ->
                with(binding!!) {
                    loadingProgressBar.isVisible = state
                    movieListRecyclerView.visibility = if (state == true) {
                        View.GONE
                    } else {
                        View.VISIBLE
                    }
                }
            })

            onError.observe(viewLifecycleOwner, { state ->
                with(binding!!) {
                    repeatButton.isEnabled = state
                    repeatButton.isVisible = state
                    movieListRecyclerView.visibility = if (state == true) {
                        View.GONE
                    } else {
                        movieListRecyclerView.visibility
                    }
                }
            })
        }

        binding!!.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}