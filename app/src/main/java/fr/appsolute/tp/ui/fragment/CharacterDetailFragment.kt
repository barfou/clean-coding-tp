package fr.appsolute.tp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import fr.appsolute.tp.R
import fr.appsolute.tp.ui.activity.MainActivity
import fr.appsolute.tp.ui.viewmodel.CharacterViewModel
import kotlinx.android.synthetic.main.fragment_detail_list.view.*
class CharacterDetailsFragment : Fragment() {
    private lateinit var characterViewModel: CharacterViewModel
    private var characterId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            characterViewModel = ViewModelProvider(this, CharacterViewModel).get()
        } ?: throw IllegalStateException("Invalid Activity")
        characterId =
            arguments?.getInt(ARG_CHARACTER_ID_KEY) ?: throw IllegalStateException("No ID found")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterViewModel.getCharacterById(characterId) {
            (activity as? MainActivity)?.supportActionBar?.apply {
                this.title = it.name
            }
            view.apply {
                this.character_details_species.text = it.species
                this.character_details_location_text.text = it.location.name
                Glide.with(this)
                    .load(it.image)
                    .into(this.character_details_image_view)
            }
        }
    }
    companion object {
        const val ARG_CHARACTER_ID_KEY = "arg_character_id_key"
    }
}