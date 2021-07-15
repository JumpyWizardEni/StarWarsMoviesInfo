package com.jumpywiz.starwarsmovies.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.jumpywiz.starwarsmovies.App
import com.jumpywiz.starwarsmovies.adapters.MovieInfoAdapter
import com.jumpywiz.starwarsmovies.databinding.FragmentMovieInfoBinding
import com.jumpywiz.starwarsmovies.viewmodels.MovieInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieInfoFragment : Fragment() {

    private var binding: FragmentMovieInfoBinding? = null

    @Inject
    lateinit var viewModelFactory: MovieInfoViewModel.AssistedFactory

    val viewModel: MovieInfoViewModel by viewModels {
        MovieInfoViewModel.provideFactory(viewModelFactory, requireArguments()["MOVIE_ID"] as Int)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieInfoBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navigate: (Int, Bundle?) -> Unit = { i: Int, bundle: Bundle? ->
            findNavController().navigate(i, bundle)
        }

        val adapter = MovieInfoAdapter(navigate)
        with(binding!!) {
            characterRecyclerView.adapter = adapter
            characterRecyclerView.layoutManager = LinearLayoutManager(view.context)
        }

        viewModel.chars.observe(viewLifecycleOwner, Observer { charsList ->
            adapter.setData(charsList)
        }
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}