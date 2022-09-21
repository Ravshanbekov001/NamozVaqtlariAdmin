package uz.futuresoft.namozvaqtlariadmin

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import uz.futuresoft.namozvaqtlariadmin.databinding.ActivityMainBinding
import uz.futuresoft.namozvaqtlariadmin.utilits.AppValueEventListener

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        getData()

        binding.btnSave.setOnClickListener {
            saveData()
        }
    }

    private fun initFields() {
        initFirebase()
    }

    private fun saveData() {
        val bomdod = binding.TIEBomdod.text.toString().trim()
        val quyosh = binding.TIEQuyosh.text.toString().trim()
        val peshin = binding.TIEPeshin.text.toString().trim()
        val asr = binding.TIEAsr.text.toString().trim()
        val shom = binding.TIEShom.text.toString().trim()
        val xufton = binding.TIEXufton.text.toString().trim()

        if (bomdod.isNotEmpty() && quyosh.isNotEmpty() && peshin.isNotEmpty() && asr.isNotEmpty() && shom.isNotEmpty() && xufton.isNotEmpty()) {
            val dataMap = mutableMapOf<String, Any>()
            dataMap[BOMDOD] = bomdod
            dataMap[QUYOSH] = quyosh
            dataMap[PESHIN] = peshin
            dataMap[ASR] = asr
            dataMap[SHOM] = shom
            dataMap[XUFTON] = xufton

            REF_DATABASE_ROOT.child(NODE_TIMES).updateChildren(dataMap).addOnCompleteListener {
                if (it.isSuccessful) {
                    showToast(getString(R.string.saqlandi))
                } else {
                    showToast(it.exception?.message.toString())
                }
            }
        } else {
            showToast(getString(R.string.berilgan_maydonlarni_toldiring))
        }
    }

    private fun getData() {
        REF_DATABASE_ROOT.child(NODE_TIMES).addValueEventListener(AppValueEventListener {
            TIMES = it.getValue(TimesModel::class.java) ?: TimesModel()
            binding.TIEBomdod.setText(TIMES.bomdod)
            binding.TIEQuyosh.setText(TIMES.quyosh)
            binding.TIEPeshin.setText(TIMES.peshin)
            binding.TIEAsr.setText(TIMES.asr)
            binding.TIEShom.setText(TIMES.shom)
            binding.TIEXufton.setText(TIMES.xufton)
        })
    }
}