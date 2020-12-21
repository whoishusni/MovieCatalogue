package id.husni.moviecatalogue.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import id.husni.moviecatalogue.R
import id.husni.moviecatalogue.viewmodel.MyCustomViewModel
import kotlinx.android.synthetic.main.fragment_movies.*


class MoviesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val adapters = MoviesAdapter()
            val viewModel = obtainViewModel(requireActivity())
            showProgressbar(true)
            viewModel.getMovies().observe(viewLifecycleOwner, Observer {
                showProgressbar(false)
                adapters.setMovies(it)
                adapters.notifyDataSetChanged()
            })
            rvMovies.apply {
                layoutManager = GridLayoutManager(context,2)
                setHasFixedSize(true)
                adapter = adapters
            }
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): MoviesViewModel{
        val factory = MyCustomViewModel.getInstance(activity.application)
        return ViewModelProvider(this, factory)[MoviesViewModel::class.java]
    }

    private fun showProgressbar(isShow: Boolean) {
        if (isShow){
            progressBar.visibility = View.VISIBLE
        }
        else{
            progressBar.visibility = View.GONE
        }
    }
}