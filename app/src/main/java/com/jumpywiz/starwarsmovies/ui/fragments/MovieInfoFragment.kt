package com.jumpywiz.starwarsmovies.ui.fragments

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jumpywiz.starwarsmovies.adapters.MovieInfoAdapter
import com.jumpywiz.starwarsmovies.databinding.FragmentMovieInfoBinding
import com.jumpywiz.starwarsmovies.viewmodels.MovieInfoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieInfoFragment : Fragment() {

    private var binding: FragmentMovieInfoBinding? = null

    private val viewModel: MovieInfoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieInfoBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navigate: (Int, Bundle?) -> Unit = { i: Int, bundle: Bundle? ->
            findNavController().navigate(i, bundle)
        }
        val actionBar: ActionBar = (activity as AppCompatActivity).supportActionBar!!
        actionBar.setHomeButtonEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.title = requireArguments()["MOVIE_TITLE"] as String
        val adapter = MovieInfoAdapter(navigate, requireContext())
        with(binding!!) {
            characterRecyclerView.adapter = adapter
            characterRecyclerView.layoutManager = LinearLayoutManager(view.context)
            repeatButton.setOnClickListener {
                viewModel.getChars()
            }
        }

        with(viewModel) {
            chars.observe(viewLifecycleOwner, Observer { charsList ->
                adapter.setData(charsList)
            })

            isLoading.observe(viewLifecycleOwner, Observer { state ->
                with(binding!!) {
                    loadingProgressBar.isVisible = state
                    characterRecyclerView.visibility = if (state == true) {
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
                    characterRecyclerView.visibility = if (state == true) {
                        View.GONE
                    } else {
                        characterRecyclerView.visibility
                    }
                }
            })

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNewId(requireArguments()["MOVIE_ID"] as Int)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                Log.d("Back pressed", "[MovieInfoFragment::] back pressed")
                findNavController().popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}