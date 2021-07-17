package com.jumpywiz.starwarsmovies.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.jumpywiz.starwarsmovies.R
import com.jumpywiz.starwarsmovies.databinding.FragmentCharacterInfoBinding
import com.jumpywiz.starwarsmovies.viewmodels.CharacterInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterInfoFragment : Fragment() {

    private var binding: FragmentCharacterInfoBinding? = null

    private val viewModel: CharacterInfoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterInfoBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionBar: ActionBar = (activity as AppCompatActivity).supportActionBar!!
        actionBar.setHomeButtonEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.title = requireArguments()["CHAR_NAME"] as String

        binding!!.repeatButton.setOnClickListener {
            viewModel.getCharInfo()
        }

        with(viewModel) {
            char.observe(viewLifecycleOwner, {
                val char = it.first
                val planet = it.second
                with(binding!!) {
                    characterInfoNameText.text = char.name
                    characterInfoSexText.text =
                        String.format(resources.getString(R.string.gender), char.sex)
                    characterInfoBirthDateText.text =
                        String.format(resources.getString(R.string.birth), char.birthDate)

                    planetNameText.text = planet.name
                    diameterText.text =
                        String.format(resources.getString(R.string.diameter), planet.diameter)
                    climateText.text =
                        String.format(resources.getString(R.string.climate), planet.climate)
                    gravityText.text =
                        String.format(resources.getString(R.string.gravity), planet.gravity)
                    terrainText.text =
                        String.format(resources.getString(R.string.terrain), planet.terrain)
                    populationText.text =
                        String.format(resources.getString(R.string.population), planet.population)
                }
            })

            isLoading.observe(viewLifecycleOwner, { state ->
                with(binding!!) {
                    loadingProgressBar.isVisible = state
                    cardView.visibility = if (state == true) {
                        View.GONE
                    } else (View.VISIBLE)
                }
            })

            onError.observe(viewLifecycleOwner, { state ->
                with(binding!!) {
                    repeatButton.isEnabled = state
                    repeatButton.isVisible = state
                    cardView.visibility = if (state == true) {
                        View.GONE
                    } else (cardView.visibility)
                }
            })
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                Log.d("Back pressed", "[CharacterInfoFragment::] back pressed")
                findNavController().popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNewURL(requireArguments()["CHAR_URL"] as String)
    }

}