package com.example.placar

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.placar.databinding.ActivityPlayerBinding
import com.example.placar.utils.KEY_RESULT_EXTRA_PLAYER_ONE_NAME
import com.example.placar.utils.KEY_RESULT_EXTRA_PLAYER_ONE_SCORE
import com.example.placar.utils.KEY_RESULT_EXTRA_PLAYER_TWO_NAME
import com.example.placar.utils.KEY_RESULT_EXTRA_PLAYER_TWO_SCORE

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding

    private val previewRequest = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode== RESULT_OK){
            val lastResult = getString(R.string.message_to_share, it.data?.getStringExtra(
                KEY_RESULT_EXTRA_PLAYER_ONE_NAME), it.data?.getStringExtra(
                KEY_RESULT_EXTRA_PLAYER_TWO_NAME), it.data?.getIntExtra(
                KEY_RESULT_EXTRA_PLAYER_ONE_SCORE, 0), it.data?.getIntExtra(
                KEY_RESULT_EXTRA_PLAYER_TWO_SCORE, 0))
                binding.tvLastGame.text = lastResult
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btStart.setOnClickListener {
            val nextScreen = Intent(this, MainActivity::class.java)
            nextScreen.putExtra("PLAYER1", binding.etPlayer1.text.toString())
            nextScreen.putExtra("PLAYER2", binding.etPlayer2.text.toString())
            //startActivity(nextScreen)
            previewRequest.launch(nextScreen)
        }

    }
}