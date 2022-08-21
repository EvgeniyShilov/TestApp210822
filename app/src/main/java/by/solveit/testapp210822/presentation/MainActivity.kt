package by.solveit.testapp210822.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.solveit.testapp210822.R
import by.solveit.testapp210822.presentation.preview.PreviewFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .replace(R.id.vContainer, PreviewFragment.newInstance())
            .commit()
    }
}