package kz.kcell.kcellbootcamp.presentation.moviesList

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kz.kcell.kcellbootcamp.R
import kz.kcell.kcellbootcamp.data.entities.Movie
import kz.kcell.kcellbootcamp.databinding.FragmentMoviesBinding
import kz.kcell.kcellbootcamp.utils.Constants.ARG_MOVIE_ID
import kz.kcell.kcellbootcamp.utils.Resource
import kz.kcell.kcellbootcamp.utils.toast

@AndroidEntryPoint
class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private val binding by viewBinding(FragmentMoviesBinding::bind)
    private val viewModel by viewModels<MoviesViewModel>()
    private val adapter by lazy {
        MoviesAdapter(::onClickedMovie)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieRecyclerView.adapter = this@MoviesFragment.adapter
        setupObservers()

        val activity = requireActivity()
        val toolbar = activity.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = getString(R.string.movies_fragment)

        binding.serachButton.setOnClickListener{
            findNavController().navigate(R.id.action_moviesFragment_to_searchFragment)
        }
    }

    private fun setupObservers() {
        viewModel.content.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) {
                        adapter.submitList(it.data)
                    }
                }

                Resource.Status.ERROR ->
                    it.message?.let { msg -> toast(requireContext(), msg) }

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun onClickedMovie(movie: Movie) {
        findNavController().navigate(
            R.id.action_moviesFragment_to_movieDetailFragment,
            bundleOf(ARG_MOVIE_ID to movie.id)
        )
    }

}