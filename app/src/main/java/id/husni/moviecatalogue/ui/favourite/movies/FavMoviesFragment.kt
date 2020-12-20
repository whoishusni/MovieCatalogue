package id.husni.moviecatalogue.ui.favourite.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.husni.moviecatalogue.R
import id.husni.moviecatalogue.viewmodel.MyCustomViewModel
import kotlinx.android.synthetic.main.fragment_fav_movies.*

class FavMoviesFragment : Fragment() {
    private lateinit var favAdapter: FavMoviesAdapter
    private lateinit var viewModel: FavMoviesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            favAdapter = FavMoviesAdapter()
            val factory = MyCustomViewModel.getInstance(requireContext())
            viewModel = ViewModelProvider(this,factory)[FavMoviesViewModel::class.java]

            viewModel.getAllFavMovies().observe(this, Observer {
                favAdapter.setMoviesFav(it)
                favAdapter.notifyDataSetChanged()
            })
            rvMovies.apply {
                layoutManager = GridLayoutManager(context,2)
                setHasFixedSize(true)
                adapter = favAdapter
            }
        }
    }
}