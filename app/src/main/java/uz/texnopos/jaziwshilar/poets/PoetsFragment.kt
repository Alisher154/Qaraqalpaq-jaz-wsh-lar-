package uz.texnopos.jaziwshilar.poets

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import kotlinx.android.synthetic.main.fragment_poets_list.*
import uz.texnopos.jaziwshilar.R
import uz.texnopos.jaziwshilar.biography.BioActivity
import uz.texnopos.jaziwshilar.data.PoetEntity
import uz.texnopos.jaziwshilar.data.PoetsDao
import uz.texnopos.jaziwshilar.data.PoetsDatabase
import uz.texnopos.jaziwshilar.favorite.FavoriteViewModel

class PoetsFragment : Fragment(R.layout.fragment_poets_list) {
    companion object {
        const val ID = "id"
    }

    lateinit var menuItem: MenuItem

    //    private lateinit var dao: PoetsDao
    private val adapter = PoetAdapter()
    private lateinit var viewModel: PoetsViewModel

    //    lateinit var presenter: PoetPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao = PoetsDatabase.getInstance(requireContext().applicationContext).dao()
        val repository = PoetRepository(dao)
        val factory = PoetsViewModel.Factory(repository)
        viewModel = ViewModelProvider(this, factory).get()
//        dao = PoetsDatabase.getInstance(requireContext()).dao()
//        presenter = PoetPresenter(dao, this)
//        presenter.getAllPoets()
        recyclerView.adapter = adapter

        viewModel.getPoets()
        viewModel.poets.observe(viewLifecycleOwner) {
            adapter.models = it
        }


        val intent = Intent(requireContext(), BioActivity::class.java)
        adapter.setOnItemClickListener { id ->
            intent.putExtra(ID, id)
            startActivity(intent)
        }
    }

//    override fun setData(models: List<PoetEntity>) {
//        adapter.models = models
//    }
//
//    override fun filteredNames(list: List<PoetEntity>) {
//        adapter.models = list
//    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        menuItem = menu.findItem(R.id.action_search)
        val viewSearch = menuItem.actionView as SearchView
        viewSearch.queryHint = getString(R.string.search)
        viewSearch.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewSearch.clearFocus()
//                presenter.filter(query!!)
                query?.let { viewModel.getPoetsByName(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                presenter.filter(newText!!)
                newText?.let { viewModel.getPoetsByName(it) }
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}