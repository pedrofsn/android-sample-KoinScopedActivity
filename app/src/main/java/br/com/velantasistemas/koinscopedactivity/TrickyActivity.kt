package br.com.velantasistemas.koinscopedactivity

import android.os.Bundle
import br.com.velantasistemas.koinscopedactivity.databinding.ActivityTrickyBinding
import org.koin.androidx.scope.ScopeActivity
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

class TrickyActivity : ScopeActivity(), MVP.View {

    private lateinit var binding: ActivityTrickyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(Modules.instance)
        super.onCreate(savedInstanceState)

        binding = ActivityTrickyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener { loadContent() }
    }

    override fun loadContent() {
        val presenter = scope.get<MVP.Presenter> {
            val id = intent.getIntExtra("id", -1)
            parametersOf(this@TrickyActivity, id)
        }
        presenter.initialize()
    }

    override fun showContent(message: String) {
        binding.textView.text = message
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(Modules.instance)
    }
}
