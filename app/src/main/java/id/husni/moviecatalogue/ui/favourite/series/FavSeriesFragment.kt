package id.husni.moviecatalogue.ui.favourite.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.husni.moviecatalogue.R
import id.husni.moviecatalogue.viewmodel.MyCustomViewModel
import kotlinx.android.synthetic.main.fragment_fav_series.*

class FavSeriesFragment : Fragment() {
    lateinit var favAdapter: FavSeriesAdapter
    lateinit var viewModel: FavSeriesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_series, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            favAdapter = FavSeriesAdapter()
            viewModel = obtainViewModel(requireActivity())
            viewModel.getAllFavSeries().observe(this, Observer { series->
                favAdapter.submitList(series)
                favAdapter.notifyDataSetChanged()
            })
            rvSeries.apply {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = favAdapter
            }
        }

    }

    private fun obtainViewModel(activity: FragmentActivity): FavSeriesViewModel {
        val factory = MyCustomViewModel.getInstance(activity.application)
        return ViewModelProvider(this, factory)[FavSeriesViewModel::class.java]
    }
}