package com.jumpywiz.starwarsmovies.ui.fragments

import android.R
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.jumpywiz.starwarsmovies.databinding.FragmentCharacterInfoBinding
import com.jumpywiz.starwarsmovies.viewmodels.CharacterInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterInfoFragment : Fragment() {

    private var binding: FragmentCharacterInfoBinding? = null

    private val viewModel: CharacterInfoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        viewModel.char.observe(viewLifecycleOwner, Observer {
            val char = it.first
            val planet = it.second
            with(binding!!) {
                characterInfoNameText.text = char.name
                characterInfoSexText.text = char.sex
                characterInfoBirthDateText.text = char.birthDate

                planetNameText.text = planet.name
                diameterText.text = planet.diameter.toString()
                climateText.text = planet.climate
                gravityText.text = planet.gravity
                terrainText.text = planet.terrain
                populationText.text = planet.population.toString()
            }
        })

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

}