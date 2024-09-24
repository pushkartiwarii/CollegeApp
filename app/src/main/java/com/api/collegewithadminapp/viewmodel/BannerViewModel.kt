package com.api.collegewithadminapp.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.api.collegewithadminapp.models.BannerModel
import com.api.collegewithadminapp.ui.adminscreens.ManageBanner
import com.api.collegewithadminapp.utilis.constant.BANNER
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.storage
import java.net.URL
import java.util.UUID

class BannerViewModel : ViewModel() {
    private val bannerRef = Firebase.firestore.collection(BANNER)

    private val storageRef = Firebase.storage.reference

    private val _isPosted = MutableLiveData<Boolean>()
    val isPosted:LiveData<Boolean> = _isPosted

    private val _isDeleted = MutableLiveData<Boolean>()
    val isDeleted :LiveData<Boolean> = _isDeleted

    private val _bannerList= MutableLiveData<List<BannerModel>>()
    val bannerList : LiveData<List<BannerModel>> = _bannerList

    fun saveImage(uri: Uri){
        _isPosted.postValue(false)
        val randomid = UUID.randomUUID().toString()

        val imageRef = storageRef.child("$BANNER/${randomid}.jpg")

        val uploadTask = imageRef.putFile(uri)

        uploadTask.addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener {
                uploadImage(it.toString(),randomid)
            }
        }
    }
    private fun uploadImage(imageUrl: String , docId: String){
        val map = mutableMapOf<String,String>()
        map["url"] = imageUrl
        map["docId"] = docId

        bannerRef.document(docId).set(map)
            .addOnSuccessListener {
                _isPosted.postValue(true
                )
            }
            .addOnFailureListener{
                _isPosted.postValue(false
                )
            }

    }
    fun getBanner(){
        bannerRef.get().addOnSuccessListener{
            val list = mutableListOf<BannerModel>()

            for(doc in it){
                list.add(doc.toObject(BannerModel::class.java))
            }
            _bannerList.postValue(list)

        }

    }
    fun deleteBanner(bannerModel:BannerModel){

        Firebase.storage.getReferenceFromUrl(bannerModel.url!!).delete()
        bannerRef.document(bannerModel.docId!!).delete()
            .addOnSuccessListener {
                Firebase.storage.getReferenceFromUrl(bannerModel.url!!).delete()
                _isDeleted.postValue(true)
            }
            .addOnFailureListener{
                _isDeleted.postValue(false)
            }
    }
}