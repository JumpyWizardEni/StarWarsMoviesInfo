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
import android.R
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
                    characterInfoSexText.text = char.sex
                    characterInfoBirthDateText.text = char.birthDate

                    planetNameText.text = planet.name
                    diameterText.text = planet.diameter
                    climateText.text = planet.climate
                    gravityText.text = planet.gravity
                    terrainText.text = planet.terrain
                    populationText.text = planet.population
                }
            })

            isLoading.observe(viewLifecycleOwner, { state ->
                binding!!.loadingProgressBar.isVisible = state
                setText(if (state == true) {View.GONE} else (View.VISIBLE))
            })

            onError.observe(viewLifecycleOwner, { state ->
                with(binding!!) {
                    repeatButton.isEnabled = state
                    repeatButton.isVisible = state
                    setText(if (state == true) {View.GONE} else (planetNameText.visibility))
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
            R.id.home -> {
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

    private fun setText(state: Int) {
        with(binding!!) {
            characterInfoNameText.visibility = state
            characterInfoSexText.visibility = state
            characterInfoBirthDateText.visibility = state
            planetNameText.visibility = state
            diameterText.visibility = state
            climateText.visibility = state
            gravityText.visibility = state
            terrainText.visibility = state
            populationText.visibility = state
        }

    }


}