package uz.texnopos.jaziwshilar.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import uz.texnopos.jaziwshilar.biography.BioActivity
import uz.texnopos.jaziwshilar.data.PoetsDatabase
import uz.texnopos.jaziwshilar.poets.PoetsFragment.Companion.ID
import uz.texnopos.jaziwshilar.poets.PoetAdapter
import kotlinx.android.synthetic.main.fragment_chosen.*
import uz.texnopos.jaziwshilar.R

class FavoriteFragment : Fragment(R.layout.fragment_chosen){
    private lateinit var viewModel: FavoriteViewModel
    private val adapter = PoetAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao = PoetsDatabase.getInstance(requireContext().applicationContext).dao()
        val repository = FavoriteRepository(dao)
        val factory = FavoriteViewModel.Factory(repository)
        viewModel = ViewModelProvider(this, factory).get()
        rvChosen.adapter = adapter


        val intent = Intent(requireContext(), BioActivity::class.java)
        adapter.setOnItemClickListener { id ->
            intent.putExtra(ID, id)
            startActivity(intent)
        }

        viewModel.favorites.observe(viewLifecycleOwner){
            adapter.models = it
            if (it.isEmpty()) linearLayout.visibility = View.VISIBLE
            else linearLayout.visibility = View.INVISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllFavorites()
    }


}
