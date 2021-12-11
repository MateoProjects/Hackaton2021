import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tfg.hackatonapp.R
import com.tfg.hackatonapp.register

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callRegister()
    }
    fun callRegister() {
        supportFragmentManager.beginTransaction().replace(R.id.mainActivity, register()).commit()
    }
}


   
