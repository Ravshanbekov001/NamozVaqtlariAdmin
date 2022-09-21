package uz.futuresoft.namozvaqtlariadmin

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var TIMES: TimesModel

const val NODE_TIMES = "times"
const val BOMDOD = "bomdod"
const val QUYOSH = "quyosh"
const val PESHIN = "peshin"
const val ASR = "asr"
const val SHOM = "shom"
const val XUFTON = "xufton"

fun initFirebase() {
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    TIMES = TimesModel()
}