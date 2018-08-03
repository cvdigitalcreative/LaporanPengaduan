package com.example.user.laporanpengaduan.mFragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.laporanpengaduan.R;
import com.example.user.laporanpengaduan.mData.HistoryInfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class PengaduanFragment extends Fragment {
    private FirebaseUser user;
    private DatabaseReference databaseRef;
    private StorageReference storageRef;
    private StorageReference mstorageRef;
    private Button btnBrowse, btnKirim;
    private EditText etNama, etLokasi, etDeskripsi;
    private Spinner sKategori;
    private ImageView ivUpload;
    private Uri uri;
    public static final int REQUEST_CODE = 1234;

    public PengaduanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengaduan, container, false);

        etNama =  view.findViewById(R.id.et_nama);
        sKategori = view.findViewById(R.id.s_kategori);
        etDeskripsi= view.findViewById(R.id.et_deskripsi);
        etLokasi = view.findViewById(R.id.et_lokasi);
        ivUpload = view.findViewById(R.id.iv_uploadFoto);
        btnBrowse =view.findViewById(R.id.btn_browseFoto);
        btnKirim = view.findViewById(R.id.btn_Kirim);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mstorageRef = FirebaseStorage.getInstance().getReference();
        databaseRef = FirebaseDatabase.getInstance().getReference("user/");

        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih Gambar : "), REQUEST_CODE);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinnerItems, android.R.layout.simple_list_item_1);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sKategori.setAdapter(adapter);

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uri != null){
                    final ProgressDialog dialog = new ProgressDialog(getActivity());
                    dialog.setTitle("Mengirim data....");
                    dialog.show();

                    storageRef = mstorageRef.child("image/").child(uri.getLastPathSegment());
                    storageRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            dialog.dismiss();
                            String Currentdate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

                            user = FirebaseAuth.getInstance().getCurrentUser();
                            String email = user.getEmail();
                            String status = "Pengaduan belum dikerjakan";


                            HistoryInfo info = new HistoryInfo(etNama.getText().toString(),sKategori.getSelectedItem().toString(),
                                    etDeskripsi.getText().toString(), etLokasi.getText().toString(), Currentdate ,
                                    taskSnapshot.getDownloadUrl().toString(), email, status);

                            String infoId = databaseRef.push().getKey();
                            databaseRef.child(infoId).setValue(info);
                            Toast.makeText(getActivity().getApplicationContext(), "Data berhasil dikirim" , Toast.LENGTH_SHORT).show();
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    dialog.dismiss();
                                    Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress = (100 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                                    dialog.setMessage("Pengiriman data .. "+(int)progress+"%");
                                }
                            });
                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(), "Tolong pilih dengan benar!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){
            uri = data.getData();

            try {
                Bitmap bmp = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                ivUpload.setImageBitmap(bmp);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
