package com.jumpywiz.starwarsmovies.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.jumpywiz.starwarsmovies.databinding.FragmentCharacterInfoBinding
import com.jumpywiz.starwarsmovies.viewmodels.CharacterInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterInfoFragment : Fragment() {

    private var binding: FragmentCharacterInfoBinding? = null

    @Inject
    lateinit var viewModelFactory: CharacterInfoViewModel.AssistedFactory

    val viewModel: CharacterInfoViewModel by viewModels {
        CharacterInfoViewModel.provideFactory(viewModelFactory, requireArguments()["CHAR_URL"] as String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterInfoBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel.char.observe(viewLifecycleOwner, Observer {
//            val char = it.first
//            val planet = it.second
//            with(binding!!) {
//                characterInfoNameText.text = char.name
//                characterInfoSexText.text = char.sex
//                characterInfoBirthDateText.text = char.birthDate
//
//                planetNameText.text = planet.name
//                diameterText.text = planet.diameter.toString()
//                climateText.text = planet.climate
//                gravityText.text = planet.gravity
//                terrainText.text = planet.terrain
//                populationText.text = planet.population.toString()
//            }
//
//        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}